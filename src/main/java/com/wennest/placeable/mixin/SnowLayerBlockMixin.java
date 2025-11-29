package com.wennest.placeable.mixin;

import com.wennest.placeable.Placeable;
import com.wennest.placeable.PlaceableConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.SnowBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SnowBlock.class)
public class SnowLayerBlockMixin {

  @Inject(method = "canPlaceAt", at = @At("HEAD"), cancellable = true)
  public void allowUniversalSnowPlacement(BlockState state, WorldView world, BlockPos pos,
      CallbackInfoReturnable<Boolean> cir) {
    PlaceableConfig config = Placeable.getConfig();

    if (config == null || !config.enable || !config.enableUniversalPlacement) {
      return;
    }

    // Allow snow placement if there's any block below (not air)
    BlockState blockBelow = world.getBlockState(pos.down());
    if (!blockBelow.isAir()) {
      cir.setReturnValue(true);
    }
  }
}