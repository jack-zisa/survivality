package com.github.creoii.survivality;

import com.github.creoii.creolib.api.util.block.BlockUtil;
import com.github.creoii.creolib.api.util.entity.EntityTypeUtil;
import com.github.creoii.creolib.api.util.fog.FogContext;
import com.github.creoii.creolib.api.util.fog.FogModifier;
import com.github.creoii.creolib.api.util.fog.FogModifiers;
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
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetPotionLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.WorldAccess;

public class Survivality implements ModInitializer {
	public static final String NAMESPACE = "survivality";
	public static final boolean CONFIG_AVAILABLE = FabricLoader.getInstance().isModLoaded("modmenu") && FabricLoader.getInstance().isModLoaded("yet_another_config_lib_v3");

	@Override
	public void onInitialize() {
		boolean explosiveFuel = true;
		boolean snowFog = true;
		boolean stackedPotions = true;
		boolean snowmenSpawn = true;
		boolean harderBuddingAmethyst = true;
		boolean slotMachineGildedBlackstone = true;
		boolean structurePotions = true;
		if (CONFIG_AVAILABLE && ModMenuIntegration.CONFIG != null) {
			ModMenuIntegration.CONFIG.preload();

			if (ModMenuIntegration.CONFIG.explosiveFuelExplosionChance.floatValue() == 0f)
				explosiveFuel = false;
			if (!ModMenuIntegration.CONFIG.snowFog.booleanValue())
				snowFog = false;
			if (!ModMenuIntegration.CONFIG.stackedPotions.booleanValue())
				stackedPotions = false;
			if (ModMenuIntegration.CONFIG.snowGolemSpawnWeight.intValue() < 0)
				snowmenSpawn = false;
			if (ModMenuIntegration.CONFIG.buddingAmethystStrength.intValue() < 0)
				harderBuddingAmethyst = false;
			if (!ModMenuIntegration.CONFIG.slotMachineGildedBlackstone.booleanValue())
				slotMachineGildedBlackstone = false;
			if (!ModMenuIntegration.CONFIG.structurePotions.booleanValue())
				structurePotions = false;
		}

		if (explosiveFuel) {
			FuelRegistry.INSTANCE.add(Items.TNT, 50);
			FuelRegistry.INSTANCE.add(Items.GUNPOWDER, 20);
		}

		if (stackedPotions) {
			ItemUtil.setMaxCount(Items.POTION, 16);
			ItemUtil.setMaxCount(Items.SPLASH_POTION, 16);
			ItemUtil.setMaxCount(Items.LINGERING_POTION, 16);
		}

		if (snowmenSpawn) {
			EntityTypeUtil.setSpawnGroup(EntityType.SNOW_GOLEM, SpawnGroup.CREATURE);
			SpawnRestriction.RESTRICTIONS.remove(EntityType.SNOW_GOLEM);
			SpawnRestriction.register(EntityType.SNOW_GOLEM, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, Survivality::canSnowGolemSpawn);
			BiomeModifications.addSpawn(BiomeSelectors.tag(SurvivalityTags.SNOW_GOLEM_BIOMES), SpawnGroup.CREATURE, EntityType.SNOW_GOLEM, ModMenuIntegration.CONFIG.snowGolemSpawnWeight.intValue(), 1, ModMenuIntegration.CONFIG.snowGolemMaxSpawnSize.intValue());
		}

		if (snowFog) {
			FogModifiers.register(FogModifier.InjectionPoint.POST, new FogModifier.Builder()
					.predicate(Survivality::snowFogPredicate)
					.fogStart(196f)
					.fogEnd(64f)
					.fogShape(FogShape.SPHERE)
					.densitySpeedSeconds(8)
					.operation(FogModifier.Operation.ADD)
					.color(15463935)
					.build()
			);
		}

		if (harderBuddingAmethyst)
			BlockUtil.setHardness(Blocks.BUDDING_AMETHYST, 4.5f);

		if (slotMachineGildedBlackstone)
			replaceGildedBlackstoneLootTable();

		if (structurePotions)
			modifyStructurePotionLootTable();
	}

