package com.github.meteorcraft;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MeteorBlocks {
    private static final String MOD_ID = "meteorcraft";
    public static final Block MOON_STONE = register(new Identifier(MOD_ID, "moonstone"), new Block(FabricBlockSettings.of(Material.STONE).breakByTool(FabricToolTags.PICKAXES, 2).strength(3.0F)));
    public static final Block TIN_ORE = register(new Identifier(MOD_ID, "tin_ore"), new OreBlock(FabricBlockSettings.of(Material.STONE).breakByTool(FabricToolTags.PICKAXES, 2).strength(4.0F)));
    public static final Block DEEPSLATE_TIN_ORE = register(new Identifier(MOD_ID, "deepslate_tin_ore"), new OreBlock(FabricBlockSettings.of(Material.STONE).breakByTool(FabricToolTags.PICKAXES, 2).strength(5.5F)));

    private static Block register(Identifier identifier, Block block) {
        Registry.register(Registry.BLOCK, identifier, block);
        Registry.register(Registry.ITEM, identifier, new BlockItem(block, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        return block;
    }

    public static void call() {
    }
}
