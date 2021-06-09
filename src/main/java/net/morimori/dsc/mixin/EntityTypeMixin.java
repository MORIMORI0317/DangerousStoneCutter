package net.morimori.dsc.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.morimori.dsc.DangerousStoneCutter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityType.class)
public class EntityTypeMixin {
    @Inject(method = "isBlockDangerous", at = @At("RETURN"), cancellable = true)
    private void isBlockDangerous(BlockState blockState, CallbackInfoReturnable<Boolean> cir) {
        if (DangerousStoneCutter.CONFIG.AIJudgmentDangerous) {
            if (blockState.is(Blocks.STONECUTTER))
                cir.setReturnValue(true);
        }
    }
}
