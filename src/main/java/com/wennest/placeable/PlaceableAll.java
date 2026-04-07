package com.wennest.placeable;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PlaceableAll implements ModInitializer {
    public static final String MODID = "placeableall";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public static boolean shouldApplyUniversalPlacement(Block block) {
        PlaceableConfig config = getConfig();
        if (config == null || !config.enableUniversalPlacement) {
            return false;
        }

        // Get the block's registry ID
        Identifier blockId = Registries.BLOCK.getId(block);
        String blockIdString = blockId.toString();

        // If the list is empty, affect all blocks
        if (config.blockList.isEmpty()) {
            return true;
        }

        boolean inList = config.blockList.contains(blockIdString);

        // If it's a blocklist: return true if NOT in list
        // If it's an allowlist: return true if IN list
        return config.useAsBlocklist ? inList : !inList;
    }

    public static PlaceableConfig getConfig() {
        return AutoConfig.getConfigHolder(PlaceableConfig.class).get();
    }

    @Override
    public void onInitialize() {
        long loadTook = System.currentTimeMillis();
        AutoConfig.register(PlaceableConfig.class, GsonConfigSerializer::new);
        LOGGER.info("Placeable All mod loaded in {} ms!", System.currentTimeMillis() - loadTook);
    }
}