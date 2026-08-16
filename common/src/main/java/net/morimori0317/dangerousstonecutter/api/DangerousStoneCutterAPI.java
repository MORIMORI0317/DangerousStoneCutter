package net.morimori0317.dangerousstonecutter.api;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.Entity;
import net.morimori0317.dangerousstonecutter.DangerousStoneCutter;

import java.util.function.Function;

public interface DangerousStoneCutterAPI {

    static DangerousStoneCutterAPI getInstance() {
        return DangerousStoneCutter.getApi();
    }

    void addBloodParticleProvider(Function<Entity, ParticleOptions> provider);
}