	public static boolean canSnowGolemSpawn(EntityType<? extends MobEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
		BlockPos blockPos = pos.down();
		return world.getBlockState(blockPos).allowsSpawning(world, blockPos, type);
	}

	public static boolean snowFogPredicate(FogContext fogContext) {
		if (CONFIG_AVAILABLE && !ModMenuIntegration.CONFIG.snowFog.booleanValue()) return false;
		if (!fogContext.world().isRaining()) return false;
		return FogModifiers.testBiomeEntry(fogContext, biomeEntry -> {
			return biomeEntry.isIn(SurvivalityTags.SNOW_FOG_BIOMES);
		});
	}

	private static void replaceGildedBlackstoneLootTable() {
		LootTableEvents.REPLACE.register((resourceManager, lootManager, id, original, source) -> {
			if (id.equals(new Identifier("minecraft", "blocks/gilded_blackstone"))) {
				return lootManager.getLootTable(new Identifier(NAMESPACE, "blocks/slot_machine_gilded_blackstone"));
			}
			return original;
		});
	}

	private static void modifyStructurePotionLootTable() {
		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
			if (id.equals(LootTables.ABANDONED_MINESHAFT_CHEST)) {
				tableBuilder.pool(LootPool.builder()
						.rolls(UniformLootNumberProvider.create(1, 2))
						.with(ItemEntry.builder(Items.POTION)
								.weight(3)
								.apply(SetPotionLootFunction.builder(Potions.NIGHT_VISION))
								.build())
						.with(ItemEntry.builder(Items.POTION)
								.weight(1)
								.apply(SetPotionLootFunction.builder(Potions.LONG_NIGHT_VISION))
								.build())
						.conditionally(RandomChanceLootCondition.builder(.4f))
						.build());
			}
			if (id.equals(LootTables.SHIPWRECK_TREASURE_CHEST)) {
				tableBuilder.pool(LootPool.builder()
						.rolls(UniformLootNumberProvider.create(1, 2))
						.with(ItemEntry.builder(Items.POTION)
								.weight(3)
								.apply(SetPotionLootFunction.builder(Potions.WATER_BREATHING))
								.build())
						.with(ItemEntry.builder(Items.POTION)
								.weight(1)
								.apply(SetPotionLootFunction.builder(Potions.LONG_WATER_BREATHING))
								.build())
						.conditionally(RandomChanceLootCondition.builder(.8f))
						.build());
			}
			if (id.equals(LootTables.NETHER_BRIDGE_CHEST)) {
				tableBuilder.pool(LootPool.builder()
						.rolls(UniformLootNumberProvider.create(1, 2))
						.with(ItemEntry.builder(Items.POTION)
								.weight(3)
								.apply(SetPotionLootFunction.builder(Potions.FIRE_RESISTANCE))
								.build())
						.with(ItemEntry.builder(Items.POTION)
								.weight(1)
								.apply(SetPotionLootFunction.builder(Potions.LONG_FIRE_RESISTANCE))
								.build())
						.conditionally(RandomChanceLootCondition.builder(.4f))
						.build());
			}
			if (id.equals(LootTables.END_CITY_TREASURE_CHEST)) {
				tableBuilder.pool(LootPool.builder()
						.rolls(UniformLootNumberProvider.create(1, 2))
						.with(ItemEntry.builder(Items.POTION)
								.weight(3)
								.apply(SetPotionLootFunction.builder(Potions.SLOW_FALLING))
								.build())
						.with(ItemEntry.builder(Items.POTION)
								.weight(1)
								.apply(SetPotionLootFunction.builder(Potions.LONG_SLOW_FALLING))
								.build())
						.conditionally(RandomChanceLootCondition.builder(.4f))
						.build());
			}
		});
	}
}
