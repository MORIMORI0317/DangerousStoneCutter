package net.morimori0317.dangerousstonecutter.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.morimori0317.dangerousstonecutter.DangerousStoneCutter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityType.class)
public class EntityTypeMixin {

    @Inject(method = "isBlockDangerous", at = @At("RETURN"), cancellable = true)
    private void isBlockDangerousInject(BlockState state, CallbackInfoReturnable<Boolean> cir) {

        if (cir.getReturnValue()) {
            return;
        }

        if (DangerousStoneCutter.getConfig().isEnableMobAvoidStonecutter() && DangerousStoneCutter.isSupportStoneCutter(state)) {
            cir.setReturnValue(true);
        }
    }

}
