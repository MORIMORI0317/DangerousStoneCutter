package net.morimori0317.dangerousstonecutter.networking;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.morimori0317.dangerousstonecutter.DangerousStoneCutter;
import org.jetbrains.annotations.NotNull;

public class DSCPackets {
    public static final CustomPacketPayload.Type<BloodParticleMessage> BLOOD_PARTICLE_TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(DangerousStoneCutter.MODID, "blood_particle"));

    public static final StreamCodec<RegistryFriendlyByteBuf, BloodParticleMessage> BLOOD_PARTICLE_CODEC = new StreamCodec<>() {
        @Override
        public void encode(RegistryFriendlyByteBuf buf, BloodParticleMessage msg) {
            buf.writeInt(msg.entityId());
            buf.writeBlockPos(msg.blockPos);
        }

        @Override
        public @NotNull BloodParticleMessage decode(RegistryFriendlyByteBuf buf) {
            return new BloodParticleMessage(buf.readInt(), buf.readBlockPos());
        }
    };

    public static void onBloodParticlePacket(int entityId, BlockPos blockPos) {
        if (!DangerousStoneCutter.getConfig().isEnableBloodParticle()) return;
        var level = Minecraft.getInstance().level;
        if (level != null) {
            var entity = level.getEntity(entityId);

            var shape = level.getBlockState(blockPos).getCollisionShape(level, blockPos);
            double shMaxX = shape.max(Direction.Axis.X) + blockPos.getX();
            double shMaxZ = shape.max(Direction.Axis.Z) + blockPos.getZ();
            double shMinX = shape.min(Direction.Axis.X) + blockPos.getX();
            double shMinZ = shape.min(Direction.Axis.Z) + blockPos.getZ();

            if (entity != null) {
                var pt = DangerousStoneCutter.getBloodParticle(entity);
                if (pt == null) return;
                var pos = entity.position();
                float width = entity.getBbWidth();
                int ct = (int) (8f * width);
                for (int i = 0; i < ct; i++) {
                    double posX = pos.x() - width / 2f + level.getRandom().nextDouble() * width;
                    double posZ = pos.z() - width / 2f + level.getRandom().nextDouble() * width;
                    double posY = pos.y() + level.getRandom().nextDouble() * (blockPos.getY() + 1d - pos.y());

                    if (posX >= shMinX && posX <= shMaxX && posZ >= shMinZ && posZ <= shMaxZ) {
                        level.addParticle(pt, posX, posY, posZ, 0, 0, 0);
                    }
                }
            }
        }
    }

    public record BloodParticleMessage(int entityId, BlockPos blockPos) implements CustomPacketPayload {
        @Override
        public @NotNull Type<? extends CustomPacketPayload> type() {
            return BLOOD_PARTICLE_TYPE;
        }
    }
}
