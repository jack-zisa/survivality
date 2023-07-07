package com.github.creoii.survivality;

import com.github.creoii.creolib.api.event.entity.EntitySpawnCallback;
import com.github.creoii.creolib.api.util.block.BlockUtil;
import com.github.creoii.creolib.api.util.entity.EntityUtil;
import com.github.creoii.creolib.api.util.fog.FogFunction;
import com.github.creoii.creolib.api.util.fog.FogModifier;
import com.github.creoii.creolib.api.util.item.ItemUtil;
import com.github.creoii.survivality.integration.ModMenuIntegration;
import com.github.creoii.survivality.util.SurvivalityTags;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.FogShape;
import net.minecraft.data.server.loottable.vanilla.VanillaBlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.WorldAccess;
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
		boolean harderBuddingAmethyst = true;
		boolean slotMachineGildedBlackstone = true;
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
			if (ModMenuIntegration.CONFIG.buddingAmethystStrength.intValue() < 0)
				harderBuddingAmethyst = false;
			if (ModMenuIntegration.CONFIG.slotMachineGildedBlackstone.booleanValue())
				slotMachineGildedBlackstone = false;
		}

		if (tntFuel)
			FuelRegistry.INSTANCE.add(Items.TNT, 50);

		if (stackedPotions) {
			ItemUtil.setMaxCount(Items.POTION, 16);
			ItemUtil.setMaxCount(Items.SPLASH_POTION, 16);
			ItemUtil.setMaxCount(Items.LINGERING_POTION, 16);
		}

		if (snowmenSpawn) {
			EntityUtil.setSpawnGroup(EntityType.SNOW_GOLEM, SpawnGroup.CREATURE);
			SpawnRestriction.RESTRICTIONS.remove(EntityType.SNOW_GOLEM);
			SpawnRestriction.register(EntityType.SNOW_GOLEM, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, Survivality::canSnowGolemSpawn);
			BiomeModifications.addSpawn(BiomeSelectors.tag(SurvivalityTags.SNOW_GOLEM_BIOMES), SpawnGroup.MISC, EntityType.SNOW_GOLEM, 100, 1, 1);
		}

		if (snowFog) {
			FogModifier.register(FogModifier.create(
					Survivality::snowFogPredicate,
					fogFunction -> 0f,
					fogFunction -> 96f,
					.0025f,
					FogShape.SPHERE
			));
		}

		if (harderBuddingAmethyst)
			BlockUtil.setHardness(Blocks.BUDDING_AMETHYST, 3f);

		if (slotMachineGildedBlackstone)
			registerSlotMachineGildedBlackstoneLootTable();
	}

	public static boolean canSnowGolemSpawn(EntityType<? extends MobEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
		BlockPos blockPos = pos.down();
		return world.getBlockState(blockPos).allowsSpawning(world, blockPos, type);
	}

	public static boolean snowFogPredicate(FogFunction fogFunction) {
		if (CONFIG_AVAILABLE && !ModMenuIntegration.CONFIG.snowFog.booleanValue()) return false;
		if (!fogFunction.world().isRaining()) return false;
		RegistryEntry<Biome> biomeEntry = fogFunction.biomeEntry();
		if (biomeEntry != null && biomeEntry.hasKeyAndValue()) {
			return biomeEntry.isIn(SurvivalityTags.SNOW_FOG_BIOMES);
		} return false;
	}

	private static void registerSlotMachineGildedBlackstoneLootTable() {
		LootTableEvents.REPLACE.register((resourceManager, lootManager, id, original, source) -> {
			if (id.equals(new Identifier("minecraft", "blocks/gilded_blackstone"))) {
				return lootManager.getLootTable(new Identifier(NAMESPACE, "blocks/slot_machine_gilded_blackstone"));
			}
			return original;
		});
	}
}
