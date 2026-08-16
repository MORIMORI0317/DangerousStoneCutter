package net.morimori0317.dangerousstonecutter.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.morimori0317.dangerousstonecutter.fabric.networking.DSCPacketsFabric;

public class DangerousStoneCutterFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        DSCPacketsFabric.clientInit();
    }
}
