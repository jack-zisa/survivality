package com.github.creoii.survivality.mixin.misc;

import com.github.creoii.survivality.Survivality;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.MobSpawnerLogic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobSpawnerLogic.class)
public class MobSpawnerLogicMixin {
    @Inject(method = "writeNbt", at = @At("TAIL"))
    private void survivality_increaseRequiredPlayerRange(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> cir) {
        nbt.remove("RequiredPlayerRange");
        nbt.putShort("RequiredPlayerRange", (short) Survivality.CONFIG.spawnerRequiredPlayerRange.intValue());
        if (Survivality.CONFIG.unrestrictedSpawners.booleanValue()) {
            nbt.remove("MaxNearbyEntities");
            nbt.putShort("MaxNearbyEntities", (short) 0);
        }
    }
}
