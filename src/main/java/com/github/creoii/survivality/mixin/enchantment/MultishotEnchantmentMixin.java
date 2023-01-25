package com.github.creoii.survivality.mixin.enchantment;

import com.github.creoii.survivality.Survivality;
import net.minecraft.enchantment.MultishotEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MultishotEnchantment.class)
public class MultishotEnchantmentMixin {
    @Inject(method = "getMaxLevel", at = @At("HEAD"), cancellable = true)
    private void survivality_moreMultishot(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(Survivality.CONFIG.maxMultishotLevel);
    }
}
