package com.github.creoii.survivality.mixin.entity;

import com.github.creoii.survivality.Survivality;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractMinecartEntity.class)
public abstract class AbstractMinecraftEntityMixin extends Entity {
    public AbstractMinecraftEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "getMaxSpeed", at = @At("HEAD"), cancellable = true)
    private void survivality_increaseMaxSpeed(CallbackInfoReturnable<Double> cir) {
        cir.setReturnValue(isTouchingWater() ? Survivality.CONFIG.maxMinecartSpeed.doubleValue() / 2d : Survivality.CONFIG.maxMinecartSpeed.doubleValue());
    }
}
