package com.github.creoii.survivality.mixin.item;

import com.google.common.collect.ImmutableList;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(CrossbowItem.class)
public abstract class CrossbowItemMixin {
    private static final List<List<Float>> SIMULATIONS = new ImmutableList.Builder<List<Float>>()
            .add(List.of(10f, 0f, -10f))
            .add(List.of(15f, 5f, -5f, -15f))
            .add(List.of(15f, 7.5f, 0f, -7.5f, -15f))
            .add(List.of(20f, 12f, 4f, -4f, -12f, -20f))
            .add(List.of(25f, 15f, 5f, 0f, -5f, -15f, -25f))
            .build();

    @Redirect(method = "onStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/CrossbowItem;loadProjectiles(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;)Z"))
    private boolean survivality_trueMultishot(LivingEntity shooter, ItemStack projectile) {
        return loadProjectiles(shooter, projectile);
    }

    private static boolean loadProjectiles(LivingEntity shooter, ItemStack projectile) {
        int i = EnchantmentHelper.getLevel(Enchantments.MULTISHOT, projectile);
        int j;
        if (i > 0) {
            j = switch (i) {
                case 1 -> 3;
                case 2 -> 4;
                case 3 -> 5;
                default -> 6;
            };
        } else j = 1;
        boolean bl = shooter instanceof PlayerEntity && ((PlayerEntity)shooter).getAbilities().creativeMode;
        ItemStack itemStack = shooter.getArrowType(projectile);
        ItemStack itemStack2 = itemStack.copy();

        for (int k = 0; k < j; ++k) {
            if (k > 0) {
                itemStack = itemStack2.copy();
            }

            if (itemStack.isEmpty() && bl) {
                itemStack = new ItemStack(Items.ARROW);
                itemStack2 = itemStack.copy();
            }

            if (!CrossbowItem.loadProjectile(shooter, projectile, itemStack, k > 0, bl)) {
                return false;
            }
        }
        return true;
    }

    @Inject(method = "shootAll", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/CrossbowItem;getSoundPitches(Lnet/minecraft/util/math/random/Random;)[F"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private static void survivality_shootAllProjectiles(World world, LivingEntity entity, Hand hand, ItemStack stack, float speed, float divergence, CallbackInfo ci, List<ItemStack> list) {
        int level = EnchantmentHelper.getLevel(Enchantments.MULTISHOT, stack);
        List<Float> simulation;
        if (level > Enchantments.MULTISHOT.getMaxLevel() + 1) simulation = SIMULATIONS.get(3);
        else simulation = SIMULATIONS.get(level - 1);
        for (int i = 0; i < list.size(); ++i) {
            ItemStack itemStack = list.get(i);
            boolean bl = entity instanceof PlayerEntity playerEntity && playerEntity.getAbilities().creativeMode;
            if (!itemStack.isEmpty()) {
                CrossbowItem.shoot(world, entity, hand, stack, itemStack, 1f, bl, speed, divergence, simulation.get(i));
            }
        }
        CrossbowItem.postShoot(world, entity, stack);
        ci.cancel();
    }
}
