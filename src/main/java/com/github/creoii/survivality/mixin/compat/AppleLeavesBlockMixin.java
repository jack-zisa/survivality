package com.github.creoii.survivality.mixin.compat;

import com.github.creoii.survivality.util.SurvivalityUtils;
import io.github.uhq_games.regions_unexplored.world.level.block.AppleLeavesBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.LeavesBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AppleLeavesBlock.class)
public abstract class AppleLeavesBlockMixin extends LeavesBlock implements Fertilizable {
    @Shadow @Final public static IntProperty AGE;

    public AppleLeavesBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void survivality_defaultSnowiness(Settings settings, CallbackInfo ci) {
        setDefaultState(stateManager.getDefaultState().with(DISTANCE, 7).with(PERSISTENT, false).with(WATERLOGGED, false).with(AGE, 0).with(SurvivalityUtils.SNOWY, false));
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
