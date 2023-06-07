package com.github.creoii.survivality;

import com.github.creoii.survivality.integration.SurvivalityConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;

/**
 *  To Test:
 *
 *  In Progress:
 *
 *  To Do:
 */
public class Survivality implements ModInitializer {
	public static final String NAMESPACE = "survivality";
	public static SurvivalityConfig CONFIG = new SurvivalityConfig();

	@Override
	public void onInitialize() {
		AutoConfig.register(SurvivalityConfig.class, JanksonConfigSerializer::new);
	}
}
