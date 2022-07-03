package net.morimori0317.dsc;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CuttingDamageSource extends DamageSource {
    private final BlockState blockState;

    protected CuttingDamageSource(BlockState blockState) {
        super("cutting");
        this.blockState = blockState;
    }

    @Override
    public Component getLocalizedDeathMessage(LivingEntity livingEntity) {
        LivingEntity livingEntity2 = livingEntity.getKillCredit();
        String string = "death.attack." + this.msgId;
        String string2 = string + ".player";
        var bname = blockState.getBlock().getName();
        return livingEntity2 != null ? new TranslatableComponent(string2, livingEntity.getDisplayName(), livingEntity2.getDisplayName(), bname) : new TranslatableComponent(string, livingEntity.getDisplayName(), bname);
    }
}
