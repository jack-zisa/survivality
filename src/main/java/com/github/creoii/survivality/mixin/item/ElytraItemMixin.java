package com.github.creoii.survivality.mixin.item;

import com.github.creoii.survivality.Survivality;
import com.github.creoii.survivality.util.SurvivalityUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Wearable;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ElytraItem.class)
@Environment(EnvType.CLIENT)
public class ElytraItemMixin extends Item implements Wearable {
    public ElytraItemMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/TypedActionResult;fail(Ljava/lang/Object;)Lnet/minecraft/util/TypedActionResult;"))
    private void survivality_swapElytra(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if (!Survivality.CONFIG.armorSwapping) return;
        SurvivalityUtils.swapArmor(user);
        if (!world.isClient()) {
            user.incrementStat(Stats.USED.getOrCreateStat(this));
        }
    }
}