package net.morimori0317.dangerousstonecutter.fabric.client;

import me.shedaniel.autoconfig.AutoConfigClient;
import net.minecraft.client.gui.screens.Screen;
import net.morimori0317.dangerousstonecutter.fabric.DSCConfigFabric;

public class DSCClientConfigFabric {
    public static Screen createConfigScreen(Screen parent) {
        return AutoConfigClient.getConfigScreen(DSCConfigFabric.class, parent).get();
    }
}
