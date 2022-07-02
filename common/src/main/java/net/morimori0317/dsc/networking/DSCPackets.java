package net.morimori0317.dsc.networking;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.morimori0317.dsc.DangerousStoneCutter;

public class DSCPackets {
    public static void onBloodParticlePacket(int entityId, BlockPos blockPos) {
        if (!DangerousStoneCutter.getConfig().isEnableBloodParticle()) return;
        var level = Minecraft.getInstance().level;
        if (level != null) {
            var entity = level.getEntity(entityId);
            if (entity != null) {
                var pt = DangerousStoneCutter.getBloodParticle(entity);
                if (pt == null) return;
                var pos = entity.position();
                float width = entity.getBbWidth();
                int ct = (int) (5f * width);

                for (int i = 0; i < ct; i++) {
                    double posX = pos.x() - width / 2f + level.getRandom().nextDouble() * width;
                    double posZ = pos.z() - width / 2f + level.getRandom().nextDouble() * width;

                    if (posX >= blockPos.getX() && posX <= blockPos.getX() + 1 && posZ >= blockPos.getZ() && posZ <= blockPos.getZ() + 1)
                        level.addParticle(pt, posX, pos.y(), posZ, 0, 0, 0);
                }
            }
        }
    }
}
