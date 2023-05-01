package com.github.creoii.survivality;

import com.github.creoii.survivality.integration.SurvivalityConfig;
import com.github.creoii.survivality.util.SurvivalityUtils;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;

/**
 *  To Test:
 *
 *  In Progress:
 *  - Wider Spawner Player Range
 *  - Boats Ignore Lily Pads
 *
 *  To Do:
 */
public class Survivality implements ModInitializer, ClientModInitializer {
	public static final String NAMESPACE = "survivality";
	public static SurvivalityConfig CONFIG = new SurvivalityConfig();

	@Override
	public void onInitialize() {
		AutoConfig.register(SurvivalityConfig.class, JanksonConfigSerializer::new);
		CONFIG = AutoConfig.getConfigHolder(SurvivalityConfig.class).getConfig();
	}

	@Override
	public void onInitializeClient() {
		if (Survivality.CONFIG.snowyLeaves) {
			ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
				if (state != null) {
					if (state.get(SurvivalityUtils.SNOWY)) {
						return 16777215;
					}

					if (state.getBlock() == Blocks.SPRUCE_LEAVES) {
						return FoliageColors.getSpruceColor();
					}

					if (state.getBlock() == Blocks.BIRCH_LEAVES) {
						return FoliageColors.getBirchColor();
					}

					if (state.getBlock() == Blocks.MANGROVE_LEAVES) {
						return FoliageColors.getMangroveColor();
					}

					if (world != null && pos != null) {
						return BiomeColors.getFoliageColor(world, pos);
					}
				}

				return FoliageColors.getDefaultColor();
			}, Blocks.OAK_LEAVES, Blocks.SPRUCE_LEAVES, Blocks.BIRCH_LEAVES, Blocks.JUNGLE_LEAVES, Blocks.ACACIA_LEAVES, Blocks.DARK_OAK_LEAVES, Blocks.MANGROVE_LEAVES);
		}
	}
}
