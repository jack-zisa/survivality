package com.github.creoii.survivality;

import com.github.creoii.creolib.api.util.fog.FogFunction;
import com.github.creoii.creolib.api.util.fog.FogModifier;
import com.github.creoii.creolib.api.util.item.ItemUtil;
import com.github.creoii.survivality.integration.ModMenuIntegration;
import com.github.creoii.survivality.util.SurvivalityTags;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.render.FogShape;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;

public class Survivality implements ModInitializer {
	public static final String NAMESPACE = "survivality";
	public static final boolean CONFIG_AVAILABLE = FabricLoader.getInstance().isModLoaded("modmenu") && FabricLoader.getInstance().isModLoaded("yet_another_config_lib_v3");

	@Override
	public void onInitialize() {
		boolean tntFuel = true;
		boolean snowFog = true;
		boolean stackedPotions = true;
		boolean snowmenSpawn = true;
		if (CONFIG_AVAILABLE && ModMenuIntegration.CONFIG != null) {
			ModMenuIntegration.CONFIG.preload();

			if (ModMenuIntegration.CONFIG.tntFuelExplosionChance.floatValue() == 0f)
				tntFuel = false;
			if (!ModMenuIntegration.CONFIG.snowFog.booleanValue())
				snowFog = false;
			if (!ModMenuIntegration.CONFIG.stackedPotions.booleanValue())
				stackedPotions = false;
			if (ModMenuIntegration.CONFIG.snowGolemSpawnWeight.intValue() < 0)
				snowmenSpawn = false;
		}

		if (tntFuel)
			FuelRegistry.INSTANCE.add(Items.TNT, 50);

		if (stackedPotions) {
			ItemUtil.setMaxCount(Items.POTION, 16);
			ItemUtil.setMaxCount(Items.SPLASH_POTION, 16);
			ItemUtil.setMaxCount(Items.LINGERING_POTION, 16);
		}

		if (snowmenSpawn) {
			BiomeModifications.addSpawn(BiomeSelectors.tag(SurvivalityTags.SNOW_GOLEM_BIOMES), SpawnGroup.CREATURE, EntityType.SNOW_GOLEM, 10, 1, 1);
		}

		if (snowFog) {
			FogModifier.register(FogModifier.createColored(
					Survivality::snowFogPredicate,
					fogFunction -> 4f,
					fogFunction -> 96f,
					.01f,
					FogShape.SPHERE,
					new Vec3d(192d, 192d, 192d)
			));
		}
	}

	public static boolean snowFogPredicate(FogFunction fogFunction) {
		if (CONFIG_AVAILABLE && !ModMenuIntegration.CONFIG.snowFog.booleanValue()) return false;
		RegistryEntry<Biome> biomeEntry = fogFunction.biomeEntry();
		if (biomeEntry != null && biomeEntry.hasKeyAndValue()) {
			return fogFunction.world().isRaining() && biomeEntry.isIn(SurvivalityTags.SNOW_FOG_BIOMES);
		} return false;
	}
}
