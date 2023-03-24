package net.morimori0317.dsc.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.morimori0317.dsc.DangerousStoneCutter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class BlockBehaviourBlockStateBaseMixin {
    @Shadow
    protected abstract BlockState asState();

    @Inject(method = "entityInside", at = @At("HEAD"))
    private void entityInside(Level level, BlockPos blockPos, Entity entity, CallbackInfo ci) {
        if (DangerousStoneCutter.isSupportStoneCutter(this.asState()))
            DangerousStoneCutter.huntStoneCutterDamage(level, this.asState(), blockPos, entity);
    }
}
