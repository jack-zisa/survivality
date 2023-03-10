package com.github.creoii.survivality.mixin.item;

import com.github.creoii.survivality.Survivality;
import com.github.creoii.survivality.util.SurvivalityTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FireworkRocketItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FireworkRocketItem.class)
public class FireworkRocketItemMixin {
    @Redirect(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;isFallFlying()Z"))
    private boolean survivality_useFireworksOnVehicles(PlayerEntity instance) {
        if (!Survivality.CONFIG.rocketBoosting) return instance.isFallFlying();
        return instance.isFallFlying() || (instance.getVehicle() != null && instance.getVehicle().getType().isIn(SurvivalityTags.EntityTypes.BOOSTABLE_VEHICLES));
    }
}
