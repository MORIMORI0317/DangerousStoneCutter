package net.morimori0317.dsc.forge;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.morimori0317.dsc.DSCConfig;

public class DSCConfigForge implements DSCConfig {
    private static ForgeConfigSpec.ConfigValue<Double> DAMAGE;
    private static ForgeConfigSpec.ConfigValue<Boolean> ENABLE_BLOOD_PARTICLE;
    private static ForgeConfigSpec.ConfigValue<Boolean> ENABLE_JUDGMENT_DANGEROUS;
    private static ForgeConfigSpec.ConfigValue<Boolean> ENABLE_CORAIL_WOODCUTTER_INTEGRATION;

    public static void init() {
        var serverConfig = buildServerConfig(new ForgeConfigSpec.Builder()).build();
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, serverConfig);

        var commonConfig = buildCommonConfig(new ForgeConfigSpec.Builder()).build();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, commonConfig);
    }

    private static ForgeConfigSpec.Builder buildServerConfig(ForgeConfigSpec.Builder builder) {
        DAMAGE = builder.define("Cutting damage", (double) DSCConfig.DEFAULT.getDamage());
        return builder;
    }


    private static ForgeConfigSpec.Builder buildCommonConfig(ForgeConfigSpec.Builder builder) {
        ENABLE_BLOOD_PARTICLE = builder.define("Enable blood particle", DSCConfig.DEFAULT.isEnableBloodParticle());
        ENABLE_JUDGMENT_DANGEROUS = builder.define("Enable judgment dangerous", DSCConfig.DEFAULT.isEnableJudgmentDangerous());
        ENABLE_CORAIL_WOODCUTTER_INTEGRATION = builder.define("Enable corail woodcutter integration", true);
        return builder;
    }

    @Override
    public float getDamage() {
        return (float) (double) DAMAGE.get();
    }

    @Override
    public boolean isEnableJudgmentDangerous() {
        return ENABLE_JUDGMENT_DANGEROUS.get();
    }

    @Override
    public boolean isEnableBloodParticle() {
        return ENABLE_BLOOD_PARTICLE.get();
    }

    public boolean isEnableCorailWoodcutterIntegration() {
        return ENABLE_CORAIL_WOODCUTTER_INTEGRATION.get();
    }
}
