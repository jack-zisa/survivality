package com.github.creoii.survivality.mixin.entity;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SlimeEntity.class)
public abstract class SlimeEntityMixin {
    @Shadow public abstract void setSize(int size, boolean heal);

    @Inject(method = "initialize", at = @At("RETURN"))
    private void survivality_biggerSlime(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, NbtCompound entityNbt, CallbackInfoReturnable<EntityData> cir) {
        int i = world.getRandom().nextInt(4);
        if (i < 2 && world.getRandom().nextFloat() < .5f * difficulty.getClampedLocalDifficulty()) {
            ++i;
        }
        setSize(1 << i, true);
    }
}
