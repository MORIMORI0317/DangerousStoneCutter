package net.morimori0317.dsc.forge.networking;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.morimori0317.dsc.DangerousStoneCutter;
import net.morimori0317.dsc.networking.DSCPackets;

public class DSCPacketsForge {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(DangerousStoneCutter.MODID, "main_channel"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void init() {
        INSTANCE.registerMessage(1, BloodParticleMessage.class, BloodParticleMessage::encode, BloodParticleMessage::decode, (msg, ctx) -> {
            ctx.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> DSCPackets.onBloodParticlePacket(msg.entityId, msg.pos)));
            ctx.get().setPacketHandled(true);
        });
    }

    public static class BloodParticleMessage {
        public int entityId;
        public BlockPos pos;

        public BloodParticleMessage(int entityId, BlockPos pos) {
            this.entityId = entityId;
            this.pos = pos;
        }

        public void encode(FriendlyByteBuf buf) {
            buf.writeInt(entityId);
            buf.writeBlockPos(pos);
        }

        public static BloodParticleMessage decode(FriendlyByteBuf buf) {
            return new BloodParticleMessage(buf.readInt(), buf.readBlockPos());
        }
    }
}
