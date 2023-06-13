package com.github.creoii.survivality.integration;

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

public class SurvivalityConfig {
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
        return YetAnotherConfigLib.createBuilder()
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
                                Text.translatable("text.survivality.config.option.safeCactus"),
                                Text.translatable("text.survivality.config.option.safeCactus.@Tooltip"),
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
                                spawnerRequiredPlayerRange, 32, 1, 128, 32))
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
                .build();
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

    public Option<Integer> createIntegerOption(Text name, Text description, MutableInt option, int def, int min, int max) {
        return createIntegerOption(name, description, option, def, min, max, 1);
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
