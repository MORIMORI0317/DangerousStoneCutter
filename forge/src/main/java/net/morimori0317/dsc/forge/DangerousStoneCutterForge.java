package net.morimori0317.dsc.forge;

import net.minecraftforge.fml.common.Mod;
import net.morimori0317.dsc.DangerousStoneCutter;
import net.morimori0317.dsc.forge.networking.DSCPacketsForge;

@Mod(DangerousStoneCutter.MODID)
public class DangerousStoneCutterForge {
    public static final DSCConfigForge CONFIG = new DSCConfigForge();

    public DangerousStoneCutterForge() {
        DSCConfigForge.init();
        DangerousStoneCutter.init();
        DSCPacketsForge.init();
    }
}
