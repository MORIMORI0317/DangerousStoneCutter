package net.morimori0317.dsc.forge.mixin;

/*

@Mixin(PathNodeDefaults.class)
public class RadiumPathNodeDefaultsMixin {
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
*/
