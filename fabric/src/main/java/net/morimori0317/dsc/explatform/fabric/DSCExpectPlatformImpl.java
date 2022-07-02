package net.morimori0317.dsc.explatform.fabric;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.morimori0317.dsc.DSCConfig;
import net.morimori0317.dsc.fabric.DangerousStoneCutterFabric;
import net.morimori0317.dsc.fabric.networking.DSCPacketsFabric;

public class DSCExpectPlatformImpl {

    public static void sendBloodParticlePacket(LevelChunk chunk, int entityId, BlockPos pos) {
        ((ServerChunkCache) chunk.getLevel().getChunkSource()).chunkMap.getPlayers(chunk.getPos(), false).forEach(player -> {
            FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
            buf.writeInt(entityId);
            buf.writeBlockPos(pos);
            ServerPlayNetworking.send(player, DSCPacketsFabric.BLOOD_PARTICLE, buf);
        });
    }

    public static DSCConfig getConfig() {
        return DangerousStoneCutterFabric.getConfig();
    }

    public static boolean isSupportStoneCutter(BlockState state) {
        return false;
    }
}
