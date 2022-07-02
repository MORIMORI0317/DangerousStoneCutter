package net.morimori0317.dsc.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.morimori0317.dsc.DangerousStoneCutter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityType.class)
public class EntityTypeMixin {
    @Inject(method = "isBlockDangerous", at = @At("RETURN"), cancellable = true)
    private void isBlockDangerous(BlockState blockState, CallbackInfoReturnable<Boolean> cir) {
        if (DangerousStoneCutter.getConfig().isEnableJudgmentDangerous() && DangerousStoneCutter.isSupportStoneCutter(blockState))
            cir.setReturnValue(true);
    }
}
