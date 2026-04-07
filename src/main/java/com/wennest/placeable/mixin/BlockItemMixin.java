package com.wennest.placeable.mixin;

import com.wennest.placeable.util.PlacementContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin to track when a player is placing a block.
 * This allows us to distinguish player placement from world generation.
 * Universal placement only works when player has debug stick in offhand.
 */
@Mixin(BlockItem.class)
public class BlockItemMixin {

    /**
     * Set the player placement flag before attempting to place a block.
     * Only sets the flag if there's a player in the context.
     * Sets hasDebugStick flag if player has debug stick in offhand.
     */
    @Inject(method = "place(Lnet/minecraft/item/ItemPlacementContext;)Lnet/minecraft/util/ActionResult;", at = @At("HEAD"))
    private void beforePlace(ItemPlacementContext context, CallbackInfoReturnable<ActionResult> cir) {
        // Only set flag if there's actually a player placing the block
        if (context.getPlayer() != null) {
            PlacementContext.setPlayerPlacing(true);
            
            // Check if player has debug stick in offhand
            boolean hasDebugStick = context.getPlayer().getOffHandStack().isOf(Items.DEBUG_STICK);
            PlacementContext.setHasDebugStick(hasDebugStick);
        }
    }

    /**
     * Clear the player placement flag after placement completes.
     * Uses RETURN to catch both success and failure cases.
     */
    @Inject(method = "place(Lnet/minecraft/item/ItemPlacementContext;)Lnet/minecraft/util/ActionResult;", at = @At("RETURN"))
    private void afterPlace(ItemPlacementContext context, CallbackInfoReturnable<ActionResult> cir) {
        // Always clear the flag to prevent memory leaks and state pollution
        PlacementContext.clear();
    }
}