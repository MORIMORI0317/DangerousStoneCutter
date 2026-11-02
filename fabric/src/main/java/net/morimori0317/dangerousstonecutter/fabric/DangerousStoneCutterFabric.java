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
        updateConfig();
        DangerousStoneCutter.init();
        DSCPacketsFabric.init();
    }

    private static void updateConfig() {
        if (!CONFIG_INIT) {
            CONFIG_INIT = true;

            if (isClothConfigLoaded()) {
                CONFIG = DSCConfigFabric.createConfig();
            } else {
                CONFIG = DSCConfig.DEFAULT;
            }
        }
    }

    public static DSCConfig getConfig() {
        updateConfig();
        return CONFIG;
    }

    public static boolean isClothConfigLoaded() {
        return FabricLoader.getInstance().isModLoaded("cloth-config");
    }
}
