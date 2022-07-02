package net.morimori0317.dsc;

public interface DSCConfig {
    DSCConfig DEFAULT = new DefaultDSCConfig();

    float getDamage();

    boolean isEnableJudgmentDangerous();

    boolean isEnableBloodParticle();
}
