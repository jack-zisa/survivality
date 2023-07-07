package com.github.creoii.survivality.mixin.item;

import com.github.creoii.survivality.Survivality;
import com.github.creoii.survivality.integration.ModMenuIntegration;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SignItem;
import net.minecraft.item.VerticallyAttachableBlockItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SignItem.class)
public abstract class SignItemMixin extends VerticallyAttachableBlockItem {
    public SignItemMixin(Block standingBlock, Block wallBlock, Settings settings, Direction verticalAttachmentDirection) {
        super(standingBlock, wallBlock, settings, verticalAttachmentDirection);
    }

    @Inject(method = "postPlacement", at = @At("HEAD"), cancellable = true)
    private void survivality_stopInitialSignEdit(BlockPos pos, World world, PlayerEntity player, ItemStack stack, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        boolean value = Survivality.CONFIG_AVAILABLE ? ModMenuIntegration.CONFIG.shovelableSnow.booleanValue() : true;
        if (value)
            cir.setReturnValue(super.postPlacement(pos, world, player, stack, state));
    }
}
