package net.morimori0317.dsc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.horse.SkeletonHorse;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StonecutterBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.phys.Vec3;
import net.morimori0317.dsc.api.DangerousStoneCutterAPI;
import net.morimori0317.dsc.explatform.DSCExpectPlatform;

import java.util.function.Function;

public class DangerousStoneCutter {
    public static final String MODID = "dangerousstonecutter";
    public static final DamageSource CUTTING = new CuttingDamageSource();

    public static void init() {

    }

    public static void huntStoneCutterDamage(Level level, BlockState blockState, BlockPos blockPos, Entity entity) {
        var shape = blockState.getCollisionShape(level, blockPos);

        double enMaxX = entity.getBoundingBox().maxX;
        double enMaxZ = entity.getBoundingBox().maxZ;
        double enMinX = entity.getBoundingBox().minX;
        double enMinZ = entity.getBoundingBox().minZ;

        double shMaxX = shape.max(Direction.Axis.X) + blockPos.getX();
        double shMaxZ = shape.max(Direction.Axis.Z) + blockPos.getZ();
        double shMinX = shape.min(Direction.Axis.X) + blockPos.getX();
        double shMinZ = shape.min(Direction.Axis.Z) + blockPos.getZ();

        boolean flgIEX = enMaxX < shMaxX && enMaxX > shMinX || enMinX < shMaxX && enMinX > shMinX;
        boolean flgIEZ = enMaxZ < shMaxZ && enMaxZ > shMinZ || enMinZ < shMaxZ && enMinZ > shMinZ;
        boolean flgISX = shMaxX < enMaxX && shMaxX > enMinX || shMinX < enMaxX && shMinX > enMinX;
        boolean flgISZ = shMaxZ < enMaxZ && shMaxZ > enMinZ || shMinZ < enMaxZ && shMinZ > enMinZ;

        if (!(flgIEX || flgISX) || !(flgIEZ || flgISZ))
            return;

        if (entity.position().y() < shape.max(Direction.Axis.Y) + blockPos.getY())
            return;

        entity.makeStuckInBlock(blockState, new Vec3(0.800000011920929, 0.75, 0.800000011920929));
        if (!level.isClientSide()) {
            if (entity instanceof ItemEntity) return;
            boolean flg = entity.isInvisible() || entity.isInvulnerableTo(CUTTING);
            if (entity instanceof Player player)
                flg |= player.getAbilities().invulnerable;
            entity.hurt(CUTTING, getConfig().getDamage());
            if (flg) return;
            level.playSound(null, blockPos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 0.3f, 1f);
            //  if (entity instanceof LivingEntity)
            //      ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 2, false, false));

            if (getConfig().isEnableBloodParticle()) {
                LevelChunk lch = (LevelChunk) level.getChunk(entity.blockPosition());
                DSCExpectPlatform.sendBloodParticlePacket(lch, entity.getId(), blockPos);
            }
        }
    }

    public static boolean isSupportStoneCutter(BlockState state) {
        if (DSCExpectPlatform.isSupportStoneCutter(state))
            return true;
        return state.getBlock() instanceof StonecutterBlock;
    }

    public static ParticleOptions getBloodParticle(Entity entity) {
        var apiImpl = (DangerousStoneCutterAPIImpl) DangerousStoneCutterAPI.getInstance();
        for (Function<Entity, ParticleOptions> bloodParticleListener : apiImpl.getBloodParticleListeners()) {
            var r = bloodParticleListener.apply(entity);
            if (r != null) return r;
        }

        if (entity instanceof WitherSkeleton || entity instanceof WitherBoss)
            return new BlockParticleOption(ParticleTypes.BLOCK, Blocks.OBSIDIAN.defaultBlockState());

        if (entity instanceof Skeleton || entity instanceof SkeletonHorse)
            return new BlockParticleOption(ParticleTypes.BLOCK, Blocks.BONE_BLOCK.defaultBlockState());

        if (entity instanceof EnderMan || entity instanceof EnderDragon || entity instanceof Endermite || entity instanceof Shulker)
            return ParticleTypes.PORTAL;

        if (entity instanceof MagmaCube || entity instanceof Blaze)
            return ParticleTypes.FLAME;

        if (entity instanceof Slime)
            return new BlockParticleOption(ParticleTypes.BLOCK, Blocks.SLIME_BLOCK.defaultBlockState());

        if (entity instanceof IronGolem)
            return new BlockParticleOption(ParticleTypes.BLOCK, Blocks.IRON_BLOCK.defaultBlockState());

        if (entity instanceof Creeper)
            return new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Items.GUNPOWDER));

        if (entity instanceof ItemEntity itemEntity)
            return new ItemParticleOption(ParticleTypes.ITEM, itemEntity.getItem());

        if (entity instanceof FallingBlockEntity fallingBlock)
            return new BlockParticleOption(ParticleTypes.BLOCK, fallingBlock.getBlockState());

        if ((entity instanceof Mob || entity instanceof Player) && !(entity instanceof Vex))
            return new BlockParticleOption(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.defaultBlockState());
        return null;
    }

    public static DSCConfig getConfig() {
        return DSCExpectPlatform.getConfig();
    }
}
