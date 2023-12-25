package com.github.creoii.survivality.mixin.block;

import com.github.creoii.survivality.Survivality;
import com.github.creoii.survivality.integration.ModMenuIntegration;
import com.github.creoii.survivality.util.SurvivalityTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityMixin {
    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/AbstractFurnaceBlockEntity;canAcceptRecipeOutput(Lnet/minecraft/registry/DynamicRegistryManager;Lnet/minecraft/recipe/Recipe;Lnet/minecraft/util/collection/DefaultedList;I)Z", ordinal = 1, shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void survivality_explodeTntFuel(World world, BlockPos pos, BlockState state, AbstractFurnaceBlockEntity blockEntity, CallbackInfo ci, boolean bl, boolean bl2, ItemStack itemStack, boolean bl3, boolean bl4, Recipe<?> recipe, int i) {
        boolean value = !Survivality.CONFIG_AVAILABLE || ModMenuIntegration.CONFIG.explosiveFuelExplosionChance.floatValue() != 0f;
        if (!world.isClient && value && blockEntity.cookTime % 20 == 0 && world.random.nextFloat() <= ModMenuIntegration.CONFIG.explosiveFuelExplosionChance.floatValue() && itemStack.isIn(SurvivalityTags.EXPLODING_FUELS)) {
            blockEntity.inventory.clear();
            world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 4f, World.ExplosionSourceType.BLOCK);
        }
    }
}
