package net.morimori0317.dangerousstonecutter.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.morimori0317.dangerousstonecutter.DangerousStoneCutter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WalkNodeEvaluator.class)
public class WalkNodeEvaluatorMixin {
    @Inject(method = "getPathTypeFromState", at = @At("RETURN"), cancellable = true)
    private static void getBlockPathTypeRaw(BlockGetter blockGetter, BlockPos blockPos, CallbackInfoReturnable<PathType> cir) {
        BlockState blockState = blockGetter.getBlockState(blockPos);
        if (DangerousStoneCutter.getConfig().isEnableMobAvoidStonecutter() && DangerousStoneCutter.isSupportStoneCutter(blockState)) {
            cir.setReturnValue(PathType.DAMAGE_OTHER);
        }
    }
}
