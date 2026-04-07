package com.wennest.placeable.mixin;

import com.wennest.placeable.util.PlacementContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Client-side mixin to track when a player is breaking a block.
 * This complements the server-side mixin for single-player worlds.
 */
@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {

    /**
     * Set the player breaking flag before attempting to break a block on the client.
     */
    @Inject(method = "breakBlock", at = @At("HEAD"))
    private void beforeClientBreakBlock(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        // Set flag to indicate a player is breaking a block.
        PlacementContext.setPlayerBreaking(true);
        if (MinecraftClient.getInstance().player != null) {
            PlacementContext.setHasDebugStick(MinecraftClient.getInstance().player.getOffHandStack().isOf(Items.DEBUG_STICK));
        }
    }

    /**
     * Clear the player breaking flag after the break completes on the client.
     */
    @Inject(method = "breakBlock", at = @At("RETURN"))
    private void afterClientBreakBlock(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        // Clear the flag
        PlacementContext.clear();
    }

    /**
     * Also track when starting to break a block (for continuous breaking).
     */
    @Inject(method = "attackBlock", at = @At("HEAD"))
    private void beforeAttackBlock(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        // Set flag when player starts attacking a block
        PlacementContext.setPlayerBreaking(true);
        if (MinecraftClient.getInstance().player != null) {
            PlacementContext.setHasDebugStick(MinecraftClient.getInstance().player.getOffHandStack().isOf(Items.DEBUG_STICK));
        }
    }

    /**
     * Clear flag after attack block.
     */
    @Inject(method = "attackBlock", at = @At("RETURN"))
    private void afterAttackBlock(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        // Clear the flag
        PlacementContext.clear();
    }
}