package net.morimori0317.dsc.explatform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.chunk.LevelChunk;
import net.morimori0317.dsc.DSCConfig;

public class DSCExpectPlatform {
    @ExpectPlatform
    public static void sendBloodParticlePacket(LevelChunk chunk, int entityId, BlockPos pos) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static DSCConfig getConfig() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isLithiumLoaded() {
        throw new AssertionError();
    }

}
