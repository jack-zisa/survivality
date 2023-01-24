package com.github.creoii.survivality.mixin.block;

import com.github.creoii.survivality.util.SurvivalityUtils;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LeavesBlock.class)
public class LeavesBlockMixin extends Block implements Waterloggable {
    public LeavesBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void survivality_defaultSnowiness(Settings settings, CallbackInfo ci) {
        setDefaultState(stateManager.getDefaultState().with(LeavesBlock.DISTANCE, 7).with(LeavesBlock.PERSISTENT, false).with(LeavesBlock.WATERLOGGED, false).with(SurvivalityUtils.SNOWY, false));
    }

    @Inject(method = "appendProperties", at = @At("TAIL"))
    private void survivality_appendSnowyLeaves(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(SurvivalityUtils.SNOWY);
    }

    @Inject(method = "hasRandomTicks", at = @At("HEAD"), cancellable = true)
    private void survivality_allowSnowyTicks(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }


    @Inject(method = "randomTick", at = @At("TAIL"))
    private void survivality_tickSnowyLeaves(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (world.isRaining() && world.getBiome(pos).value().isCold(pos)) {
            if (!state.get(SurvivalityUtils.SNOWY)) {
                world.setBlockState(pos, state.with(SurvivalityUtils.SNOWY, true), 3);
            }
        } else if (state.get(SurvivalityUtils.SNOWY)) {
            world.setBlockState(pos, state.with(SurvivalityUtils.SNOWY, false), 3);
        }
    }
}
