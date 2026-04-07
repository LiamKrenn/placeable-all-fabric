<p align="center">
    <br>
    <img src="src/main/resources/assets/placeable/icon.png" alt="Placeable Plants Logo" width="256">
    <br>
</p>

<h1 align="center">Placeable All (fork by Liam Krenn)</h1>

This repository contains a fork of the original Placeable Plants Fabric mod. The mod lets you place **any blocks** on almost any surface when a Debug Stick is held in your offhand.

## 🌱 What does this mod do?

- Allows **any block** placement on most surfaces when the player holds a Debug Stick in the offhand.
- Keeps vanilla placement rules when the Debug Stick is not present.
- Supports fences, walls, and other non-standard top surfaces when configured.
- Can optionally allow floating placement in air.
- Works without changing world generation.

## 🎮 How to use

1. Give yourself a Debug Stick: `/give @s debug_stick`
2. Hold the Debug Stick in your offhand.
3. Hold a placeable plant block in your main hand.
4. Right-click on the target surface.

> If the Debug Stick is not in your offhand, vanilla Minecraft placement rules are used.

## 🛠️ Main features

- `Enable Universal Placement`: turns the mod on or off.
- `Ignore Top Rim`: allows placement on fences, walls, and other blocks without a normal top surface.
- `Allow Floating Blocks`: enables placing supported blocks in mid-air.
- `Block List`: customize which blocks are affected by the mod.
- `useAsBlocklist`: choose whether the list excludes or includes blocks.

## 📦 Configuration

The mod uses `config/placeableall.json` and can be configured from the mod menu or by editing the file directly.

Example config:

```json
{
  "enableUniversalPlacement": true,
  "ignoreTopRim": false,
  "allowFloatingBlocks": false,
  "blockList": ["minecraft:fern", "minecraft:large_fern"],
  "useAsBlocklist": false
}
```

- `enableUniversalPlacement`: enables or disables the forked placement behavior.
- `ignoreTopRim`: lets you place on blocks like fences and walls.
- `allowFloatingBlocks`: allows midair placement.
- `blockList`: when empty, all blocks are affected. Otherwise, this list is used as either allowlist or blocklist.
- `useAsBlocklist`: if `true`, blocks in the list are excluded; if `false`, blocks in the list are included.

## 🌿 Supported blocks

This fork is designed for **any blocks**, configurable via the block list. By default, it includes common decoration blocks, but you can customize it to include or exclude specific blocks.

## 📘 Local docs

- `docs/README.md` contains a shorter documentation landing page for this fork.
- `docs/img/` holds the example recipe and config screenshots used in this repository.

## 📌 Credits

- Fork maintained by **Liam Krenn**.
- Originally based on the Placeable Plants mod and forked from the `wenwen357951/placeable-fabric` repository.
- Original feature concept by **Bisumto**.

[Original source]: https://github.com/wenwen357951/placeable-fabric
[Bisumto]: https://github.com/BisUmTo/placeable
