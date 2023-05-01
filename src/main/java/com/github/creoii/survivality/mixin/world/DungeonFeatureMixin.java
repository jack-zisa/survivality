package com.github.creoii.survivality.mixin.world;

import com.github.creoii.survivality.Survivality;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.tag.BiomeTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.MobSpawnerLogic;
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
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.function.Predicate;

@Mixin(DungeonFeature.class)
public abstract class DungeonFeatureMixin {
    @Shadow protected abstract EntityType<?> getMobSpawnerEntity(Random random);

    @Inject(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/MobSpawnerLogic;setEntityId(Lnet/minecraft/entity/EntityType;)V", shift = At.Shift.AFTER))
    private void survivality_variantSpawners(FeatureContext<DefaultFeatureConfig> context, CallbackInfoReturnable<Boolean> cir) {
        if (!Survivality.CONFIG.variantSpawners) return;
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockEntity blockEntity = structureWorldAccess.getBlockEntity(blockPos);
        MobSpawnerLogic logic = ((MobSpawnerBlockEntity) blockEntity).getLogic();
        EntityType<?> type = getMobSpawnerEntity(random);
        RegistryEntry<Biome> biome = structureWorldAccess.getBiome(blockPos);
        if (!biome.hasKeyAndValue()) return;
        if (type == EntityType.SKELETON && biome.value().isCold(blockPos)) {
            logic.setEntityId(EntityType.STRAY);
        } else if (type == EntityType.ZOMBIE) {
            if (biome.value().isHot(blockPos)) {
                logic.setEntityId(EntityType.HUSK);
            } else if (biome.isIn(BiomeTags.IS_OCEAN)) {
                logic.setEntityId(EntityType.DROWNED);
            }
        } else logic.setEntityId(type);
    }
}
