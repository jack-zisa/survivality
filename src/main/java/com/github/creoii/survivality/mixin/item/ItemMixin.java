package com.github.creoii.survivality.mixin.item;

import com.github.creoii.survivality.Survivality;
import com.github.creoii.survivality.integration.ModMenuIntegration;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "getMaxUseTime", at = @At(value = "RETURN", ordinal = 0), cancellable = true)
    private void custom_applyFoodEatingSpeeds(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        boolean value = Survivality.CONFIG_AVAILABLE ? ModMenuIntegration.CONFIG.moreSnacks.booleanValue() : true;
        if (!value) return;
        if (stack.isOf(Items.GLOW_BERRIES) || stack.isOf(Items.SWEET_BERRIES) || stack.isOf(Items.COOKIE)) {
            cir.setReturnValue(16);
        }
    }
}
