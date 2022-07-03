package net.morimori0317.dsc.fabric.mixin;

import me.jellysquid.mods.lithium.common.ai.pathing.PathNodeDefaults;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.morimori0317.dsc.DangerousStoneCutter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PathNodeDefaults.class)
public class LithiumPathNodeDefaultsMixin {
    @Inject(method = "getNeighborNodeType", at = @At("HEAD"), cancellable = true, remap = false)
    private static void getNeighborNodeType(BlockState state, CallbackInfoReturnable<BlockPathTypes> cir) {
        if (DangerousStoneCutter.getConfig().isEnableJudgmentDangerous() && DangerousStoneCutter.isSupportStoneCutter(state))
            cir.setReturnValue(BlockPathTypes.DANGER_OTHER);
    }

    @Inject(method = "getNodeType", at = @At("RETURN"), cancellable = true, remap = false)
    private static void getNodeType(BlockState state, CallbackInfoReturnable<BlockPathTypes> cir) {
        if (DangerousStoneCutter.getConfig().isEnableJudgmentDangerous() && DangerousStoneCutter.isSupportStoneCutter(state))
            cir.setReturnValue(BlockPathTypes.DAMAGE_OTHER);
    }
}
