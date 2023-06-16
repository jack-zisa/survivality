package com.github.creoii.survivality.mixin.world;

import com.github.creoii.survivality.Survivality;
import com.github.creoii.survivality.integration.ModMenuIntegration;
import com.google.common.collect.Lists;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MultiNoiseUtil.MultiNoiseSampler.class)
public class MultiNoiseSamplerMixin {
    @Mutable @Shadow @Final private List<MultiNoiseUtil.NoiseHypercube> spawnTarget;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void survivality_randomSpawnBiomes(DensityFunction densityFunction, DensityFunction densityFunction2, DensityFunction densityFunction3, DensityFunction densityFunction4, DensityFunction densityFunction5, DensityFunction densityFunction6, List list, CallbackInfo ci) {
        boolean value = Survivality.CONFIG_AVAILABLE ? ModMenuIntegration.CONFIG.randomWorldStartBiome.booleanValue() : true;
        if (!value) return;
        spawnTarget = Lists.newArrayList(MultiNoiseUtil.createNoiseHypercube(MultiNoiseUtil.ParameterRange.of(-1f, 1f), MultiNoiseUtil.ParameterRange.of(-1f, 1f), MultiNoiseUtil.ParameterRange.of(-1f, 1f), MultiNoiseUtil.ParameterRange.of(-1f, 1f), MultiNoiseUtil.ParameterRange.of(-1f, 1f), MultiNoiseUtil.ParameterRange.of(-1f, 1f), 0f));
    }
}
