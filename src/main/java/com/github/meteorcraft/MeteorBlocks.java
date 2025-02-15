package com.github.meteorcraft;

import com.github.meteorcraft.block.OxygenCollectorBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import static com.github.meteorcraft.MeteorCraft.MOD_ID;

public class MeteorBlocks {
    public static final RegistryKey<Block> MOON_STONE_KEY = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "moonstone"));
    public static final Block MOON_STONE = register(new Block(AbstractBlock.Settings.create().strength(3.0F).registryKey(MOON_STONE_KEY)), MOON_STONE_KEY);

    public static final RegistryKey<Block> TIN_ORE_KEY = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "tin_ore"));
    public static final Block TIN_ORE = register(new Block(AbstractBlock.Settings.create().strength(4.0F).registryKey(TIN_ORE_KEY)), TIN_ORE_KEY);

    public static final RegistryKey<Block> DEEPSLATE_TIN_ORE_KEY = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "deepslate_tin_ore"));
    public static final Block DEEPSLATE_TIN_ORE = register(new Block(AbstractBlock.Settings.create().strength(5.5F).registryKey(DEEPSLATE_TIN_ORE_KEY)), DEEPSLATE_TIN_ORE_KEY);

    public static final RegistryKey<Block> OXYGEN_COLLECTOR_KEY = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "oxygen_collector"));
    public static final Block OXYGEN_COLLECTOR = register(new OxygenCollectorBlock(AbstractBlock.Settings.create().strength(6.0F).registryKey(OXYGEN_COLLECTOR_KEY)), OXYGEN_COLLECTOR_KEY);

    public static final RegistryKey<Block> ALUMINUM_ORE_KEY = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "aluminum_ore"));
    public static final Block ALUMINUM_ORE = register(new Block(AbstractBlock.Settings.create().strength(4.0F).registryKey(ALUMINUM_ORE_KEY)), ALUMINUM_ORE_KEY);

    public static final RegistryKey<Block> DEEPSLATE_ALUMINUM_ORE_KEY = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "deepslate_aluminum_ore"));
    public static final Block DEEPSLATE_ALUMINUM_ORE = register(new Block(AbstractBlock.Settings.create().strength(5.5F).registryKey(DEEPSLATE_ALUMINUM_ORE_KEY)), DEEPSLATE_ALUMINUM_ORE_KEY);

    public static Block register(Block block, RegistryKey<Block> blockKey) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, blockKey.getValue());

        BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, blockItem);

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    public static void call() {
        ItemGroupEvents.modifyEntriesEvent(MeteorItems.GROUP_KEY).register(group -> {
            group.add(MOON_STONE.asItem());
            group.add(TIN_ORE.asItem());
            group.add(DEEPSLATE_TIN_ORE.asItem());
            group.add(OXYGEN_COLLECTOR.asItem());
            group.add(ALUMINUM_ORE.asItem());
            group.add(DEEPSLATE_ALUMINUM_ORE.asItem());
        });
    }
}
