package com.github.creoii.survivality.integration;

import com.github.creoii.survivality.Survivality;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import org.jetbrains.annotations.Nullable;

public class ModMenuIntegration implements ModMenuApi {
    @Nullable
    public static SurvivalityConfig CONFIG = Survivality.CONFIG_AVAILABLE ? new SurvivalityConfig() : null;

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            if (CONFIG != null) {
                return CONFIG.getYACL().generateScreen(parent);
            }
            return null;
        };
    }
}