package net.morimori0317.dangerousstonecutter.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.morimori0317.dangerousstonecutter.DangerousStoneCutter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class BlockBehaviourBlockStateBaseMixin {
    @Shadow
    protected abstract BlockState asState();

    @Inject(method = "entityInside", at = @At("TAIL"))
    private void entityInsideInject(Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean isPrecise, CallbackInfo ci) {
        if (DangerousStoneCutter.isSupportStoneCutter(this.asState())) {
            DangerousStoneCutter.huntStoneCutterDamage(level, this.asState(), pos, entity);
        }
    }
}
