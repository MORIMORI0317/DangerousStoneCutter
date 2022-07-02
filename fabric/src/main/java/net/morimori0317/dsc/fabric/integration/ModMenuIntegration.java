package net.morimori0317.dsc.fabric.integration;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.morimori0317.dsc.fabric.DSCConfigFabric;
import net.morimori0317.dsc.fabric.DangerousStoneCutterFabric;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (DangerousStoneCutterFabric.getConfig() instanceof DSCConfigFabric)
            return DSCConfigFabric::createConfigScreen;
        return ModMenuApi.super.getModConfigScreenFactory();
    }
}
