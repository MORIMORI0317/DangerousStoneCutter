package net.morimori0317.dsc.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.morimori0317.dsc.fabric.networking.DSCPacketsFabric;

public class DangerousStoneCutterClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        DSCPacketsFabric.clientInit();
    }
}
