package net.morimori0317.dsc.fabric;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraft.client.gui.screens.Screen;
import net.morimori0317.dsc.DSCConfig;
import net.morimori0317.dsc.DangerousStoneCutter;

@Config(name = DangerousStoneCutter.MODID)
@Config.Gui.Background("textures/block/stonecutter_top.png")
public class DSCConfigFabric implements ConfigData, DSCConfig {

    public float damage = DSCConfig.DEFAULT.getDamage();

    public boolean enableJudgmentDangerous = DSCConfig.DEFAULT.isEnableJudgmentDangerous();

    public boolean enableBloodParticle = DSCConfig.DEFAULT.isEnableBloodParticle();

    @Override
    public float getDamage() {
        return damage;
    }

    @Override
    public boolean isEnableJudgmentDangerous() {
        return enableJudgmentDangerous;
    }

    @Override
    public boolean isEnableBloodParticle() {
        return enableBloodParticle;
    }

    public static DSCConfigFabric createConfig() {
        return AutoConfig.register(DSCConfigFabric.class, Toml4jConfigSerializer::new).getConfig();
    }

    public static Screen createConfigScreen(Screen parent) {
        return AutoConfig.getConfigScreen(DSCConfigFabric.class, parent).get();
    }
}
