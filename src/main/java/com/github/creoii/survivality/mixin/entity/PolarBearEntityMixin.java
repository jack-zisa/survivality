package com.github.creoii.survivality.mixin.entity;

import com.github.creoii.survivality.Survivality;
import com.github.creoii.survivality.integration.ModMenuIntegration;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.StrayEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

@Mixin(PolarBearEntity.class)
public abstract class PolarBearEntityMixin extends AnimalEntity implements Angerable {
    protected PolarBearEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initialize", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/AnimalEntity;initialize(Lnet/minecraft/world/ServerWorldAccess;Lnet/minecraft/world/LocalDifficulty;Lnet/minecraft/entity/SpawnReason;Lnet/minecraft/entity/EntityData;Lnet/minecraft/nbt/NbtCompound;)Lnet/minecraft/entity/EntityData;"))
    private void survivality_polarBearCavalry(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, NbtCompound entityNbt, CallbackInfoReturnable<EntityData> cir) {
        float value = Survivality.CONFIG_AVAILABLE ? ModMenuIntegration.CONFIG.polarBearCavalryChance.floatValue() : .01f;
        if (random.nextFloat() <= value && spawnReason == SpawnReason.NATURAL) {
            StrayEntity strayEntity = EntityType.STRAY.create(getWorld());
            strayEntity.refreshPositionAndAngles(getX(), getY(), getZ(), getYaw(), 0f);
            strayEntity.initialize(world, difficulty, spawnReason, null, null);

            LocalDate localDate = LocalDate.now();
            if (localDate.get(ChronoField.DAY_OF_MONTH) == 1 && localDate.get(ChronoField.MONTH_OF_YEAR) == 4) {
                startRiding(strayEntity);
                world.spawnEntity(strayEntity);
            } else strayEntity.startRiding(this);
        }
    }
}
