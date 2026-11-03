package net.morimori0317.dangerousstonecutter.fabric.client.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.morimori0317.dangerousstonecutter.networking.DSCPackets;

public class DSCClientPacketsFabric {
    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(DSCPackets.BLOOD_PARTICLE_TYPE, (payload, context) ->
                DSCPackets.onBloodParticlePacket(context.player().level(), payload.entityId(), payload.blockPos())
        );
    }
}
