package com.github.creoii.survivality.mixin.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.StrayEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(StrayEntity.class)
public abstract class StrayEntityMixin extends AbstractSkeletonEntity {
    protected StrayEntityMixin(EntityType<? extends AbstractSkeletonEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected boolean isAffectedByDaylight() {
        Entity passenger = getFirstPassenger();
        if (passenger != null && passenger.getType() == EntityType.POLAR_BEAR) return false;
        return super.isAffectedByDaylight();
    }
}
