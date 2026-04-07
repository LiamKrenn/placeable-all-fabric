package com.wennest.placeable.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.wennest.placeable.Placeable;
import com.wennest.placeable.PlaceableConfig;
import com.wennest.placeable.util.PlacementContext;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class AbstractBlockMixin {

    @Shadow
    public abstract BlockState asBlockState();

    /**
     * Modifies the return value of canPlaceAt to allow universal placement.
     * This catches ALL blocks, including those that override canPlaceAt.
     * 
     * Logic:
     * 1. Player placing WITH debug stick -> allow universal placement
     * 2. Player placing WITHOUT debug stick -> normal rules, but preserve neighbors
     * 3. Player breaking with floating enabled -> preserve floating blocks
     * 4. World generation -> normal rules
     */
    @ModifyReturnValue(method = "canPlaceAt", at = @At("RETURN"))
    private boolean allowUniversalPlacement(boolean original, WorldView world, BlockPos pos) {
        // If already true, don't change it
        if (original) {
            return true;
        }

        BlockState state = asBlockState();
        
        // Check if this block should have universal placement
        if (!Placeable.shouldApplyUniversalPlacement(state.getBlock())) {
            return original;
        }

        PlaceableConfig config = Placeable.getConfig();

        // Handle player breaking blocks - preserve floating blocks only when the player is using a debug stick.
        if (PlacementContext.isPlayerBreaking()) {
            return PlacementContext.isDebugStickActive() && config.allowFloatingBlocks;
        }

        // Handle player placement context
        if (PlacementContext.isPlayerPlacing()) {
            if (PlacementContext.isDebugStickActive()) {
                // Get the block below
                BlockPos belowPos = pos.down();
                BlockState blockBelow = world.getBlockState(belowPos);
                
                // If air below, only allow if floating blocks is enabled
                if (blockBelow.isAir()) {
                    return config.allowFloatingBlocks;
                }
                
                // There's a block below - now check if it has a top rim or if we allow placement anyway
                if (config.allowFloatingBlocks) {
                    // If floating blocks is enabled, allow placement on anything
                    return true;
                }
                
                // Check if the block below has a valid top surface
                // This includes: blocks with top rim, leaves, or dirt path
                boolean hasTopRim = Block.hasTopRim(world, belowPos);
                boolean isLeaves = blockBelow.isIn(BlockTags.LEAVES);
                boolean isDirtPath = blockBelow.getBlock().getTranslationKey().contains("dirt_path");
                
                return hasTopRim || isLeaves || isDirtPath;
            } else {
                // Player placing WITHOUT debug stick
                // Preserve existing blocks during neighbor updates, but don't allow new invalid placements
                // This prevents breaking blocks that were placed with debug stick
                // Return true if the block already exists (neighbor update), false for new placement
                return !world.getBlockState(pos).isAir();
            }
        }

        // Not a player action - use normal rules (world generation, etc.)
        return original;
    }
}