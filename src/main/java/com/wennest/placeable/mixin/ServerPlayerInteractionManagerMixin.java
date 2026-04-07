package com.wennest.placeable.mixin;

import com.wennest.placeable.util.PlacementContext;
import net.minecraft.block.BlockState;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin to track when a player is breaking a block.
 * This allows us to preserve floating blocks when a player breaks the support block.
 */
@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin {

    @Shadow
    public ServerPlayerEntity player;

    /**
     * Set the player breaking flag before attempting to break a block.
     * This ensures that neighbor updates triggered by the break are aware of the player context.
     */
    @Inject(method = "tryBreakBlock", at = @At("HEAD"))
    private void beforeBreakBlock(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        // Set flag to indicate a player is breaking a block
        PlacementContext.setPlayerBreaking(true);
    }

    /**
     * Clear the player breaking flag after the break completes.
     * Uses RETURN to catch both success and failure cases.
     */
    @Inject(method = "tryBreakBlock", at = @At("RETURN"))
    private void afterBreakBlock(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        // Clear the flag to prevent memory leaks and state pollution
        PlacementContext.clear();
    }
}