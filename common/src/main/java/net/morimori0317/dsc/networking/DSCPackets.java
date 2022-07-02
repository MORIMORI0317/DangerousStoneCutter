package net.morimori0317.dsc.networking;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.morimori0317.dsc.DangerousStoneCutter;

public class DSCPackets {
    private static final Minecraft mc = Minecraft.getInstance();

    public static void onBloodParticlePacket(int entityId, BlockPos blockPos) {
        if (!DangerousStoneCutter.getConfig().isEnableBloodParticle()) return;
        var level = mc.level;
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

                    if (posX >= shMinX && posX <= shMaxX && posZ >= shMinZ && posZ <= shMaxZ)
                        level.addParticle(pt, posX, posY, posZ, 0, 0, 0);
                }
            }
        }
    }
}
