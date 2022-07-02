package net.morimori0317.dsc.forge.integration;

import net.minecraftforge.fml.ModList;
import net.morimori0317.dsc.forge.DangerousStoneCutterForge;

public class CorailWoodcutterIntegration {
    public static boolean isEnable() {
        return ModList.get().isLoaded("corail_woodcutter") && DangerousStoneCutterForge.CONFIG.isEnableCorailWoodcutterIntegration();
    }
}
