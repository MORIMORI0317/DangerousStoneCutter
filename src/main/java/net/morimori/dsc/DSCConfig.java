package net.morimori.dsc;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = DangerousStoneCutter.MODID)
@Config.Gui.Background("textures/block/stonecutter_top.png")
public class DSCConfig implements ConfigData {
    public float damage = 3f;
    public boolean AIJudgmentDangerous = true;
}
