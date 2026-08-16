package net.morimori0317.dangerousstonecutter;


public interface DSCConfig {
    DSCConfig DEFAULT = new DefaultDSCConfig();

    float getDamage();

    boolean isEnableMobAvoidStonecutter();

    boolean isEnableBloodParticle();
}