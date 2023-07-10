package com.github.creoii.survivality.mixin.block;

import net.minecraft.block.FallingBlock;
import net.minecraft.block.SandBlock;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SandBlock.class)
public abstract class SandBlockMixin extends FallingBlock {
    public SandBlockMixin(Settings settings) {
        super(settings);
    }
}
