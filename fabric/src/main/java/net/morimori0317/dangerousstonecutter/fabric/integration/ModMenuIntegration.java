package net.morimori0317.dangerousstonecutter.fabric.integration;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.morimori0317.dangerousstonecutter.fabric.DSCConfigFabric;
import net.morimori0317.dangerousstonecutter.fabric.DangerousStoneCutterFabric;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (DangerousStoneCutterFabric.getConfig() instanceof DSCConfigFabric)
            return DSCConfigFabric::createConfigScreen;
        return ModMenuApi.super.getModConfigScreenFactory();
    }
}
