package com.github.creoii.survivality.mixin.entity;

import com.github.creoii.survivality.Survivality;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.ZombieHorseEntity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.inventory.InventoryChangedListener;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(HorseEntity.class)
public abstract class HorseEntityMixin extends AbstractHorseEntity implements InventoryChangedListener, RideableInventory, JumpingMount, Saddleable {
    protected HorseEntityMixin(EntityType<? extends AbstractHorseEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void onStruckByLightning(ServerWorld world, LightningEntity lightning) {
        if (world.getDifficulty() != Difficulty.PEACEFUL && Survivality.CONFIG.zombieHorseTransmutation) {
            ZombieHorseEntity zombieHorseEntity = EntityType.ZOMBIE_HORSE.create(world);
            if (zombieHorseEntity != null) {
                zombieHorseEntity.refreshPositionAndAngles(getX(), getY(), getZ(), getYaw(), getPitch());
                zombieHorseEntity.initialize(world, world.getLocalDifficulty(zombieHorseEntity.getBlockPos()), SpawnReason.CONVERSION, null, null);
                zombieHorseEntity.setAiDisabled(isAiDisabled());
                EntityAttributeInstance jumpStrength = zombieHorseEntity.getAttributeInstance(EntityAttributes.HORSE_JUMP_STRENGTH);
                if (jumpStrength != null) {
                    jumpStrength.setBaseValue(getAttributeBaseValue(EntityAttributes.HORSE_JUMP_STRENGTH));
                }

                if (hasCustomName()) {
                    zombieHorseEntity.setCustomName(getCustomName());
                    zombieHorseEntity.setCustomNameVisible(isCustomNameVisible());
                }

                zombieHorseEntity.setPersistent();
                world.spawnNewEntityAndPassengers(zombieHorseEntity);
                discard();
            } else super.onStruckByLightning(world, lightning);
        }
    }
}
