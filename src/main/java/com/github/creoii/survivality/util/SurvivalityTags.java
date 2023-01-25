package com.github.creoii.survivality.util;

import com.github.creoii.survivality.Survivality;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class SurvivalityTags {
    public static final class EntityTypes {
        public static final TagKey<EntityType<?>> BREAK_DRIPSTONE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(Survivality.NAMESPACE, "break_dripstone"));
        public static final TagKey<EntityType<?>> BOOSTABLE_VEHICLES = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(Survivality.NAMESPACE, "boostable_vehicles"));
    }
}
