package net.morimori.dangerousstonecutter;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

@Mod("dangerousstonecutter")
public class DangerousStoneCutter {

    private static ConfigValue<Integer> damage_config;

    public DangerousStoneCutter() {
        MinecraftForge.EVENT_BUS.register(this);

        Pair<ServerConfigLoder, ForgeConfigSpec> seconfig = new ForgeConfigSpec.Builder().configure(ServerConfigLoder::new);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, seconfig.getRight());


    }

    public static final DamageSource CUTTING = new DamageSource("cutting");
    public static final Tag.Named<Block> STONE_CUTTER = BlockTags.bind("dangerousstonecutter:stone_cutter");

    @SubscribeEvent
    public void onLETickEvent(LivingEvent.LivingUpdateEvent e) {

        nomalStoneCutterDamege(e);

    }

    public void nomalStoneCutterDamege(LivingEvent.LivingUpdateEvent e) {

        LivingEntity le = (LivingEntity) e.getEntity();
        BlockState bl = le.level.getBlockState(new BlockPos(le.position()));
        if (STONE_CUTTER.contains(bl.getBlock())) {
            le.hurt(CUTTING, damage_config.get());

            le.level.playSound(null, new BlockPos(le.position()), SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 0.3f, 1);

            le.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 2, false, false));
        }
    }

    static class ServerConfigLoder {
        public ServerConfigLoder(ForgeConfigSpec.Builder builder) {
            builder.push("general");
            damage_config = builder.define("Cutting damage", 3);
            builder.pop();
        }
    }

}
