package com.github.creoii.survivality.integration;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "survivality")
@Config.Gui.Background("minecraft:textures/block/dirt.png")
public class SurvivalityConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Category("general_settings")
    public boolean safeCactus = true;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Category("general_settings")
    public boolean featheryFallingBoots = true;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Category("general_settings")
    public boolean snowyLeaves = true;

    @ConfigEntry.Gui.Tooltip(count = 2)
    @ConfigEntry.Category("general_settings")
    public boolean unstableDripstone = true;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Category("general_settings")
    public boolean editableSigns = true;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Category("general_settings")
    public boolean armorSwapping = true;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Gui.RequiresRestart(value = false)
    @ConfigEntry.Category("general_settings")
    public boolean betterNightVision = true;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Gui.RequiresRestart(value = false)
    @ConfigEntry.BoundedDiscrete(min = 0, max = 25)
    @ConfigEntry.Category("general_settings")
    public float maxNightVisionModifier = 5f;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Category("general_settings")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 7)
    public int maxMultishotLevel = 4;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Category("general_settings")
    public double maxMinecartSpeed = 1.6d;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Category("general_settings")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 1)
    public float tridentDropRate = .15f;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Category("general_settings")
    public float eyeOfEnderBreakChance = 0f;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Category("general_settings")
    public boolean zombieHorseTransmutation = true;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Category("general_settings")
    public boolean rideableZombieHorses = true;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Category("general_settings")
    public double playerXpModifier = 12d;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Category("general_settings")
    public boolean colorfulSheep = true;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Category("general_settings")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 10)
    public int maxSlimeSize = 4;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Category("general_settings")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 10)
    public int maxMagmaCubeSize = 4;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Category("general_settings")
    public boolean moreSnacks = true;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Category("general_settings")
    public boolean shovelableSnow = true;

    @ConfigEntry.Gui.Tooltip(count = 4)
    @ConfigEntry.Category("general_settings")
    public boolean variantSpawners = true;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Gui.RequiresRestart(value = false)
    @ConfigEntry.Category("general_settings")
    public boolean randomWorldStartTime = true;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Gui.RequiresRestart(value = false)
    @ConfigEntry.Category("general_settings")
    public boolean randomWorldStartWeather = true;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Gui.RequiresRestart(value = false)
    @ConfigEntry.Category("general_settings")
    public boolean randomWorldStartSpawnPos = true;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Gui.RequiresRestart(value = false)
    @ConfigEntry.Category("general_settings")
    public boolean randomWorldStartBiome = true;

    @ConfigEntry.Gui.Tooltip(count = 3)
    @ConfigEntry.Category("general_settings")
    public boolean rocketBoosting = true;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Category("general_settings")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 128)
    public int spawnerRequiredPlayerRange = 32;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Category("general_settings")
    public boolean unrestrictedSpawners = true;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Category("creative_settings")
    public boolean unboundEnchant = true;
}
