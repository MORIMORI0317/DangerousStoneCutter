package net.morimori0317.dangerousstonecutter.explatform.neoforge;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.morimori0317.dangerousstonecutter.DSCConfig;
import net.morimori0317.dangerousstonecutter.neoforge.DangerousStoneCutterNeoForge;
import net.morimori0317.dangerousstonecutter.networking.DSCPackets;
import net.neoforged.neoforge.network.PacketDistributor;

public class DSCExpectPlatformImpl {
    public static void sendBloodParticlePacket(ServerLevel level, ChunkPos chunkPos, int entityId, BlockPos pos) {
        PacketDistributor.sendToPlayersTrackingChunk(level, chunkPos, new DSCPackets.BloodParticleMessage(entityId, pos.immutable()));
    }

    public static DSCConfig getConfig() {
        return DangerousStoneCutterNeoForge.CONFIG;
    }
}
