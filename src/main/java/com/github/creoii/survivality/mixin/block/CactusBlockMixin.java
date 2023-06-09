package com.github.creoii.survivality.mixin.block;

import com.github.creoii.survivality.Survivality;
import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CactusBlock.class)
public class CactusBlockMixin {
    @Inject(method = "onEntityCollision", at = @At("HEAD"), cancellable = true)
    private void survivality_cactiDontBreakCacti(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
        if (Survivality.CONFIG.safeCactus.booleanValue() && entity instanceof ItemEntity itemEntity && itemEntity.getStack().isOf(Items.CACTUS)) {
            ci.cancel();
        }
    }

    @ModifyConstant(method = "randomTick", constant = @Constant(intValue = 3))
    private int survivality_increaseCactusHeight(int constant) {
        return Survivality.CONFIG.cactusGrowHeight.intValue();
    }
}
