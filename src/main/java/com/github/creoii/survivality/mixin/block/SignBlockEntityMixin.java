package com.github.creoii.survivality.mixin.block;

import com.github.creoii.survivality.Survivality;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SignBlockEntity.class)
public class SignBlockEntityMixin {
    @Shadow private boolean editable;

    @Inject(method = "onActivate", at = @At("HEAD"))
    private void survivality_editSign(ServerPlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        if (Survivality.CONFIG.editableSigns) {
            ItemStack stack = player.getStackInHand(player.getActiveHand());
            if (stack.getItem() instanceof DyeItem || stack.isOf(Items.GLOW_INK_SAC) || stack.isOf(Items.INK_SAC)) {
                return;
            }
            editable = true;
            player.openEditSignScreen((SignBlockEntity) (Object) this);
        }
    }
}
