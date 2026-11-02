package net.morimori0317.dangerousstonecutter.fabric.integration;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.gui.screens.Screen;
import net.morimori0317.dangerousstonecutter.DangerousStoneCutter;
import net.morimori0317.dangerousstonecutter.fabric.DangerousStoneCutterFabric;
import net.morimori0317.dangerousstonecutter.fabric.client.DSCClientConfigFabric;

import java.util.Map;
import java.util.function.BiFunction;

public class CatalogueIntegration {

    // 参考
    // https://github.com/shedaniel/cloth-config/blob/v26.1/fabric/src/main/java/me/shedaniel/clothconfig2/fabric/ClothConfigCatalogueDemo.java

/*    public static Screen createConfigScreen(Screen currentScreen, ModContainer container) {
        if (DangerousStoneCutterFabric.isClothConfigLoaded()) {
            return DSCClientConfigFabric.createConfigScreen(currentScreen);
        }
        return null;
    }*/

    // https://github.com/MrCrayfish/Catalogue/blob/c556f997d5539c77fb3371eeccf755ca8a00f321/fabric/src/main/java/com/mrcrayfish/catalogue/Catalogue.java#L114

    public static Map<String, BiFunction<Screen, ModContainer, Screen>> createConfigProvider() {
        if (DangerousStoneCutterFabric.isClothConfigLoaded()) {
            return ImmutableMap.of(DangerousStoneCutter.MODID,
                    (currentScreen, container) -> DSCClientConfigFabric.createConfigScreen(currentScreen));
        }
        return null;
    }

}
