package com.wennest.placeable.integration;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import com.wennest.placeable.PlaceableConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.HashSet;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            PlaceableConfig config = AutoConfig.getConfigHolder(PlaceableConfig.class).getConfig();
            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Text.translatable("config.placeable.title"));
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            // Main Category
            ConfigCategory mainCategory = builder.getOrCreateCategory(
                    Text.translatable("config.placeable.category.main")
            );
            
            mainCategory.addEntry(entryBuilder
                    .startBooleanToggle(
                            Text.translatable("config.placeable.option.enable"),
                            config.enableUniversalPlacement
                    )
                    .setDefaultValue(false)
                    .setTooltip(Text.translatable("config.placeable.option.enable.tooltip"))
                    .setSaveConsumer(newValue -> config.enableUniversalPlacement = newValue)
                    .build()
            );

            mainCategory.addEntry(entryBuilder
                    .startBooleanToggle(
                            Text.translatable("config.placeable.option.ignore_top_rim"),
                            config.ignoreTopRim
                    )
                    .setDefaultValue(false)
                    .setTooltip(Text.translatable("config.placeable.option.ignore_top_rim.tooltip"))
                    .setSaveConsumer(newValue -> config.ignoreTopRim = newValue)
                    .build()
            );

            mainCategory.addEntry(entryBuilder
                    .startBooleanToggle(
                            Text.translatable("config.placeable.option.floating"),
                            config.allowFloatingBlocks
                    )
                    .setDefaultValue(false)
                    .setTooltip(Text.translatable("config.placeable.option.floating.tooltip"))
                    .setSaveConsumer(newValue -> config.allowFloatingBlocks = newValue)
                    .build()
            );

            // Block List Category
            ConfigCategory blockListCategory = builder.getOrCreateCategory(
                    Text.translatable("config.placeable.category.blocklist")
            );
            
            blockListCategory.addEntry(entryBuilder
                    .startBooleanToggle(
                            Text.translatable("config.placeable.option.blocklist_mode"),
                            config.useAsBlocklist
                    )
                    .setDefaultValue(false)
                    .setTooltip(Text.translatable("config.placeable.option.blocklist_mode.tooltip"))
                    .setSaveConsumer(newValue -> config.useAsBlocklist = newValue)
                    .build()
            );
            
            blockListCategory.addEntry(entryBuilder
                    .startStrList(
                            Text.translatable("config.placeable.option.block_list"),
                            new ArrayList<>(config.blockList)
                    )
                    .setDefaultValue(new ArrayList<>())
                    .setTooltip(Text.translatable("config.placeable.option.block_list.tooltip"))
                    .setSaveConsumer(newValue -> config.blockList = new HashSet<>(newValue))
                    .build()
            );

            // Saving
            builder.setSavingRunnable(() ->
                    AutoConfig.getConfigHolder(PlaceableConfig.class).save()
            );

            return builder.build();
        };
    }
}