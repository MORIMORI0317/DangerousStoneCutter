package net.morimori.dsc.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StonecutterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.morimori.dsc.DangerousStoneCutter;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(StonecutterBlock.class)
public class StonecutterBlockMixin extends Block {
    public StonecutterBlockMixin(Properties properties) {
        super(properties);
    }

    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
        DangerousStoneCutter.huntStoneCutterDamage(level, blockPos, entity);
    }
}
