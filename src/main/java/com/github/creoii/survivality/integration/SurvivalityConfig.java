package com.github.creoii.survivality.integration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.DoubleSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.FloatSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.config.ConfigEntry;
import net.minecraft.text.Text;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.apache.commons.lang3.mutable.MutableInt;

import java.nio.file.Files;
import java.nio.file.Path;

public class SurvivalityConfig {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Path configPath = Path.of("config", "survivality.json");
    private boolean preloaded = false;

    @ConfigEntry
    public MutableBoolean safeCactus = new MutableBoolean(true);

    @ConfigEntry
    public MutableBoolean featheryFallingBoots = new MutableBoolean(true);

    @ConfigEntry
    public MutableBoolean unstableDripstone = new MutableBoolean(true);

    @ConfigEntry
    public MutableBoolean betterNightVision = new MutableBoolean(true);

    @ConfigEntry
    public MutableFloat maxNightVisionModifier = new MutableFloat(5f);

    @ConfigEntry
    public MutableInt maxMultishotLevel = new MutableInt(4);

    @ConfigEntry
    public MutableInt maxMinecartSpeed = new MutableInt(16);

    @ConfigEntry
    public MutableFloat tridentDropRate = new MutableFloat(.15f);

    @ConfigEntry
    public MutableFloat eyeOfEnderBreakChance = new MutableFloat(0f);

    @ConfigEntry
    public MutableBoolean zombieHorseTransmutation = new MutableBoolean(true);

    @ConfigEntry
    public MutableBoolean rideableZombieHorses = new MutableBoolean(true);

    @ConfigEntry
    public MutableDouble playerXpModifier = new MutableDouble(12d);

    @ConfigEntry
    public MutableBoolean colorfulSheep = new MutableBoolean(true);

    @ConfigEntry
    public MutableInt maxSlimeSize = new MutableInt(4);

    @ConfigEntry
    public MutableInt maxMagmaCubeSize = new MutableInt(4);

    @ConfigEntry
    public MutableBoolean moreSnacks = new MutableBoolean(true);

    @ConfigEntry
    public MutableBoolean shovelableSnow = new MutableBoolean(true);

    @ConfigEntry
    public MutableBoolean variantSpawners = new MutableBoolean(true);

    @ConfigEntry
    public MutableBoolean randomWorldStartTime = new MutableBoolean(true);

    @ConfigEntry
    public MutableBoolean randomWorldStartWeather = new MutableBoolean(true);

    @ConfigEntry
    public MutableBoolean randomWorldStartSpawnPos = new MutableBoolean(true);

    @ConfigEntry
    public MutableBoolean randomWorldStartBiome = new MutableBoolean(true);

    @ConfigEntry
    public MutableBoolean rocketBoosting = new MutableBoolean(true);

    @ConfigEntry
    public MutableInt spawnerRequiredPlayerRange = new MutableInt(32);

    @ConfigEntry
    public MutableBoolean unrestrictedSpawners = new MutableBoolean(true);

    @ConfigEntry
    public MutableFloat polarBearCavalryChance = new MutableFloat(.01f);

    @ConfigEntry
    public MutableBoolean boatsIgnoreWaterlilies = new MutableBoolean(true);

    @ConfigEntry
    public MutableBoolean unboundEnchant = new MutableBoolean(true);

    @ConfigEntry
    public MutableInt cactusGrowHeight = new MutableInt(5);

    @ConfigEntry
    public MutableInt sugarCaneGrowHeight = new MutableInt(5);

    @ConfigEntry
    public MutableBoolean fertilizableDirt = new MutableBoolean(true);

    @ConfigEntry
    public MutableFloat explosiveFuelExplosionChance = new MutableFloat(.25f);

    @ConfigEntry
    public MutableBoolean snowFog = new MutableBoolean(true);

    @ConfigEntry
    public MutableBoolean stackedPotions = new MutableBoolean(true);

    @ConfigEntry
    public MutableInt snowGolemSpawnWeight = new MutableInt(10);

    @ConfigEntry
    public MutableInt snowGolemMaxSpawnSize = new MutableInt(2);

    @ConfigEntry
    public MutableInt creeperChainExplosionFuseTime = new MutableInt(20);

