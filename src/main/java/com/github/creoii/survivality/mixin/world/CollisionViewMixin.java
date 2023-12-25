package com.github.creoii.survivality.mixin.world;

import com.github.creoii.survivality.Survivality;
import com.github.creoii.survivality.integration.ModMenuIntegration;
import com.github.creoii.survivality.util.EntityBlockCollisionSpliterator;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.CollisionView;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CollisionView.class)
public interface CollisionViewMixin {
    @Inject(method = "getBlockCollisions", at = @At("HEAD"), cancellable = true)
    private void survivality_boatsIgnoreWaterlilies(@Nullable Entity entity, Box box, CallbackInfoReturnable<Iterable<VoxelShape>> cir) {
        boolean value = !Survivality.CONFIG_AVAILABLE || ModMenuIntegration.CONFIG.boatsIgnoreWaterlilies.booleanValue();
        if (entity instanceof BoatEntity && value) {
            cir.setReturnValue(() -> new EntityBlockCollisionSpliterator((CollisionView) this, entity, box, RegistryEntryList.of(RegistryEntry.of(Blocks.LILY_PAD))));
        }
    }
}