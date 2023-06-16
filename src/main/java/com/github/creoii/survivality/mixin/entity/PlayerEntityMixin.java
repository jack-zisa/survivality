package com.github.creoii.survivality.mixin.entity;

import com.github.creoii.survivality.Survivality;
import com.github.creoii.survivality.integration.ModMenuIntegration;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Shadow public int experienceLevel;

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "getXpToDrop", at = @At("HEAD"), cancellable = true)
    private void survivality_dropMoreXp(CallbackInfoReturnable<Integer> cir) {
        if (!getWorld().getGameRules().getBoolean(GameRules.KEEP_INVENTORY) && !isSpectator()) {
            double value = Survivality.CONFIG_AVAILABLE ? ModMenuIntegration.CONFIG.playerXpModifier.doubleValue() : 12d;
            cir.setReturnValue((int)(experienceLevel * value));
        }
    }
}
