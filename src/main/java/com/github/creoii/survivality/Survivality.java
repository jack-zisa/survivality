package com.github.creoii.survivality;

import com.github.creoii.survivality.integration.ModMenuIntegration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Items;

/**
 *  To Test:
 *
 *  In Progress:
 *
 *  To Do:
 */
public class Survivality implements ModInitializer {
	public static final String NAMESPACE = "survivality";
	public static final boolean CONFIG_AVAILABLE = FabricLoader.getInstance().isModLoaded("modmenu") && FabricLoader.getInstance().isModLoaded("yet_another_config_lib_v3");

	@Override
	public void onInitialize() {
		if (CONFIG_AVAILABLE && ModMenuIntegration.CONFIG != null) {
			ModMenuIntegration.CONFIG.preload();
		}

		FuelRegistry.INSTANCE.add(Items.TNT, 50);
	}
}
