package net.morimori0317.dsc;

public class DefaultDSCConfig implements DSCConfig {

    @Override
    public float getDamage() {
        return 3f;
    }

    @Override
    public boolean isEnableJudgmentDangerous() {
        return true;
    }

    @Override
    public boolean isEnableBloodParticle() {
        return true;
    }
}
