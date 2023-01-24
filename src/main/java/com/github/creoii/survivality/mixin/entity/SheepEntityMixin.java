package com.github.creoii.survivality.mixin.entity;

import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SheepEntity.class)
public class SheepEntityMixin {
    private static final Random RANDOM = Random.createLocal();

    @Inject(method = "generateDefaultColor", at = @At("HEAD"), cancellable = true)
    private static void survivality_randomizeSheepColor(Random random, CallbackInfoReturnable<DyeColor> cir) {
        if (random.nextInt(200) == 0) {
            int i = RANDOM.nextInt(11);
            switch (i) {
                case 0 -> cir.setReturnValue(DyeColor.BLUE);
                case 1 -> cir.setReturnValue(DyeColor.LIGHT_BLUE);
                case 2 -> cir.setReturnValue(DyeColor.CYAN);
                case 3 -> cir.setReturnValue(DyeColor.GREEN);
                case 4 -> cir.setReturnValue(DyeColor.LIME);
                case 5 -> cir.setReturnValue(DyeColor.YELLOW);
                case 6 -> cir.setReturnValue(DyeColor.RED);
                case 7 -> cir.setReturnValue(DyeColor.ORANGE);
                case 8 -> cir.setReturnValue(DyeColor.MAGENTA);
                case 9 -> cir.setReturnValue(DyeColor.PURPLE);
            }
        }
    }
}
