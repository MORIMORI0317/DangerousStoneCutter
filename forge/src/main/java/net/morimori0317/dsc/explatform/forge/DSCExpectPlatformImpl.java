package net.morimori0317.dsc.explatform.forge;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.network.PacketDistributor;
import net.morimori0317.dsc.DSCConfig;
import net.morimori0317.dsc.forge.DangerousStoneCutterForge;
import net.morimori0317.dsc.forge.networking.DSCPacketsForge;

public class DSCExpectPlatformImpl {
    public static void sendBloodParticlePacket(LevelChunk chunk, int entityId, BlockPos pos) {
        DSCPacketsForge.INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk), new DSCPacketsForge.BloodParticleMessage(entityId, pos));
    }

    public static DSCConfig getConfig() {
        return DangerousStoneCutterForge.CONFIG;
    }
}
