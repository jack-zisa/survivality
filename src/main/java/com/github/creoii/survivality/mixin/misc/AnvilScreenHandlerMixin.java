package com.github.creoii.survivality.mixin.misc;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.Property;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerMixin {
    @Shadow @Final private Property levelCost;
    private static final boolean NO_EXPENSIVE_LOADED = FabricLoader.getInstance().isModLoaded("noexpensive");
    private static int levelPreMaxOut;

    @Inject(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/Property;set(I)V", ordinal = 6))
    private void survivality_getMaxPreOut(CallbackInfo ci) {
        if (NO_EXPENSIVE_LOADED) return;
        levelPreMaxOut = levelCost.get();
    }

    @Inject(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/Property;set(I)V", shift = At.Shift.AFTER, ordinal = 6))
    private void survivality_dontMaxAt39(CallbackInfo ci) {
        if (NO_EXPENSIVE_LOADED) return;
        levelCost.set(levelPreMaxOut);
    }

    @Redirect(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/Property;get()I", ordinal = 1))
    private int survivality_dontMaxAt39(Property instance) {
        if (NO_EXPENSIVE_LOADED) return instance.get();
        return levelPreMaxOut;
    }
}
