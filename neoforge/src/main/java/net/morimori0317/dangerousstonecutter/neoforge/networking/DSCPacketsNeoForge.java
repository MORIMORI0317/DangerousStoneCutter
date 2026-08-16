package net.morimori0317.dangerousstonecutter.neoforge.networking;

import net.morimori0317.dangerousstonecutter.networking.DSCPackets;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class DSCPacketsNeoForge {
    public static final String PROTOCOL_VERSION = "1";

    public static void handleBloodParticleDataOnClient(final DSCPackets.BloodParticleMessage data, final IPayloadContext context) {
        context.enqueueWork(() -> DSCPackets.onBloodParticlePacket(data.entityId(), data.blockPos()));
    }

    public static void handleBloodParticleDataOnServer(final DSCPackets.BloodParticleMessage data, final IPayloadContext context) {
    }
}
