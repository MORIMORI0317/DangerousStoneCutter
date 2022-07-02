package net.morimori0317.dsc;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.Entity;
import net.morimori0317.dsc.api.DangerousStoneCutterAPI;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@ApiStatus.Internal
public class DangerousStoneCutterAPIImpl implements DangerousStoneCutterAPI {
    private final List<Function<Entity, ParticleOptions>> bloodParticleListeners = new ArrayList<>();

    @Override
    public void addBloodParticleListener(Function<Entity, ParticleOptions> listener) {
        bloodParticleListeners.add(listener);
    }

    public List<Function<Entity, ParticleOptions>> getBloodParticleListeners() {
        return bloodParticleListeners;
    }
}
