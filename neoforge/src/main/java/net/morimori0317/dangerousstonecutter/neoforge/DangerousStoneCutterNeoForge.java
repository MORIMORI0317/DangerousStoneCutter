package net.morimori0317.dangerousstonecutter.neoforge;

import net.morimori0317.dangerousstonecutter.DangerousStoneCutter;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(DangerousStoneCutter.MODID)
public final class DangerousStoneCutterNeoForge {
    public static final DSCConfigNeoForge CONFIG = new DSCConfigNeoForge();

    public DangerousStoneCutterNeoForge(ModContainer container) {
        DSCConfigNeoForge.init(container);
        DangerousStoneCutter.init();
    }
}
