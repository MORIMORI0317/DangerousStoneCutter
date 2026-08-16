package net.morimori0317.dangerousstonecutter.explatform.fabric;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.morimori0317.dangerousstonecutter.DSCConfig;
import net.morimori0317.dangerousstonecutter.fabric.DangerousStoneCutterFabric;
import net.morimori0317.dangerousstonecutter.networking.DSCPackets;

public class DSCExpectPlatformImpl {
    public static void sendBloodParticlePacket(ServerLevel level, ChunkPos chunkPos, int entityId, BlockPos pos) {
        DSCPackets.BloodParticleMessage msg = new DSCPackets.BloodParticleMessage(entityId, pos.immutable());
        level.getChunkSource().chunkMap.getPlayers(chunkPos, false)
                .forEach(player -> ServerPlayNetworking.send(player, msg));
    }

    public static DSCConfig getConfig() {
        return DangerousStoneCutterFabric.getConfig();
    }
}
