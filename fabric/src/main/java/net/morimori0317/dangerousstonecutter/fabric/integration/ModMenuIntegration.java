package net.morimori0317.dangerousstonecutter.fabric.integration;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.morimori0317.dangerousstonecutter.fabric.DSCConfigFabric;
import net.morimori0317.dangerousstonecutter.fabric.DangerousStoneCutterFabric;
import net.morimori0317.dangerousstonecutter.fabric.client.DSCClientConfigFabric;

public class ModMenuIntegration implements ModMenuApi {

    // 参考
    // https://github.com/shedaniel/cloth-config/blob/v26.1/fabric/src/main/java/me/shedaniel/clothconfig2/fabric/ClothConfigModMenuDemo.java

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (DangerousStoneCutterFabric.isClothConfigLoaded()) {
            return DSCClientConfigFabric::createConfigScreen;
        }
        return ModMenuApi.super.getModConfigScreenFactory();
    }
}
