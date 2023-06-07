package com.github.creoii.survivality.mixin.block;

import com.github.creoii.survivality.Survivality;
import com.github.creoii.survivality.util.SurvivalityTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.PointedDripstoneBlock;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PointedDripstoneBlock.class)
public class PointedDripstoneBlockMixin {
    @Inject(method = "onProjectileHit", at = @At("TAIL"), cancellable = true)
    private void survivality_unstableDripstone(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile, CallbackInfo ci) {
        if (!Survivality.CONFIG.unstableDripstone) return;
        BlockPos hitPos = hit.getBlockPos();
        if (!world.isClient && projectile.canModifyAt(world, hitPos) && projectile.getType().isIn(SurvivalityTags.BREAK_DRIPSTONE) && projectile.getVelocity().length() > .6d) {
            world.breakBlock(hitPos, true);
        }
        ci.cancel();
    }

    @Inject(method = "randomTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/PointedDripstoneBlock;tryGrow(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/random/Random;)V"), cancellable = true)
    private void survivality_dripstoneRandomlyFall(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (Survivality.CONFIG.unstableDripstone && random.nextInt(3) == 0 && !world.isClient) {
            world.breakBlock(pos, true);
            ci.cancel();
        }
    }
}
