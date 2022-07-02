package net.morimori0317.dsc.fabric.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.morimori0317.dsc.DangerousStoneCutter;
import net.morimori0317.dsc.networking.DSCPackets;

public class DSCPacketsFabric {
    public static final ResourceLocation BLOOD_PARTICLE = new ResourceLocation(DangerousStoneCutter.MODID, "blood_particle");

    public static void clientInit() {
        ClientPlayNetworking.registerGlobalReceiver(BLOOD_PARTICLE, (client, handler, buf, responseSender) -> {
            int id = buf.readInt();
            BlockPos pos = buf.readBlockPos();
            client.execute(() -> DSCPackets.onBloodParticlePacket(id, pos));
        });
    }
}
