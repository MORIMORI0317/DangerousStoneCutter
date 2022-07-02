package net.morimori0317.dsc.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.morimori0317.dsc.DangerousStoneCutter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WalkNodeEvaluator.class)
public class WalkNodeEvaluatorMixin {
    @Redirect(method = "checkNeighbourBlocks", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z", ordinal = 1))
    private static boolean checkNeighbourBlocks(BlockState state, Block block) {
        if (DangerousStoneCutter.getConfig().isEnableJudgmentDangerous() && DangerousStoneCutter.isSupportStoneCutter(state))
            return true;
        return state.is(block);
    }

    @Inject(method = "getBlockPathTypeRaw", at = @At("RETURN"), cancellable = true)
    private static void getBlockPathTypeRaw(BlockGetter blockGetter, BlockPos blockPos, CallbackInfoReturnable<BlockPathTypes> cir) {
        BlockState blockState = blockGetter.getBlockState(blockPos);
        if (DangerousStoneCutter.getConfig().isEnableJudgmentDangerous() && DangerousStoneCutter.isSupportStoneCutter(blockState))
            cir.setReturnValue(BlockPathTypes.DAMAGE_OTHER);
    }
}
