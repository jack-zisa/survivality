package com.github.creoii.survivality.mixin.entity;

import com.github.creoii.survivality.Survivality;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DrownedEntity.class)
public abstract class DrownedEntityMixin extends ZombieEntity implements RangedAttackMob {
    public DrownedEntityMixin(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initEquipment", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/DrownedEntity;equipStack(Lnet/minecraft/entity/EquipmentSlot;Lnet/minecraft/item/ItemStack;)V", shift = At.Shift.AFTER, ordinal = 0))
    private void survivality_moreTridents(Random random, LocalDifficulty localDifficulty, CallbackInfo ci) {
        setEquipmentDropChance(EquipmentSlot.MAINHAND, Survivality.CONFIG.tridentDropRate);
    }
}
