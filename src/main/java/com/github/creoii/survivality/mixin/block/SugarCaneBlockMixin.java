package com.github.creoii.survivality.mixin.block;

import com.github.creoii.survivality.Survivality;
import net.minecraft.block.SugarCaneBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(SugarCaneBlock.class)
public class SugarCaneBlockMixin {
    @ModifyConstant(method = "randomTick", constant = @Constant(intValue = 3))
    private int survivality_increaseCactusHeight(int constant) {
        return Survivality.CONFIG.sugarCaneGrowHeight.intValue();
    }
}
