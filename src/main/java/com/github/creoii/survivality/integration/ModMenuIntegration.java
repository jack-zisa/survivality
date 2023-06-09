package com.github.creoii.survivality.integration;

import com.github.creoii.survivality.Survivality;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> Survivality.CONFIG.getYACL().generateScreen(parent);
    }
}