package net.morimori0317.dangerousstonecutter.neoforge;

import net.morimori0317.dangerousstonecutter.DSCConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class DSCConfigNeoForge implements DSCConfig {
    private static ModConfigSpec.ConfigValue<Double> DAMAGE;
    private static ModConfigSpec.ConfigValue<Boolean> ENABLE_BLOOD_PARTICLE;
    private static ModConfigSpec.ConfigValue<Boolean> ENABLE_MOB_AVOID_STONECUTTER;

    public static void init(ModContainer container) {
        var serverConfig = buildServerConfig(new ModConfigSpec.Builder()).build();
        container.registerConfig(ModConfig.Type.SERVER, serverConfig);

        var commonConfig = buildCommonConfig(new ModConfigSpec.Builder()).build();
        container.registerConfig(ModConfig.Type.COMMON, commonConfig);
    }

    private static ModConfigSpec.Builder buildServerConfig(ModConfigSpec.Builder builder) {
        DAMAGE = builder.define("Cutting damage", (double) DSCConfig.DEFAULT.getDamage());
        return builder;
    }

    private static ModConfigSpec.Builder buildCommonConfig(ModConfigSpec.Builder builder) {
        ENABLE_BLOOD_PARTICLE = builder.define("Enable blood particle", DSCConfig.DEFAULT.isEnableBloodParticle());
        ENABLE_MOB_AVOID_STONECUTTER = builder.define("Enable mobs to avoid the stonecutter", DSCConfig.DEFAULT.isEnableMobAvoidStonecutter());
        return builder;
    }

    @Override
    public float getDamage() {
        return (float) (double) DAMAGE.get();
    }

    @Override
    public boolean isEnableMobAvoidStonecutter() {
        return ENABLE_MOB_AVOID_STONECUTTER.get();
    }

    @Override
    public boolean isEnableBloodParticle() {
        return ENABLE_BLOOD_PARTICLE.get();
    }

}
