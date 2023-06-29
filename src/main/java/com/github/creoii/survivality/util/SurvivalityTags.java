package com.github.creoii.survivality.util;

import com.github.creoii.survivality.Survivality;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class SurvivalityTags {
    public static final TagKey<Block> DIRT_FERTILIZABLE_GRASS = TagKey.of(RegistryKeys.BLOCK, new Identifier(Survivality.NAMESPACE, "dirt_fertilizable_grass"));
    public static final TagKey<Item> EXPLODING_FUELS = TagKey.of(RegistryKeys.ITEM, new Identifier(Survivality.NAMESPACE, "exploding_fuels"));
    public static final TagKey<EntityType<?>> BREAK_DRIPSTONE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(Survivality.NAMESPACE, "break_dripstone"));
    public static final TagKey<EntityType<?>> BOOSTABLE_VEHICLES = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(Survivality.NAMESPACE, "boostable_vehicles"));
    public static final TagKey<Biome> SNOW_FOG_BIOMES = TagKey.of(RegistryKeys.BIOME, new Identifier(Survivality.NAMESPACE, "snow_fog_biomes"));
    public static final TagKey<Biome> SNOW_GOLEM_BIOMES = TagKey.of(RegistryKeys.BIOME, new Identifier(Survivality.NAMESPACE, "snow_golem_biomes"));
}
