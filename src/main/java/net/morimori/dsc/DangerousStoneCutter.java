package net.morimori.dsc;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;

public class DangerousStoneCutter implements ModInitializer {
    public static final DSCConfig CONFIG = AutoConfig.register(DSCConfig.class, Toml4jConfigSerializer::new).getConfig();
    public static final DamageSource CUTTING = new DamageSource("cutting");
    public static final String MODID = "dangerousstonecutter";

    @Override
    public void onInitialize() {
    }

    public static void huntStoneCutterDamage(Level level, BlockPos blockPos, Entity entity) {
        if (!level.isClientSide()) {
            if (!(entity instanceof ItemEntity)) {
                entity.hurt(CUTTING, CONFIG.damage);
                level.playSound(null, blockPos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 0.3f, 1f);
                if (entity instanceof LivingEntity)
                    ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 2, false, false));
            }
        }
    }
}
