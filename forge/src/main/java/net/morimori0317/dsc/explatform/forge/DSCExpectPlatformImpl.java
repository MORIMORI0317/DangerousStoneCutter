package net.morimori0317.dsc.explatform.forge;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.network.PacketDistributor;
import net.morimori0317.dsc.DSCConfig;
import net.morimori0317.dsc.forge.DangerousStoneCutterForge;
import net.morimori0317.dsc.forge.integration.CorailWoodcutterIntegration;
import net.morimori0317.dsc.forge.networking.DSCPacketsForge;
import ovh.corail.woodcutter.block.WoodcutterBlock;

public class DSCExpectPlatformImpl {
    public static void sendBloodParticlePacket(LevelChunk chunk, int entityId, BlockPos pos) {
        DSCPacketsForge.INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk), new DSCPacketsForge.BloodParticleMessage(entityId, pos));
    }

    public static DSCConfig getConfig() {
        return DangerousStoneCutterForge.CONFIG;
    }

    public static boolean isSupportStoneCutter(BlockState state) {
        return CorailWoodcutterIntegration.isEnable() && state.getBlock() instanceof WoodcutterBlock;
    }

    public static boolean isLithiumLoaded() {
        return FMLLoader.getLoadingModList().getModFileById("radium") != null;
    }
}
