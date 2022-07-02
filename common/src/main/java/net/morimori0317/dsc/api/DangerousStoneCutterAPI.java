package net.morimori0317.dsc.api;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.Entity;
import net.morimori0317.dsc.DangerousStoneCutterAPIImpl;

import java.util.function.Function;

public interface DangerousStoneCutterAPI {
    DangerousStoneCutterAPI INSTANCE = new DangerousStoneCutterAPIImpl();

    static DangerousStoneCutterAPI getInstance() {
        return INSTANCE;
    }

    void addBloodParticleListener(Function<Entity, ParticleOptions> listener);
}
