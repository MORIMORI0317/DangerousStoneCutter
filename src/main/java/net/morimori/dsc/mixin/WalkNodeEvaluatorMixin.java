package net.morimori.dsc.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.morimori.dsc.DangerousStoneCutter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WalkNodeEvaluator.class)
public class WalkNodeEvaluatorMixin {
    @Inject(method = "getBlockPathTypeRaw", at = @At("RETURN"), cancellable = true)
    private static void getBlockPathTypeRaw(BlockGetter blockGetter, BlockPos blockPos, CallbackInfoReturnable<BlockPathTypes> cir) {
        if (DangerousStoneCutter.CONFIG.AIJudgmentDangerous) {
            BlockState blockState = blockGetter.getBlockState(blockPos);
            if (blockState.is(Blocks.STONECUTTER))
                cir.setReturnValue(BlockPathTypes.DAMAGE_OTHER);
        }
    }
}
