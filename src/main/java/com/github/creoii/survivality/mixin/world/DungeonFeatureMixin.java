package com.github.creoii.survivality.mixin.world;

import com.github.creoii.survivality.Survivality;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.DungeonFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DungeonFeature.class)
public abstract class DungeonFeatureMixin {
    @Shadow protected abstract EntityType<?> getMobSpawnerEntity(Random random);

    @Inject(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/MobSpawnerBlockEntity;setEntityType(Lnet/minecraft/entity/EntityType;Lnet/minecraft/util/math/random/Random;)V", shift = At.Shift.AFTER))
    private void survivality_variantSpawners(FeatureContext<DefaultFeatureConfig> context, CallbackInfoReturnable<Boolean> cir) {
        if (!Survivality.CONFIG.variantSpawners) return;
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        StructureWorldAccess structureWorldAccess = context.getWorld();
        MobSpawnerBlockEntity mobSpawnerBlockEntity = (MobSpawnerBlockEntity) structureWorldAccess.getBlockEntity(blockPos);
        EntityType<?> type = getMobSpawnerEntity(random);
        RegistryEntry<Biome> biome = structureWorldAccess.getBiome(blockPos);
        if (!biome.hasKeyAndValue()) return;
        if (type == EntityType.SKELETON && biome.value().isCold(blockPos)) {
            mobSpawnerBlockEntity.setEntityType(EntityType.STRAY, random);
        } else if (type == EntityType.ZOMBIE) {
            if (biome.value().getTemperature() > 1f) {
                mobSpawnerBlockEntity.setEntityType(EntityType.HUSK, random);
            } else if (biome.isIn(BiomeTags.IS_OCEAN)) {
                mobSpawnerBlockEntity.setEntityType(EntityType.DROWNED, random);
            }
        } else mobSpawnerBlockEntity.setEntityType(type, random);
    }
}