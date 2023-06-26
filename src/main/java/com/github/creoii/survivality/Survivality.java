package com.github.creoii.survivality;

import com.github.creoii.creolib.api.util.fog.FogFunction;
import com.github.creoii.creolib.api.util.fog.FogModifier;
import com.github.creoii.survivality.integration.ModMenuIntegration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.render.FogShape;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;

public class Survivality implements ModInitializer {
	public static final String NAMESPACE = "survivality";
	public static final boolean CONFIG_AVAILABLE = FabricLoader.getInstance().isModLoaded("modmenu") && FabricLoader.getInstance().isModLoaded("yet_another_config_lib_v3");

	@Override
	public void onInitialize() {
		if (CONFIG_AVAILABLE && ModMenuIntegration.CONFIG != null) {
			ModMenuIntegration.CONFIG.preload();
		}

		FuelRegistry.INSTANCE.add(Items.TNT, 50);

		FogModifier.register(FogModifier.createColored(
				Survivality::snowFogPredicate,
				fogFunction -> 4f,
				fogFunction -> 96f,
				.01f,
				FogShape.SPHERE,
				new Vec3d(192d, 192d, 192d)
		));
	}

	public static boolean snowFogPredicate(FogFunction fogFunction) {
		if (CONFIG_AVAILABLE && !ModMenuIntegration.CONFIG.snowFog.booleanValue()) return false;
		RegistryEntry<Biome> biomeEntry = fogFunction.biomeEntry();
		if (biomeEntry != null && biomeEntry.hasKeyAndValue()) {
			return fogFunction.world().isRaining() && biomeEntry.isIn(BiomeTags.IS_MOUNTAIN) && fogFunction.world().getBiome(fogFunction.focusedEntity().getBlockPos()).value().isCold(fogFunction.focusedEntity().getBlockPos());
		} return false;
	}
}
