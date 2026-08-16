package net.morimori0317.dangerousstonecutter;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.Entity;
import net.morimori0317.dangerousstonecutter.api.DangerousStoneCutterAPI;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@ApiStatus.Internal
public class DangerousStoneCutterAPIImpl implements DangerousStoneCutterAPI {
    private final List<Function<Entity, ParticleOptions>> bloodParticleProviders = new ArrayList<>();

    @Override
    public void addBloodParticleProvider(Function<Entity, ParticleOptions> provider) {
        bloodParticleProviders.add(provider);
    }

    public List<Function<Entity, ParticleOptions>> getBloodParticleProviders() {
        return bloodParticleProviders;
    }
}