package com.github.creoii.survivality.mixin.item;

import com.github.creoii.survivality.Survivality;
import com.github.creoii.survivality.integration.ModMenuIntegration;
import com.github.creoii.survivality.util.SurvivalityTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BoneMealItem.class)
public class BoneMealItemMixin {
    @Inject(method = "useOnBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isSideSolidFullSquare(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;)Z"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void survivality_fertilizeDirt(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir, World world, BlockPos blockPos, BlockPos blockPos2, BlockState blockState) {
        boolean value = Survivality.CONFIG_AVAILABLE ? ModMenuIntegration.CONFIG.fertilizableDirt.booleanValue() : true;
        if (!value) return;

        if (blockState.isOf(Blocks.DIRT)) {
            boolean nextToGrass = false;

            for (Direction direction : Direction.Type.HORIZONTAL) {
                if (world.getBlockState(blockPos.offset(direction)).isIn(SurvivalityTags.DIRT_FERTILIZABLE_GRASS)) {
                    nextToGrass = true;
                    break;
                }
            }

            if (nextToGrass) {
                if (world.setBlockState(blockPos, Blocks.GRASS_BLOCK.getDefaultState())) {
                    if (!world.isClient) {
                        world.syncWorldEvent(1505, blockPos2, 0);
                    }

                    context.getStack().decrement(1);
                    cir.setReturnValue(ActionResult.success(world.isClient));
                }
            }
        }
    }
}
