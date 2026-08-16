package net.morimori0317.dangerousstonecutter.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.morimori0317.dangerousstonecutter.DSCConfig;
import net.morimori0317.dangerousstonecutter.DangerousStoneCutter;
import net.morimori0317.dangerousstonecutter.fabric.networking.DSCPacketsFabric;

public final class DangerousStoneCutterFabric implements ModInitializer {

    private static DSCConfig CONFIG;
    private static boolean CONFIG_INIT = false;

    @Override
    public void onInitialize() {
        DangerousStoneCutter.init();
        DSCPacketsFabric.init();
    }

    public static DSCConfig getConfig() {

        if (!CONFIG_INIT) {
            CONFIG_INIT = true;

            if (FabricLoader.getInstance().isModLoaded("cloth-config")) {
                CONFIG = DSCConfigFabric.createConfig();
            } else {
                CONFIG = DSCConfig.DEFAULT;
            }
        }

        return CONFIG;
    }
}
