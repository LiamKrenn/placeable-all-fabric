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
        // Default allowlist - common decoration blocks
        blockList.add("snow");
        blockList.add("snow_block");
        
        // All carpet colors
        // blockList.add("white_carpet");
        // blockList.add("orange_carpet");
        // blockList.add("magenta_carpet");
        // blockList.add("light_blue_carpet");
        // blockList.add("yellow_carpet");
        // blockList.add("lime_carpet");
        // blockList.add("pink_carpet");
        // blockList.add("gray_carpet");
        // blockList.add("light_gray_carpet");
        // blockList.add("cyan_carpet");
        // blockList.add("purple_carpet");
        // blockList.add("blue_carpet");
        // blockList.add("brown_carpet");
        // blockList.add("green_carpet");
        // blockList.add("red_carpet");
        // blockList.add("black_carpet");
        
        // Torches
        // blockList.add("torch");
        // blockList.add("wall_torch");
        // blockList.add("soul_torch");
        // blockList.add("soul_wall_torch");
        // blockList.add("redstone_torch");
        // blockList.add("redstone_wall_torch");
        
        // Lanterns
        // blockList.add("lantern");
        // blockList.add("soul_lantern");
        
        // Vegetation
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
        
        // Flowers
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
        
        // Mushrooms
        blockList.add("red_mushroom");
        blockList.add("brown_mushroom");
        
        // Saplings
        blockList.add("oak_sapling");
        blockList.add("spruce_sapling");
        blockList.add("birch_sapling");
        blockList.add("jungle_sapling");
        blockList.add("acacia_sapling");
        blockList.add("dark_oak_sapling");
        blockList.add("cherry_sapling");
        blockList.add("mangrove_propagule");
        blockList.add("bamboo_sapling");
        // blockList.add("bamboo");
        
        // Other decoration
        // blockList.add("ladder");
        // blockList.add("vine");
        // blockList.add("lever");
        // blockList.add("cactus");
        // blockList.add("sugar_cane");
        // blockList.add("bamboo");
        // blockList.add("lily_pad");
        
        // Rails (for decoration)
        // blockList.add("rail");
        // blockList.add("powered_rail");
        // blockList.add("detector_rail");
        // blockList.add("activator_rail");
        
        // Buttons (for decoration)
        // blockList.add("stone_button");
        // blockList.add("oak_button");
        // blockList.add("spruce_button");
        // blockList.add("birch_button");
        // blockList.add("jungle_button");
        // blockList.add("acacia_button");
        // blockList.add("dark_oak_button");
        // blockList.add("crimson_button");
        // blockList.add("warped_button");
        // blockList.add("mangrove_button");
        // blockList.add("cherry_button");
        // blockList.add("bamboo_button");
        
        // Pressure plates
        // blockList.add("stone_pressure_plate");
        // blockList.add("oak_pressure_plate");
        // blockList.add("spruce_pressure_plate");
        // blockList.add("birch_pressure_plate");
        // blockList.add("jungle_pressure_plate");
        // blockList.add("acacia_pressure_plate");
        // blockList.add("dark_oak_pressure_plate");
        // blockList.add("crimson_pressure_plate");
        // blockList.add("warped_pressure_plate");
        // blockList.add("mangrove_pressure_plate");
        // blockList.add("cherry_pressure_plate");
        // blockList.add("bamboo_pressure_plate");
        // blockList.add("light_weighted_pressure_plate");
        // blockList.add("heavy_weighted_pressure_plate");
    }
}