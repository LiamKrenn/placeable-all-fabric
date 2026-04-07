# Placeable Plants Fork by Liam Krenn

This repository is a fork of the original Placeable Plants Fabric mod, maintained by Liam Krenn.

## What this fork does

- Enables placement of **any blocks** on most surfaces when a Debug Stick is held in the offhand.
- Respects vanilla placement rules when the Debug Stick is not present.
- Supports fences, walls, and other surfaces when `ignoreTopRim` is enabled.
- Supports optional floating placement when `allowFloatingBlocks` is enabled.
- Keeps world generation unchanged.

## Usage

1. Run `/give @s debug_stick`.
2. Place the Debug Stick in your offhand.
3. Hold a placeable plant block in your main hand.
4. Right-click to place.

## Configuration

The mod configuration is stored in `config/placeableall.json`.

Important config values:

- `enableUniversalPlacement`: enable or disable the mod.
- `ignoreTopRim`: allow placement on blocks without a normal top surface.
- `allowFloatingBlocks`: allow placement with no supporting block below.
- `blockList`: list of block IDs that the mod includes or excludes.
- `useAsBlocklist`: if true, `blockList` is excluded; if false, it is included.

## Notes

- This fork preserves normal Minecraft behavior when the Debug Stick is not active.
- Floating block preservation requires both `allowFloatingBlocks` and the Debug Stick.
- The `blockList` defaults to allow a set of common decoration blocks, but users can customize it.

## Credits

- Maintained by **Liam Krenn**.
- Forked from the original `wenwen357951/placeable-fabric` repository.
- Original feature concept by **Bisumto**.
