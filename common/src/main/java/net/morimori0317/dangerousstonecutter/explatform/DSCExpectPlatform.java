package net.morimori0317.dangerousstonecutter.explatform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.morimori0317.dangerousstonecutter.DSCConfig;

public class DSCExpectPlatform {
    @ExpectPlatform
    public static void sendBloodParticlePacket(ServerLevel level, ChunkPos chunkPos, int entityId, BlockPos pos) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static DSCConfig getConfig() {
        throw new AssertionError();
    }
}