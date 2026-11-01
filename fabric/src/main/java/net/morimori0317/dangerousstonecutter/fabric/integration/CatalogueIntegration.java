package net.morimori0317.dangerousstonecutter.fabric.integration;

import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.gui.screens.Screen;
import net.morimori0317.dangerousstonecutter.fabric.client.DSCClientConfigFabric;

public class CatalogueIntegration {

    // 参考
    // https://github.com/shedaniel/cloth-config/blob/v26.1/fabric/src/main/java/me/shedaniel/clothconfig2/fabric/ClothConfigCatalogueDemo.java

    public static Screen createConfigScreen(Screen currentScreen, ModContainer container) {
        return DSCClientConfigFabric.createConfigScreen(currentScreen);
    }
}
