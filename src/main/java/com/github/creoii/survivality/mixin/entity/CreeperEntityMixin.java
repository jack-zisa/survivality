package com.github.creoii.survivality.mixin.entity;

import com.github.creoii.survivality.Survivality;
import com.github.creoii.survivality.integration.ModMenuIntegration;
import net.minecraft.client.render.entity.feature.SkinOverlayOwner;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin extends HostileEntity implements SkinOverlayOwner {
    @Shadow protected abstract void explode();

    protected CreeperEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void onKilledBy(@Nullable LivingEntity adversary) {
        boolean value = !Survivality.CONFIG_AVAILABLE || !ModMenuIntegration.CONFIG.creeperChainExplosions.booleanValue();
        if (adversary == null) return;
        if (!value && adversary.getType() == EntityType.CREEPER) {
            explode();
            return;
        }
        super.onKilledBy(adversary);
    }
}
