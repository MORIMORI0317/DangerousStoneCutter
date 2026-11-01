package net.morimori0317.dangerousstonecutter.fabric.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.morimori0317.dangerousstonecutter.networking.DSCPackets;

public class DSCPacketsFabric {
    public static void init() {
        PayloadTypeRegistry.clientboundPlay().register(DSCPackets.BLOOD_PARTICLE_TYPE, DSCPackets.BLOOD_PARTICLE_CODEC);
        ClientPlayNetworking.registerGlobalReceiver(DSCPackets.BLOOD_PARTICLE_TYPE, (payload, context) ->
                DSCPackets.onBloodParticlePacket(context.player().level(), payload.entityId(), payload.blockPos())
        );
    }
}
