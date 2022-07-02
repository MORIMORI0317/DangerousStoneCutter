package net.morimori0317.dsc.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.morimori0317.dsc.DSCConfig;
import net.morimori0317.dsc.DangerousStoneCutter;


public class DangerousStoneCutterFabric implements ModInitializer {
    private static DSCConfigFabric CONFIG;

    @Override
    public void onInitialize() {
        DangerousStoneCutter.init();
    }

    public static DSCConfig getConfig() {
        if (CONFIG == null && FabricLoader.getInstance().isModLoaded("cloth-config"))
            CONFIG = DSCConfigFabric.createConfig();

        if (CONFIG != null)
            return CONFIG;
        return DSCConfig.DEFAULT;
    }
}
