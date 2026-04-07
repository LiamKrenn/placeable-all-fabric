package com.wennest.placeable;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

import java.util.HashSet;
import java.util.Set;

@Config(name = Placeable.MODID)
public class PlaceableConfig implements ConfigData {

    @Comment("Enable or disable universal block placement.")
    public boolean enableUniversalPlacement = true;

    @Comment("List of block IDs to control (e.g., 'snow', 'stone').")
    public Set<String> blockList = new HashSet<>();

    @Comment("If true, blocks in the list are EXCLUDED. If false, ONLY blocks in the list are affected.")
    public boolean useAsBlocklist = false;

    @Comment("Allow placement on blocks without a top rim (fences, walls, etc.).")
    public boolean ignoreTopRim = false;

    @Comment("Allow placement without any block below (floating in air). Overrides ignoreTopRim.")
    public boolean allowFloatingBlocks = false;

    public PlaceableConfig() {
        // Default allowlist: common decoration blocks
        blockList.add("snow");
        blockList.add("snow_block");

        blockList.add("short_grass");
        blockList.add("tall_grass");
        blockList.add("fern");
        blockList.add("large_fern");
        blockList.add("dead_bush");
        blockList.add("short_dry_grass");
        blockList.add("tall_dry_grass");
        blockList.add("cactus_flower");
        blockList.add("firefly_bush");
        blockList.add("leaf_litter");
        blockList.add("pink_petals");
        blockList.add("wildflowers");
        blockList.add("spore_blossom");
        blockList.add("flowering_azalea");
        blockList.add("azalea");
        blockList.add("big_dripleaf");
        blockList.add("big_dripleaf_stem");
        blockList.add("small_dripleaf");
        blockList.add("bush");
        blockList.add("sweet_berry_bush");

        blockList.add("dandelion");
        blockList.add("poppy");
        blockList.add("blue_orchid");
        blockList.add("allium");
        blockList.add("azure_bluet");
        blockList.add("red_tulip");
        blockList.add("orange_tulip");
        blockList.add("white_tulip");
        blockList.add("pink_tulip");
        blockList.add("oxeye_daisy");
        blockList.add("cornflower");
        blockList.add("lily_of_the_valley");
        blockList.add("wither_rose");
        blockList.add("sunflower");
        blockList.add("lilac");
        blockList.add("rose_bush");
        blockList.add("peony");
        blockList.add("closed_eyeblossom");
        blockList.add("open_eyeblossom");
        blockList.add("torchflower");
        blockList.add("pitcher_plant");

        blockList.add("red_mushroom");
        blockList.add("brown_mushroom");

        blockList.add("oak_sapling");
        blockList.add("spruce_sapling");
        blockList.add("birch_sapling");
        blockList.add("jungle_sapling");
        blockList.add("acacia_sapling");
        blockList.add("dark_oak_sapling");
        blockList.add("cherry_sapling");
        blockList.add("mangrove_propagule");
        blockList.add("bamboo_sapling");
    }
}
