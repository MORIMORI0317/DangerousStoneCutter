
package net.morimori0317.dsc;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CuttingDamageSource extends DamageSource {
    private final BlockState blockState;

    public CuttingDamageSource(Holder<DamageType> holder, BlockState blockState) {
        super(holder);
        this.blockState = blockState;
    }

    @Override
    public Component getLocalizedDeathMessage(LivingEntity livingEntity) {
        LivingEntity livingEntity2 = livingEntity.getKillCredit();
        String string = "death.attack." + this.type().msgId();
        String string2 = string + ".player";
        var bname = blockState.getBlock().getName();
        return livingEntity2 != null ? Component.translatable(string2, livingEntity.getDisplayName(), livingEntity2.getDisplayName(), bname) : Component.translatable(string, livingEntity.getDisplayName(), bname);
    }
}

