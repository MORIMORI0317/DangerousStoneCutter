package net.morimori0317.dangerousstonecutter;

public class DefaultDSCConfig implements DSCConfig {

    @Override
    public float getDamage() {
        return 3f;
    }

    @Override
    public boolean isEnableMobAvoidStonecutter() {
        return true;
    }

    @Override
    public boolean isEnableBloodParticle() {
        return true;
    }
}