    @ConfigEntry
    public MutableBoolean noInitialSignEdit = new MutableBoolean(true);

    @ConfigEntry
    public MutableFloat buddingAmethystStrength = new MutableFloat(4.5f);

    @ConfigEntry
    public MutableBoolean slotMachineGildedBlackstone = new MutableBoolean(true);

    @ConfigEntry
    public MutableBoolean structurePotions = new MutableBoolean(true);

    @ConfigEntry
    public MutableBoolean cactusHurtsHands = new MutableBoolean(true);

    @ConfigEntry
    public MutableBoolean lightningSmeltsSand = new MutableBoolean(true);

    public YetAnotherConfigLib getYACL() {
        YetAnotherConfigLib config = YetAnotherConfigLib.createBuilder()
                .title(Text.translatable("text.survivality.config.title"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("text.survivality.config.general"))
                        .option(createBooleanOption(
                                Text.translatable("text.survivality.config.option.safeCactus"),
                                Text.translatable("text.survivality.config.option.safeCactus.@Tooltip"),
                                safeCactus, true))
                        .option(createBooleanOption(
                                Text.translatable("text.survivality.config.option.featheryFallingBoots"),
                                Text.translatable("text.survivality.config.option.featheryFallingBoots.@Tooltip"),
                                featheryFallingBoots, true))
                        .option(createBooleanOption(
                                Text.translatable("text.survivality.config.option.unstableDripstone"),
                                Text.translatable("text.survivality.config.option.unstableDripstone.@Tooltip"),
                                unstableDripstone, true))
                        .option(createBooleanOption(
                                Text.translatable("text.survivality.config.option.betterNightVision"),
                                Text.translatable("text.survivality.config.option.betterNightVision.@Tooltip"),
                                betterNightVision, true))
                        .option(createFloatOption(
                                Text.translatable("text.survivality.config.option.maxNightVisionModifier"),
                                Text.translatable("text.survivality.config.option.maxNightVisionModifier.@Tooltip"),
                                maxNightVisionModifier, 5f, 0, 25f, .5f))
                        .option(createIntegerOption(
                                Text.translatable("text.survivality.config.option.maxMultishotLevel"),
                                Text.translatable("text.survivality.config.option.maxMultishotLevel.@Tooltip"),
                                maxMultishotLevel, 4, 1, 7, 1))
                        .option(createIntegerOption(
                                Text.translatable("text.survivality.config.option.maxMinecartSpeed"),
                                Text.translatable("text.survivality.config.option.maxMinecartSpeed.@Tooltip"),
                                maxMinecartSpeed, 16, 0, 32, 2))
                        .option(createFloatOption(
                                Text.translatable("text.survivality.config.option.tridentDropRate"),
                                Text.translatable("text.survivality.config.option.tridentDropRate.@Tooltip"),
                                tridentDropRate, .15f, 0f, 1f, .05f))
                        .option(createFloatOption(
                                Text.translatable("text.survivality.config.option.eyeOfEnderBreakChance"),
                                Text.translatable("text.survivality.config.option.eyeOfEnderBreakChance.@Tooltip"),
                                eyeOfEnderBreakChance, 0f, 0f, 1f, .05f))
                        .option(createBooleanOption(
                                Text.translatable("text.survivality.config.option.zombieHorseTransmutation"),
                                Text.translatable("text.survivality.config.option.zombieHorseTransmutation.@Tooltip"),
                                zombieHorseTransmutation, true))
                        .option(createBooleanOption(
                                Text.translatable("text.survivality.config.option.rideableZombieHorses"),
                                Text.translatable("text.survivality.config.option.rideableZombieHorses.@Tooltip"),
                                rideableZombieHorses, true))
                        .option(createDoubleOption(
                                Text.translatable("text.survivality.config.option.playerXpModifier"),
                                Text.translatable("text.survivality.config.option.playerXpModifier.@Tooltip"),
                                playerXpModifier, 12d, 0d, 120d, 2d))
                        .option(createBooleanOption(
                                Text.translatable("text.survivality.config.option.colorfulSheep"),
                                Text.translatable("text.survivality.config.option.colorfulSheep.@Tooltip"),
                                colorfulSheep, true))
                        .option(createIntegerOption(
                                Text.translatable("text.survivality.config.option.maxSlimeSize"),
                                Text.translatable("text.survivality.config.option.maxSlimeSize.@Tooltip"),
                                maxSlimeSize, 4, 0, 16, 1))
                        .option(createIntegerOption(
                                Text.translatable("text.survivality.config.option.maxMagmaCubeSize"),
                                Text.translatable("text.survivality.config.option.maxMagmaCubeSize.@Tooltip"),
                                maxMagmaCubeSize, 4, 0, 16, 1))
                        .option(createBooleanOption(
                                Text.translatable("text.survivality.config.option.moreSnacks"),
                                Text.translatable("text.survivality.config.option.moreSnacks.@Tooltip"),
                                moreSnacks, true))
                        .option(createBooleanOption(
                                Text.translatable("text.survivality.config.option.shovelableSnow"),
                                Text.translatable("text.survivality.config.option.shovelableSnow.@Tooltip"),
                                shovelableSnow, true))
                        .option(createBooleanOption(
                                Text.translatable("text.survivality.config.option.variantSpawners"),
                                Text.translatable("text.survivality.config.option.variantSpawners.@Tooltip"),
                                variantSpawners, true))
                        .option(createBooleanOption(
                                Text.translatable("text.survivality.config.option.randomWorldStartTime"),
                                Text.translatable("text.survivality.config.option.randomWorldStartTime.@Tooltip"),
                                randomWorldStartTime, true))
                        .option(createBooleanOption(
                                Text.translatable("text.survivality.config.option.randomWorldStartWeather"),
                                Text.translatable("text.survivality.config.option.randomWorldStartWeather.@Tooltip"),
                                randomWorldStartWeather, true))
                        .option(createBooleanOption(
                                Text.translatable("text.survivality.config.option.randomWorldStartSpawnPos"),
                                Text.translatable("text.survivality.config.option.randomWorldStartSpawnPos.@Tooltip"),
                                randomWorldStartSpawnPos, true))
                        .option(createBooleanOption(
                                Text.translatable("text.survivality.config.option.randomWorldStartBiome"),
                                Text.translatable("text.survivality.config.option.randomWorldStartBiome.@Tooltip"),
                                randomWorldStartBiome, true))
                        .option(createBooleanOption(
                                Text.translatable("text.survivality.config.option.rocketBoosting"),
                                Text.translatable("text.survivality.config.option.rocketBoosting.@Tooltip"),
                                rocketBoosting, true))
                        .option(createIntegerOption(
                                Text.translatable("text.survivality.config.option.spawnerRequiredPlayerRange"),
                                Text.translatable("text.survivality.config.option.spawnerRequiredPlayerRange.@Tooltip"),
                                spawnerRequiredPlayerRange, 32, 0, 128, 8))
                        .option(createBooleanOption(
                                Text.translatable("text.survivality.config.option.unrestrictedSpawners"),
                                Text.translatable("text.survivality.config.option.unrestrictedSpawners.@Tooltip"),
                                unrestrictedSpawners, true))
                        .option(createFloatOption(
                                Text.translatable("text.survivality.config.option.polarBearCavalryChance"),
                                Text.translatable("text.survivality.config.option.polarBearCavalryChance.@Tooltip"),
                                polarBearCavalryChance, .01f, 0f, 1f, .05f))
                        .option(createBooleanOption(
                                Text.translatable("text.survivality.config.option.boatsIgnoreWaterlilies"),
                                Text.translatable("text.survivality.config.option.boatsIgnoreWaterlilies.@Tooltip"),
                                boatsIgnoreWaterlilies, true))
                        .option(createIntegerOption(
                                Text.translatable("text.survivality.config.option.cactusGrowHeight"),
                                Text.translatable("text.survivality.config.option.cactusGrowHeight.@Tooltip"),
                                cactusGrowHeight, 5, 0, 384, 1))
                        .option(createIntegerOption(
                                Text.translatable("text.survivality.config.option.sugarCaneGrowHeight"),
                                Text.translatable("text.survivality.config.option.sugarCaneGrowHeight.@Tooltip"),
                                sugarCaneGrowHeight, 5, 0, 384, 1))
                        .option(createBooleanOption(
                                Text.translatable("text.survivality.config.option.fertilizableDirt"),
                                Text.translatable("text.survivality.config.option.fertilizableDirt.@Tooltip"),
                                fertilizableDirt, true))
                        .option(createFloatOption(
                                Text.translatable("text.survivality.config.option.explosiveFuelExplosionChance"),
                                Text.translatable("text.survivality.config.option.explosiveFuelExplosionChance.@Tooltip"),
                                explosiveFuelExplosionChance, .25f, 0f, 1f, .05f))
                        .option(createBooleanOption(
                                Text.translatable("text.survivality.config.option.snowFog"),
                                Text.translatable("text.survivality.config.option.snowFog.@Tooltip"),
                                snowFog, true))
                        .option(createBooleanOption(
                                Text.translatable("text.survivality.config.option.stackedPotions"),
                                Text.translatable("text.survivality.config.option.stackedPotions.@Tooltip"),
                                stackedPotions, true))
                        .option(createIntegerOption(
                                Text.translatable("text.survivality.config.option.snowGolemSpawnWeight"),
                                Text.translatable("text.survivality.config.option.snowGolemSpawnWeight.@Tooltip"),
                                snowGolemSpawnWeight, 10, -1, 100, 1))
                        .option(createIntegerOption(
                                Text.translatable("text.survivality.config.option.snowGolemMaxSpawnSize"),
                                Text.translatable("text.survivality.config.option.snowGolemMaxSpawnSize.@Tooltip"),
                                snowGolemMaxSpawnSize, 2, 1, 10, 1))
                        .option(createIntegerOption(
                                Text.translatable("text.survivality.config.option.creeperChainExplosionFuseTime"),
                                Text.translatable("text.survivality.config.option.creeperChainExplosionFuseTime.@Tooltip"),
                                creeperChainExplosionFuseTime, 20, -1, 30, 1))
                        .option(createBooleanOption(
                                Text.translatable("text.survivality.config.option.noInitialSignEdit"),
                                Text.translatable("text.survivality.config.option.noInitialSignEdit.@Tooltip"),
                                noInitialSignEdit, true))
                        .option(createFloatOption(
                                Text.translatable("text.survivality.config.option.buddingAmethystStrength"),
                                Text.translatable("text.survivality.config.option.buddingAmethystStrength.@Tooltip"),
                                buddingAmethystStrength, 4.5f, 0f, 30f, .5f))
                        .option(createBooleanOption(
                                Text.translatable("text.survivality.config.option.slotMachineGildedBlackstone"),
                                Text.translatable("text.survivality.config.option.slotMachineGildedBlackstone.@Tooltip"),
                                slotMachineGildedBlackstone, true))
                        .option(createBooleanOption(
                                Text.translatable("text.survivality.config.option.structurePotions"),
                                Text.translatable("text.survivality.config.option.structurePotions.@Tooltip"),
                                structurePotions, true))
                        .option(createBooleanOption(
                                Text.translatable("text.survivality.config.option.cactusHurtsHands"),
                                Text.translatable("text.survivality.config.option.cactusHurtsHands.@Tooltip"),
                                cactusHurtsHands, true))
                        .option(createBooleanOption(
                                Text.translatable("text.survivality.config.option.lightningSmeltsSand"),
                                Text.translatable("text.survivality.config.option.lightningSmeltsSand.@Tooltip"),
                                lightningSmeltsSand, true))
                        .build())
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("text.survivality.config.creative"))
                        .option(createBooleanOption(
                                Text.translatable("text.survivality.config.option.unboundEnchant"),
                                Text.translatable("text.survivality.config.option.unboundEnchant.@Tooltip"),
                                unboundEnchant, true))
                        .build())
                .save(this::save)
                .build();
        return config;
    }

    public void preload() {
        if (preloaded) return;
        preloaded = true;

        if (!Files.exists(configPath))
            return;

        try {
            String jsonString = Files.readString(configPath);
            JsonObject json = gson.fromJson(jsonString, JsonObject.class);
            JsonObject survival = json.getAsJsonObject("survival");
            JsonObject creative = json.getAsJsonObject("creative");

            if (survival.get("safe_cactus") != null)
                safeCactus.setValue(survival.get("safe_cactus").getAsBoolean());
            if (survival.get("feathery_falling_boots") != null)
                featheryFallingBoots.setValue(survival.get("feathery_falling_boots").getAsBoolean());
            if (survival.get("unstable_dripstone") != null)
                unstableDripstone.setValue(survival.get("unstable_dripstone").getAsBoolean());
            if (survival.get("better_night_vision") != null)
                betterNightVision.setValue(survival.get("better_night_vision").getAsBoolean());
            if (survival.get("max_night_vision_modifier") != null)
                maxNightVisionModifier.setValue(survival.get("max_night_vision_modifier").getAsFloat());
            if (survival.get("max_multishot_level") != null)
                maxMultishotLevel.setValue(survival.get("max_multishot_level").getAsInt());
            if (survival.get("max_minecart_speed") != null)
                maxMinecartSpeed.setValue(survival.get("max_minecart_speed").getAsInt());
            if (survival.get("trident_drop_rate") != null)
                tridentDropRate.setValue(survival.get("trident_drop_rate").getAsFloat());
            if (survival.get("eye_of_ender_break_chance") != null)
                eyeOfEnderBreakChance.setValue(survival.get("eye_of_ender_break_chance").getAsFloat());
            if (survival.get("zombie_horse_transmutation") != null)
                zombieHorseTransmutation.setValue(survival.get("zombie_horse_transmutation").getAsBoolean());
            if (survival.get("rideable_zombie_horses") != null)
                rideableZombieHorses.setValue(survival.get("rideable_zombie_horses").getAsBoolean());
            if (survival.get("player_xp_modifier") != null)
                playerXpModifier.setValue(survival.get("player_xp_modifier").getAsDouble());
            if (survival.get("colorful_sheep") != null)
                colorfulSheep.setValue(survival.get("colorful_sheep").getAsBoolean());
            if (survival.get("max_slime_size") != null)
                maxSlimeSize.setValue(survival.get("max_slime_size").getAsInt());
            if (survival.get("max_magma_cube_size") != null)
                maxMagmaCubeSize.setValue(survival.get("max_magma_cube_size").getAsInt());
            if (survival.get("more_snacks") != null)
                moreSnacks.setValue(survival.get("more_snacks").getAsBoolean());
            if (survival.get("shovelable_snow") != null)
                shovelableSnow.setValue(survival.get("shovelable_snow").getAsBoolean());
            if (survival.get("variant_spawners") != null)
                variantSpawners.setValue(survival.get("variant_spawners").getAsBoolean());
            if (survival.get("random_world_start_time") != null)
                randomWorldStartTime.setValue(survival.get("random_world_start_time").getAsBoolean());
            if (survival.get("random_world_start_weather") != null)
                randomWorldStartWeather.setValue(survival.get("random_world_start_weather").getAsBoolean());
            if (survival.get("random_world_start_spawn_pos") != null)
                randomWorldStartSpawnPos.setValue(survival.get("random_world_start_spawn_pos").getAsBoolean());
            if (survival.get("random_world_start_biome") != null)
                randomWorldStartBiome.setValue(survival.get("random_world_start_biome").getAsBoolean());
            if (survival.get("rocket_boosting") != null)
                rocketBoosting.setValue(survival.get("rocket_boosting").getAsBoolean());
            if (survival.get("spawner_required_player_range") != null)
                spawnerRequiredPlayerRange.setValue(survival.get("spawner_required_player_range").getAsInt());
            if (survival.get("unrestricted_spawners") != null)
                unrestrictedSpawners.setValue(survival.get("unrestricted_spawners").getAsBoolean());
            if (survival.get("polar_bear_cavalry_chance") != null)
                polarBearCavalryChance.setValue(survival.get("polar_bear_cavalry_chance").getAsFloat());
            if (survival.get("boats_ignore_waterlilies") != null)
                boatsIgnoreWaterlilies.setValue(survival.get("boats_ignore_waterlilies").getAsBoolean());
            if (survival.get("cactus_grow_height") != null)
                cactusGrowHeight.setValue(survival.get("cactus_grow_height").getAsInt());
            if (survival.get("sugar_cane_grow_height") != null)
                sugarCaneGrowHeight.setValue(survival.get("sugar_cane_grow_height").getAsInt());
            if (survival.get("fertilize_dirt") != null)
                fertilizableDirt.setValue(survival.get("fertilize_dirt").getAsBoolean());
            if (survival.get("explosive_fuel_explosion_chance") != null)
                explosiveFuelExplosionChance.setValue(survival.get("explosive_fuel_explosion_chance").getAsFloat());
            if (survival.get("snow_fog") != null)
                snowFog.setValue(survival.get("snow_fog").getAsBoolean());
            if (survival.get("stacked_potions") != null)
                stackedPotions.setValue(survival.get("stacked_potions").getAsBoolean());
            if (survival.get("snow_golem_spawn_weight") != null)
                snowGolemSpawnWeight.setValue(survival.get("snow_golem_spawn_weight").getAsInt());
            if (survival.get("snow_golem_max_spawn_size") != null)
                snowGolemMaxSpawnSize.setValue(survival.get("snow_golem_max_spawn_size").getAsInt());
            if (survival.get("creeper_chain_explosions") != null)
                creeperChainExplosionFuseTime.setValue(survival.get("creeper_chain_explosion_fuse_time").getAsInt());
            if (survival.get("no_initial_sign_edit") != null)
                noInitialSignEdit.setValue(survival.get("no_initial_sign_edit").getAsBoolean());
            if (survival.get("budding_amethyst_strength") != null)
                buddingAmethystStrength.setValue(survival.get("budding_amethyst_strength").getAsFloat());
            if (survival.get("slot_machine_gilded_blackstone") != null)
                slotMachineGildedBlackstone.setValue(survival.get("slot_machine_gilded_blackstone").getAsBoolean());
            if (survival.get("structure_potions") != null)
                structurePotions.setValue(survival.get("structure_potions").getAsBoolean());
            if (survival.get("cactus_hurts_hands") != null)
                cactusHurtsHands.setValue(survival.get("cactus_hurts_hands").getAsBoolean());
            if (survival.get("lightning_smelts_sand") != null)
                lightningSmeltsSand.setValue(survival.get("lightning_smelts_sand").getAsBoolean());

            if (creative.get("unbound_enchant") != null)
                unboundEnchant.setValue(creative.get("unbound_enchant").getAsBoolean());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            Files.deleteIfExists(configPath);

            JsonObject json = new JsonObject();
            JsonObject survival = new JsonObject();
            JsonObject creative = new JsonObject();
            json.add("survival", survival);
            json.add("creative", creative);
            survival.addProperty("safe_cactus", safeCactus.booleanValue());
            survival.addProperty("feathery_falling_boots", featheryFallingBoots.booleanValue());
            survival.addProperty("unstable_dripstone", unstableDripstone.booleanValue());
            survival.addProperty("better_night_vision", betterNightVision.booleanValue());
            survival.addProperty("max_night_vision_modifier", maxNightVisionModifier.floatValue());
            survival.addProperty("max_multishot_level", maxMultishotLevel.intValue());
            survival.addProperty("max_minecart_speed", maxMinecartSpeed.intValue());
            survival.addProperty("trident_drop_rate", tridentDropRate.floatValue());
            survival.addProperty("eye_of_ender_break_chance", eyeOfEnderBreakChance.floatValue());
            survival.addProperty("zombie_horse_transmutation", zombieHorseTransmutation.booleanValue());
            survival.addProperty("rideable_zombie_horses", rideableZombieHorses.booleanValue());
            survival.addProperty("player_xp_modifier", playerXpModifier.doubleValue());
            survival.addProperty("colorful_sheep", colorfulSheep.booleanValue());
            survival.addProperty("max_slime_size", maxSlimeSize.intValue());
            survival.addProperty("max_magma_cube_size", maxMagmaCubeSize.intValue());
            survival.addProperty("more_snacks", moreSnacks.booleanValue());
            survival.addProperty("shovelable_snow", shovelableSnow.booleanValue());
            survival.addProperty("variant_spawners", variantSpawners.booleanValue());
            survival.addProperty("random_world_start_time", randomWorldStartTime.booleanValue());
            survival.addProperty("random_world_start_weather", randomWorldStartWeather.booleanValue());
            survival.addProperty("random_world_start_spawn_pos", randomWorldStartSpawnPos.booleanValue());
            survival.addProperty("random_world_start_biome", randomWorldStartBiome.booleanValue());
            survival.addProperty("rocket_boosting", rocketBoosting.booleanValue());
            survival.addProperty("spawner_required_player_range", spawnerRequiredPlayerRange.intValue());
            survival.addProperty("unrestricted_spawners", unrestrictedSpawners.booleanValue());
            survival.addProperty("polar_bear_cavalry_chance", polarBearCavalryChance.floatValue());
            survival.addProperty("boats_ignore_waterlilies", boatsIgnoreWaterlilies.booleanValue());
            survival.addProperty("cactus_grow_height", cactusGrowHeight.intValue());
            survival.addProperty("sugar_cane_grow_height", sugarCaneGrowHeight.intValue());
            survival.addProperty("fertilize_dirt", fertilizableDirt.booleanValue());
            survival.addProperty("explosive_fuel_explosion_chance", explosiveFuelExplosionChance.floatValue());
            survival.addProperty("snow_fog", snowFog.booleanValue());
            survival.addProperty("stacked_potions", stackedPotions.booleanValue());
            survival.addProperty("snow_golem_spawn_weight", snowGolemSpawnWeight.intValue());
            survival.addProperty("snow_golem_max_spawn_size", snowGolemMaxSpawnSize.intValue());
            survival.addProperty("creeper_chain_explosion_fuse_time", creeperChainExplosionFuseTime.intValue());
            survival.addProperty("no_initial_sign_edit", noInitialSignEdit.booleanValue());
            survival.addProperty("budding_amethyst_strength", buddingAmethystStrength.floatValue());
            survival.addProperty("slot_machine_gilded_blackstone", slotMachineGildedBlackstone.booleanValue());
            survival.addProperty("structure_potions", structurePotions.booleanValue());
            survival.addProperty("cactus_hurts_hands", cactusHurtsHands.booleanValue());
            survival.addProperty("lightning_smelts_sand", lightningSmeltsSand.booleanValue());

            creative.addProperty("unbound_enchant", unboundEnchant.booleanValue());

            Files.createFile(configPath);
            Files.writeString(configPath, gson.toJson(json));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
    public Option<Boolean> createBooleanOption(Text name, Text description, MutableBoolean option, boolean def) {
        return Option.createBuilder(Boolean.class)
                .name(name)
                .description(OptionDescription.createBuilder()
                        .text(description)
                        .build())
                .binding(def, option::booleanValue, option::setValue)
                .controller(TickBoxControllerBuilder::create)
                .build();
    }

    @SuppressWarnings("deprecation")
    public Option<Integer> createIntegerOption(Text name, Text description, MutableInt option, int def, int min, int max, int interval) {
        return Option.createBuilder(Integer.class)
                .name(name)
                .description(OptionDescription.createBuilder()
                        .text(description)
                        .build())
                .binding(def, option::intValue, option::setValue)
                .controller(integerOption -> IntegerSliderControllerBuilder.create(integerOption)
                        .range(min, max)
                        .step(interval))
                .build();
    }

    @SuppressWarnings("deprecation")
    public Option<Float> createFloatOption(Text name, Text description, MutableFloat option, float def, float min, float max, float interval) {
        return Option.createBuilder(Float.class)
                .name(name)
                .description(OptionDescription.createBuilder()
                        .text(description)
                        .build())
                .binding(def, option::floatValue, option::setValue)
                .controller(floatOption -> FloatSliderControllerBuilder.create(floatOption)
                        .range(min, max)
                        .step(interval))
                .build();
    }

    @SuppressWarnings("deprecation")
    public Option<Double> createDoubleOption(Text name, Text description, MutableDouble option, double def, double min, double max, double interval) {
        return Option.createBuilder(Double.class)
                .name(name)
                .description(OptionDescription.createBuilder()
                        .text(description)
                        .build())
                .binding(def, option::doubleValue, option::setValue)
                .controller(doubleOption -> DoubleSliderControllerBuilder.create(doubleOption)
                        .range(min, max)
                        .step(interval))
                .build();
    }
}
