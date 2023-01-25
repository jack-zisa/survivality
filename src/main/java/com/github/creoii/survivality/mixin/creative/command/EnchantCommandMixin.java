package com.github.creoii.survivality.mixin.creative.command;

import com.github.creoii.survivality.Survivality;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.server.command.EnchantCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnchantCommand.class)
public class EnchantCommandMixin {
    @Redirect(method = "execute", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;getMaxLevel()I", ordinal = 0))
    private static int survivality_unboundEnchant(Enchantment instance) {
        if (Survivality.CONFIG.unboundEnchant) {
            return 127;
        } else return instance.getMaxLevel();
    }
}
