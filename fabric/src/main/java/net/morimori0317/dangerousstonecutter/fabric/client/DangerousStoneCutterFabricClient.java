package net.morimori0317.dangerousstonecutter.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.morimori0317.dangerousstonecutter.fabric.client.networking.DSCClientPacketsFabric;

public class DangerousStoneCutterFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        DSCClientPacketsFabric.init();
    }
}
