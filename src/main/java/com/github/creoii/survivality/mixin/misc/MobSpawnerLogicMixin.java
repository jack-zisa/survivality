package com.github.creoii.survivality.mixin.misc;

import com.github.creoii.survivality.Survivality;
import com.github.creoii.survivality.integration.ModMenuIntegration;
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
        int value = Survivality.CONFIG_AVAILABLE ? ModMenuIntegration.CONFIG.spawnerRequiredPlayerRange.intValue() : 32;
        nbt.putShort("RequiredPlayerRange", (short) value);
        boolean value1 = Survivality.CONFIG_AVAILABLE ? ModMenuIntegration.CONFIG.unrestrictedSpawners.booleanValue() : true;
        if (value1) {
            nbt.remove("MaxNearbyEntities");
            nbt.putShort("MaxNearbyEntities", (short) 0);
        }
    }
}
