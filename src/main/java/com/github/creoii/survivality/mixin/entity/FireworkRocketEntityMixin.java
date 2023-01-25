package com.github.creoii.survivality.mixin.entity;

import com.github.creoii.survivality.Survivality;
import com.github.creoii.survivality.util.SurvivalityTags;
import net.minecraft.block.AbstractRailBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FireworkRocketEntity.class)
public class FireworkRocketEntityMixin {
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isFallFlying()Z"))
    private boolean survivality_rocketBoosting(LivingEntity instance) {
        if (!Survivality.CONFIG.rocketBoosting) return instance.isFallFlying();
        Entity vehicle = instance.getVehicle();
        if ((vehicle != null && vehicle.getType().isIn(SurvivalityTags.EntityTypes.BOOSTABLE_VEHICLES))) {
            if (vehicle instanceof AbstractMinecartEntity minecartEntity) {
                return AbstractRailBlock.isRail(minecartEntity.getBlockStateAtPos());
            } else if (vehicle instanceof BoatEntity boatEntity) {
                return boatEntity.location == BoatEntity.Location.IN_WATER;
            }
        }
        return instance.isFallFlying();
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setVelocity(Lnet/minecraft/util/math/Vec3d;)V"))
    private void survivality_fixVehicleBoostVelocity(LivingEntity instance, Vec3d vec3d) {
        double velocityX = vec3d.x * .1d + (vec3d.x * 1.5d - vec3d.x) * .5d;
        double velocityZ = vec3d.z * .1d + (vec3d.z * 1.5d - vec3d.z) * .5d;
        Entity vehicle = instance.getVehicle();
        if (vehicle != null && vehicle.getType().isIn(SurvivalityTags.EntityTypes.BOOSTABLE_VEHICLES)) {
            Vec3d vehicleVelocity = new Vec3d(vec3d.x, 0d, vec3d.z);
            vehicle.setVelocity(vehicleVelocity.add(velocityX, 0d, velocityZ));
        } else instance.setVelocity(vec3d.add(velocityX, vec3d.y * .1d + (vec3d.y * 1.5d - vec3d.y) * .5d, velocityZ));
    }
}
