package com.github.creoii.survivality.mixin.item;

import com.github.creoii.survivality.Survivality;
import com.github.creoii.survivality.integration.ModMenuIntegration;
import net.minecraft.block.BlockState;
import net.minecraft.block.SnowBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ShovelItem;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ShovelItem.class)
public class ShovelItemMixin {
    @Inject(method = "useOnBlock", at = @At(value = "INVOKE", target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void survivality_depleteSnow(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir, World world, BlockPos blockPos, BlockState blockState, PlayerEntity playerEntity) {
        boolean value = Survivality.CONFIG_AVAILABLE ? ModMenuIntegration.CONFIG.shovelableSnow.booleanValue() : true;
        if (!value) return;
        if (blockState.getBlock() instanceof SnowBlock && context.getSide() == Direction.UP) {
            if (!world.isClient) {
                int layers = blockState.get(Properties.LAYERS) - 1;
                if (layers > 0) {
                    BlockState newState = blockState.with(Properties.LAYERS, layers);
                    world.setBlockState(blockPos, newState, 11);
                    world.emitGameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Emitter.of(playerEntity, newState));
                    if (playerEntity != null) {
                        context.getStack().damage(1, playerEntity, p -> {
                            p.sendToolBreakStatus(context.getHand());
                        });
                    }
                } else world.removeBlock(blockPos, false);
            }
            cir.setReturnValue(ActionResult.success(world.isClient));
        }
    }
}
