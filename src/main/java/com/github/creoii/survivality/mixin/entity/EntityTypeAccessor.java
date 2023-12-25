package com.github.creoii.survivality.mixin.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityType.class)
public interface EntityTypeAccessor {
    @Accessor("spawnGroup")
    void setSpawnGroup(SpawnGroup spawnGroup);
}
