package com.github.creoii.survivality.integration;

import com.google.common.collect.ImmutableList;
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
import dev.isxander.yacl3.config.GsonConfigInstance;
import dev.isxander.yacl3.gui.YACLScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.apache.commons.lang3.mutable.MutableInt;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;

public class SurvivalityConfig {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Path configPath = Path.of("config", "survivality.json");
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
    public MutableDouble maxMinecartSpeed = new MutableDouble(1.6d);

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
                        .option(createDoubleOption(
                                Text.translatable("text.survivality.config.option.maxMinecartSpeed"),
                                Text.translatable("text.survivality.config.option.maxMinecartSpeed.@Tooltip"),
                                maxMinecartSpeed, 1.6d, 0d, 5d, .2d))
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

            safeCactus.setValue(json.get("safe_cactus").getAsBoolean());
            featheryFallingBoots.setValue(json.get("feathery_falling_boots").getAsBoolean());
            unstableDripstone.setValue(json.get("unstable_dripstone").getAsBoolean());
            betterNightVision.setValue(json.get("better_night_vision").getAsBoolean());
            maxNightVisionModifier.setValue(json.get("max_night_vision_modifier").getAsFloat());
            maxMultishotLevel.setValue(json.get("max_multishot_level").getAsInt());
            maxMinecartSpeed.setValue(json.get("max_minecart_speed").getAsDouble());
            tridentDropRate.setValue(json.get("trident_drop_rate").getAsFloat());
            eyeOfEnderBreakChance.setValue(json.get("eye_of_ender_break_chance").getAsFloat());
            zombieHorseTransmutation.setValue(json.get("zombie_horse_transmutation").getAsBoolean());
            rideableZombieHorses.setValue(json.get("rideable_zombie_horses").getAsBoolean());
            playerXpModifier.setValue(json.get("player_xp_modifier").getAsDouble());
            colorfulSheep.setValue(json.get("colorful_sheep").getAsBoolean());
            maxSlimeSize.setValue(json.get("max_slime_size").getAsInt());
            maxMagmaCubeSize.setValue(json.get("max_magma_cube_size").getAsInt());
            moreSnacks.setValue(json.get("more_snacks").getAsBoolean());
            shovelableSnow.setValue(json.get("shovelable_snow").getAsBoolean());
            variantSpawners.setValue(json.get("variant_spawners").getAsBoolean());
            randomWorldStartTime.setValue(json.get("random_world_start_time").getAsBoolean());
            randomWorldStartWeather.setValue(json.get("random_world_start_weather").getAsBoolean());
            randomWorldStartSpawnPos.setValue(json.get("random_world_start_spawn_pos").getAsBoolean());
            randomWorldStartBiome.setValue(json.get("random_world_start_biome").getAsBoolean());
            rocketBoosting.setValue(json.get("rocket_boosting").getAsBoolean());
            spawnerRequiredPlayerRange.setValue(json.get("spawner_required_player_range").getAsInt());
            unrestrictedSpawners.setValue(json.get("unrestricted_spawners").getAsBoolean());
            polarBearCavalryChance.setValue(json.get("polar_bear_cavalry_chance").getAsFloat());
            boatsIgnoreWaterlilies.setValue(json.get("boats_ignore_waterlilies").getAsBoolean());
            unboundEnchant.setValue(json.get("unbound_enchant").getAsBoolean());
            cactusGrowHeight.setValue(json.get("cactus_grow_height").getAsInt());
            sugarCaneGrowHeight.setValue(json.get("sugar_cane_grow_height").getAsInt());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            Files.deleteIfExists(configPath);

            JsonObject json = new JsonObject();
            json.addProperty("safe_cactus", safeCactus.booleanValue());
            json.addProperty("feathery_falling_boots", featheryFallingBoots.booleanValue());
            json.addProperty("unstable_dripstone", unstableDripstone.booleanValue());
            json.addProperty("better_night_vision", betterNightVision.booleanValue());
            json.addProperty("max_night_vision_modifier", maxNightVisionModifier.floatValue());
            json.addProperty("max_multishot_level", maxMultishotLevel.intValue());
            json.addProperty("max_minecart_speed", maxMinecartSpeed.doubleValue());
            json.addProperty("trident_drop_rate", tridentDropRate.floatValue());
            json.addProperty("eye_of_ender_break_chance", eyeOfEnderBreakChance.floatValue());
            json.addProperty("zombie_horse_transmutation", zombieHorseTransmutation.booleanValue());
            json.addProperty("rideable_zombie_horses", rideableZombieHorses.booleanValue());
            json.addProperty("player_xp_modifier", playerXpModifier.doubleValue());
            json.addProperty("colorful_sheep", colorfulSheep.booleanValue());
            json.addProperty("max_slime_size", maxSlimeSize.intValue());
            json.addProperty("max_magma_cube_size", maxMagmaCubeSize.intValue());
            json.addProperty("more_snacks", moreSnacks.booleanValue());
            json.addProperty("shovelable_snow", shovelableSnow.booleanValue());
            json.addProperty("variant_spawners", variantSpawners.booleanValue());
            json.addProperty("random_world_start_time", randomWorldStartTime.booleanValue());
            json.addProperty("random_world_start_weather", randomWorldStartWeather.booleanValue());
            json.addProperty("random_world_start_spawn_pos", randomWorldStartSpawnPos.booleanValue());
            json.addProperty("random_world_start_biome", randomWorldStartBiome.booleanValue());
            json.addProperty("rocket_boosting", rocketBoosting.booleanValue());
            json.addProperty("spawner_required_player_range", spawnerRequiredPlayerRange.intValue());
            json.addProperty("unrestricted_spawners", unrestrictedSpawners.booleanValue());
            json.addProperty("polar_bear_cavalry_chance", polarBearCavalryChance.floatValue());
            json.addProperty("boats_ignore_waterlilies", boatsIgnoreWaterlilies.booleanValue());
            json.addProperty("unbound_enchant", unboundEnchant.booleanValue());
            json.addProperty("cactus_grow_height", cactusGrowHeight.intValue());
            json.addProperty("sugar_cane_grow_height", sugarCaneGrowHeight.intValue());

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
