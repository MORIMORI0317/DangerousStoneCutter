package net.morimori0317.dsc.forge.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.morimori0317.dsc.DangerousStoneCutter;
import net.morimori0317.dsc.forge.integration.CorailWoodcutterIntegration;
import org.spongepowered.asm.mixin.Mixin;
import ovh.corail.woodcutter.block.WoodcutterBlock;

@Mixin(WoodcutterBlock.class)
public class WoodcutterBlockMixin extends HorizontalDirectionalBlock {

    protected WoodcutterBlockMixin(Properties arg) {
        super(arg);
    }

    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
        super.entityInside(blockState, level, blockPos, entity);
        if (CorailWoodcutterIntegration.isEnable())
            DangerousStoneCutter.huntStoneCutterDamage(level, blockState, blockPos, entity);
    }
}
