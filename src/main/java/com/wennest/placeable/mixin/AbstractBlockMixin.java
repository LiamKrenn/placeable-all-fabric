package com.wennest.placeable.mixin;

import com.wennest.placeable.Placeable;
import com.wennest.placeable.PlaceableConfig;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {

  @Inject(method = "canPlaceAt", at = @At("HEAD"), cancellable = true)
  public void allowUniversalPlacement(BlockState state, WorldView world, BlockPos pos,
      CallbackInfoReturnable<Boolean> cir) {
    PlaceableConfig config = Placeable.getConfig();

    if (config == null || !config.enable || !config.enableUniversalPlacement) {
      return;
    }

    // Allow placement if there's a block below (not air)
    BlockState blockBelow = world.getBlockState(pos.down());
    if (!blockBelow.isAir()) {
      cir.setReturnValue(true);
    }
  }
}