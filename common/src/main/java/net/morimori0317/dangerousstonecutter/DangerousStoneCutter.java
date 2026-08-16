package net.morimori0317.dangerousstonecutter;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.animal.horse.SkeletonHorse;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.breeze.Breeze;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.morimori0317.dangerousstonecutter.api.DangerousStoneCutterAPI;
import net.morimori0317.dangerousstonecutter.explatform.DSCExpectPlatform;

import java.util.function.Function;

public final class DangerousStoneCutter {
    public static final String MODID = "dangerousstonecutter";
    private static final DangerousStoneCutterAPIImpl API = new DangerousStoneCutterAPIImpl();

    public static final ResourceKey<DamageType> CUTTING = ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(MODID, "cutting"));
    public static final TagKey<Block> DANGEROUS_CUTTER_TAG = bindTag(ResourceLocation.fromNamespaceAndPath(MODID, "dangerous_cutter"));

    public static DangerousStoneCutterAPI getApi() {
        return API;
    }

    public static void init() {
        // Do Nothing
    }

    private static TagKey<Block> bindTag(ResourceLocation location) {
        return TagKey.create(Registries.BLOCK, location);
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

            var dmg = new CuttingDamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(CUTTING), blockState);
            boolean flg = entity.isInvisible() || entity.isInvulnerableTo(dmg);
            if (entity instanceof Player player)
                flg |= player.getAbilities().invulnerable;
            entity.hurt(dmg, Math.max(getConfig().getDamage(), 0f));
            if (flg) return;
            level.playSound(null, blockPos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 0.3f, 1f);
            //  if (entity instanceof LivingEntity)
            //      ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 2, false, false));

            if (getConfig().isEnableBloodParticle()) {
                DSCExpectPlatform.sendBloodParticlePacket((ServerLevel) level, entity.chunkPosition(), entity.getId(), blockPos);
            }
        }
    }

    public static boolean isSupportStoneCutter(BlockState state) {
        return state.is(DANGEROUS_CUTTER_TAG);
    }

    public static ParticleOptions getBloodParticle(Entity entity) {
        for (Function<Entity, ParticleOptions> bloodParticleListener : API.getBloodParticleProviders()) {
            var r = bloodParticleListener.apply(entity);
            if (r != null) return r;
        }

        if (entity instanceof Allay) {
            return new BlockParticleOption(ParticleTypes.BLOCK, Blocks.AMETHYST_BLOCK.defaultBlockState());
        } else if (entity instanceof Bee) {
            return new BlockParticleOption(ParticleTypes.BLOCK, Blocks.HONEYCOMB_BLOCK.defaultBlockState());
        } else if (entity instanceof Skeleton || entity instanceof SkeletonHorse || entity instanceof Bogged || entity instanceof Stray) {
            return new BlockParticleOption(ParticleTypes.BLOCK, Blocks.BONE_BLOCK.defaultBlockState());
        } else if (entity instanceof Breeze) {
            return new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Items.WIND_CHARGE));
        } else if (entity instanceof Spider) {
            return new BlockParticleOption(ParticleTypes.BLOCK, Blocks.COBWEB.defaultBlockState());
        } else if (entity instanceof Creeper) {
            return new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Items.GUNPOWDER));
        } else if (entity instanceof EnderMan || entity instanceof EnderDragon || entity instanceof Endermite || entity instanceof Shulker) {
            return ParticleTypes.PORTAL;
        } else if (entity instanceof IronGolem) {
            return new BlockParticleOption(ParticleTypes.BLOCK, Blocks.IRON_BLOCK.defaultBlockState());
        } else if (entity instanceof SnowGolem) {
            return new BlockParticleOption(ParticleTypes.BLOCK, Blocks.SNOW_BLOCK.defaultBlockState());
        } else if (entity instanceof Warden) {
            return new BlockParticleOption(ParticleTypes.BLOCK, Blocks.SCULK.defaultBlockState());
        } else if (entity instanceof WitherSkeleton || entity instanceof WitherBoss) {
            return new BlockParticleOption(ParticleTypes.BLOCK, Blocks.COAL_BLOCK.defaultBlockState());
        } else if (entity instanceof MagmaCube || entity instanceof Blaze) {
            return ParticleTypes.FLAME;
        } else if (entity instanceof Slime) {
            return new BlockParticleOption(ParticleTypes.BLOCK, Blocks.SLIME_BLOCK.defaultBlockState());
        } else if (entity instanceof ItemEntity itemEntity) {
            return new ItemParticleOption(ParticleTypes.ITEM, itemEntity.getItem());
        } else if (entity instanceof FallingBlockEntity fallingBlock) {
            return new BlockParticleOption(ParticleTypes.BLOCK, fallingBlock.getBlockState());
        } else if ((entity instanceof Mob || entity instanceof Player) && !(entity instanceof Vex)) {
            return new BlockParticleOption(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.defaultBlockState());
        }
        
        return null;
    }

    public static DSCConfig getConfig() {
        return DSCExpectPlatform.getConfig();
    }
}
