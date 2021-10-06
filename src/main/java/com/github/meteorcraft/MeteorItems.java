package com.github.meteorcraft;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MeteorItems {
    private static final String MOD_ID = "meteorcraft";
    public static final Item RAW_TIN = register(new Identifier(MOD_ID, "raw_tin"), new Item(new FabricItemSettings().group(ItemGroup.MISC)));
    public static final Item TIN_INGOT = register(new Identifier(MOD_ID, "tin_ingot"), new Item(new FabricItemSettings().group(ItemGroup.MISC)));

    private static Item register(Identifier identifier, Item item) {
        Registry.register(Registry.ITEM, identifier, item);
        return item;
    }

    public static void call() {
    }
}
