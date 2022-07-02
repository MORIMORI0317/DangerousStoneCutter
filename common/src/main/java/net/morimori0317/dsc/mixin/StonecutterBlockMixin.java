package net.morimori0317.dsc.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StonecutterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.morimori0317.dsc.DangerousStoneCutter;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(StonecutterBlock.class)
public abstract class StonecutterBlockMixin extends Block {
    public StonecutterBlockMixin(Properties properties) {
        super(properties);
    }

    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
        super.entityInside(blockState, level, blockPos, entity);
        DangerousStoneCutter.huntStoneCutterDamage(level, blockPos, entity);
    }
}
