package com.wennest.placeable.util;

/**
 * Helper class to track whether a block action is player-initiated.
 * Uses ThreadLocal to ensure thread-safety and avoid affecting world generation.
 */
public class PlacementContext {
    private static final ThreadLocal<Boolean> isPlayerPlacing = ThreadLocal.withInitial(() -> false);
    private static final ThreadLocal<Boolean> isPlayerBreaking = ThreadLocal.withInitial(() -> false);
    private static final ThreadLocal<Boolean> hasDebugStick = ThreadLocal.withInitial(() -> false);

    /**
     * Set whether we're currently in a player placement context.
     * Should be called before player-initiated block placement.
     */
    public static void setPlayerPlacing(boolean placing) {
        isPlayerPlacing.set(placing);
    }

    /**
     * Check if we're currently in a player placement context.
     * @return true if a player is placing a block, false otherwise (e.g., world generation)
     */
    public static boolean isPlayerPlacing() {
        return isPlayerPlacing.get();
    }

    /**
     * Set whether the player currently has a debug stick in their offhand.
     * Should be called during player-initiated block placement.
     */
    public static void setHasDebugStick(boolean hasStick) {
        hasDebugStick.set(hasStick);
    }

    /**
     * Check if the player has a debug stick in their offhand.
     * @return true if player has debug stick, false otherwise
     */
    public static boolean hasDebugStick() {
        return hasDebugStick.get();
    }

    /**
     * Set whether we're currently in a player breaking context.
     * Should be called before player-initiated block breaking.
     */
    public static void setPlayerBreaking(boolean breaking) {
        isPlayerBreaking.set(breaking);
    }

    /**
     * Check if we're currently in a player breaking context.
     * This is used to preserve floating blocks when the support is broken by a player.
     * @return true if a player is breaking a block, false otherwise
     */
    public static boolean isPlayerBreaking() {
        return isPlayerBreaking.get();
    }

    /**
     * Check if we're in any player-initiated context (placing or breaking).
     * @return true if a player is performing an action
     */
    public static boolean isPlayerAction() {
        return isPlayerPlacing.get() || isPlayerBreaking.get();
    }

    /**
     * Check if we're in a player action AND the player has a debug stick.
     * This is the condition for universal placement to be active.
     * @return true if player action with debug stick
     */
    public static boolean isDebugStickActive() {
        return isPlayerAction() && hasDebugStick.get();
    }

    /**
     * Clear all ThreadLocal values to prevent memory leaks.
     * Should be called after action completes.
     */
    public static void clear() {
        isPlayerPlacing.remove();
        isPlayerBreaking.remove();
        hasDebugStick.remove();
    }
}