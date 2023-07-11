package com.github.creoii.survivality.mixin.block;

import com.github.creoii.creolib.api.block.LightningStrikeable;
import com.github.creoii.survivality.Survivality;
import com.github.creoii.survivality.integration.ModMenuIntegration;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.SandBlock;
import net.minecraft.entity.LightningEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SandBlock.class)
public abstract class SandBlockMixin extends FallingBlock implements LightningStrikeable {
    public SandBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public void onStruckByLightning(ServerWorld world, BlockPos pos, LightningEntity lightning) {
        boolean value = Survivality.CONFIG_AVAILABLE ? ModMenuIntegration.CONFIG.lightningSmeltsSand.booleanValue() : true;
        if (value)
            world.setBlockState(pos, Blocks.GLASS.getDefaultState());
    }
}
