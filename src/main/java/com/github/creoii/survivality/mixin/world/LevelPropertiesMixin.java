package com.github.creoii.survivality.mixin.world;

import com.mojang.serialization.Lifecycle;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.GeneratorOptions;
import net.minecraft.world.level.LevelInfo;
import net.minecraft.world.level.LevelProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelProperties.class)
public class LevelPropertiesMixin {
    @Shadow private int spawnX;
    @Shadow private int spawnZ;
    @Shadow private float spawnAngle;
    @Shadow private long timeOfDay;
    @Shadow private int rainTime;
    @Shadow private boolean raining;
    @Shadow private int thunderTime;
    @Shadow private boolean thundering;
    private static final Random RANDOM = Random.createLocal();

    @Inject(method = "<init>(Lnet/minecraft/world/level/LevelInfo;Lnet/minecraft/world/gen/GeneratorOptions;Lnet/minecraft/world/level/LevelProperties$SpecialProperty;Lcom/mojang/serialization/Lifecycle;)V", at = @At("TAIL"))
    private void great_big_world_startWorldRandomly(LevelInfo levelInfo, GeneratorOptions generatorOptions, LevelProperties.SpecialProperty specialProperty, Lifecycle lifecycle, CallbackInfo ci) {
        spawnX = RANDOM.nextInt(24000);
        spawnZ = RANDOM.nextInt(24000);
        spawnAngle = (float)RANDOM.nextInt(360);
        timeOfDay = RANDOM.nextInt(24000);
        rainTime = RANDOM.nextInt(180000);
        raining = RANDOM.nextInt(5) == 0;
        thunderTime = RANDOM.nextInt(180000);
        thundering = RANDOM.nextInt(5) == 0;
    }
}
