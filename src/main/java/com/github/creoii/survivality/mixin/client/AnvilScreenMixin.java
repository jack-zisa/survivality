package com.github.creoii.survivality.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.client.gui.screen.ingame.ForgingScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(AnvilScreen.class)
@Environment(EnvType.CLIENT)
public abstract class AnvilScreenMixin extends ForgingScreen<AnvilScreenHandler> {
    public AnvilScreenMixin(AnvilScreenHandler handler, PlayerInventory playerInventory, Text title, Identifier texture) {
        super(handler, playerInventory, title, texture);
    }

    @Inject(method = "drawForeground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;drawWithShadow(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/text/Text;FFI)I"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void survivality_neverTooExpensiveAnvils(MatrixStack matrices, int mouseX, int mouseY, CallbackInfo ci, int i, int j, Text text, int k, int l) {
        if (text == AnvilScreen.TOO_EXPENSIVE_TEXT) {
            text = Text.translatable("container.repair.cost", i);
            k = backgroundWidth - 8 - textRenderer.getWidth(text) - 2;
            fill(matrices, k - 2, 67, backgroundWidth - 8, 79, 1325400064);
            textRenderer.drawWithShadow(matrices, text, (float)k, 69f, j);
            ci.cancel();
        }
    }
}
