package com.github.creoii.survivality.mixin.client;

import com.github.creoii.survivality.Survivality;
import com.github.creoii.survivality.integration.ModMenuIntegration;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
@Environment(EnvType.CLIENT)
public class GameRendererMixin {
    @Inject(method = "getNightVisionStrength", at = @At("HEAD"), cancellable = true)
    private static void great_big_world_applyIlluminating(LivingEntity entity, float tickDelta, CallbackInfoReturnable<Float> cir) {
        boolean value = Survivality.CONFIG_AVAILABLE ? ModMenuIntegration.CONFIG.betterNightVision.booleanValue() : true;
        if (!value) return;
        float value1 = Survivality.CONFIG_AVAILABLE ? ModMenuIntegration.CONFIG.maxNightVisionModifier.floatValue() : 5f;
        int d = entity.getStatusEffect(StatusEffects.NIGHT_VISION).getDuration();
        int amplifier = entity.getStatusEffect(StatusEffects.NIGHT_VISION).getAmplifier();
        if (amplifier <= 25) {
            float a = Math.min(.2f * (amplifier + 1), value1);
            cir.setReturnValue(d > 200 ? a : (a * .7f) + MathHelper.sin((float) ((d - tickDelta) * Math.PI * 0f)) * 0f);
        } else cir.setReturnValue(value1);
    }
}