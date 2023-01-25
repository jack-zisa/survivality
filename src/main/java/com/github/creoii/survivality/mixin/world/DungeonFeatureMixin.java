package com.github.creoii.survivality.mixin.world;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.DungeonFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.function.Predicate;

@Mixin(DungeonFeature.class)
public class DungeonFeatureMixin {
    @Inject(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/MobSpawnerBlockEntity;setEntityType(Lnet/minecraft/entity/EntityType;Lnet/minecraft/util/math/random/Random;)V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void survivality_variantSpawners(FeatureContext<DefaultFeatureConfig> context, CallbackInfoReturnable<Boolean> cir, Predicate<BlockState> predicate, BlockPos blockPos, Random random, StructureWorldAccess structureWorldAccess, int j, int o, BlockEntity blockEntity, MobSpawnerBlockEntity mobSpawnerBlockEntity) {
        //if (!Survivality.CONFIG.variantSpawners) return;
        //EntityType<?> type = getMobSpawnerEntity(random);
        /*if (type == EntityType.SKELETON && structureWorldAccess.getBiome(blockPos).value().isCold(blockPos)) {
            mobSpawnerBlockEntity.setEntityType(EntityType.STRAY, random);
        } else if (type == EntityType.ZOMBIE) {
            if (structureWorldAccess.getBiome(blockPos).value().isHot(blockPos)) {
                mobSpawnerBlockEntity.setEntityType(EntityType.HUSK, random);
            } else if (structureWorldAccess.getBiome(blockPos).isIn(BiomeTags.IS_OCEAN)) {
                mobSpawnerBlockEntity.setEntityType(EntityType.DROWNED, random);
            }
        } else mobSpawnerBlockEntity.setEntityType(type, random);*/
    }
}
