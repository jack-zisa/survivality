package com.github.creoii.survivality.mixin.entity;

import com.github.creoii.survivality.Survivality;
import com.github.creoii.survivality.integration.ModMenuIntegration;
import com.github.creoii.survivality.util.ChainExplodable;
import net.minecraft.client.render.entity.feature.SkinOverlayOwner;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin extends HostileEntity implements SkinOverlayOwner, ChainExplodable {
    @Shadow protected abstract void explode();

    @Shadow public abstract void setFuseSpeed(int fuseSpeed);

    @Shadow public abstract void ignite();

    @Shadow private int currentFuseTime;
    private boolean hasChainExploded;

    protected CreeperEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        boolean value = !Survivality.CONFIG_AVAILABLE || ModMenuIntegration.CONFIG.creeperChainExplosionFuseTime.intValue() <= -1;
        if (!value && !isDead() && source.isIn(DamageTypeTags.IS_EXPLOSION)) {
            if (!hasChainExploded()) {
                setFuseSpeed(1);
                currentFuseTime = 20;
                ignite();
                return true;
            }
        }
        return super.damage(source, amount);
    }

    @Inject(method = "explode", at = @At("HEAD"))
    private void survivality_setChainExploded(CallbackInfo ci) {
        setHasChainExploded(true);
    }

    @Override
    public void setHasChainExploded(boolean hasChainExploded) {
        this.hasChainExploded = hasChainExploded;
    }

    @Override
    public boolean hasChainExploded() {
        return hasChainExploded;
    }
}
