package net.morimori0317.dangerousstonecutter.fabric.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.morimori0317.dangerousstonecutter.networking.DSCPackets;

public class DSCPacketsFabric {
    public static void init() {
        PayloadTypeRegistry.playS2C().register(DSCPackets.BLOOD_PARTICLE_TYPE, DSCPackets.BLOOD_PARTICLE_CODEC);
    }

    public static void clientInit() {
        ClientPlayNetworking.registerGlobalReceiver(DSCPackets.BLOOD_PARTICLE_TYPE, (payload, context) -> DSCPackets.onBloodParticlePacket(payload.entityId(), payload.blockPos()));
    }
}
