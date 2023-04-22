package com.github.creoii.survivality.util;

import com.github.creoii.survivality.Survivality;
import net.minecraft.entity.EntityType;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SurvivalityTags {
    public static final class EntityTypes {
        public static final TagKey<EntityType<?>> BREAK_DRIPSTONE = TagKey.of(Registry.ENTITY_TYPE_KEY, new Identifier(Survivality.NAMESPACE, "break_dripstone"));
        public static final TagKey<EntityType<?>> BOOSTABLE_VEHICLES = TagKey.of(Registry.ENTITY_TYPE_KEY, new Identifier(Survivality.NAMESPACE, "boostable_vehicles"));
    }
}
