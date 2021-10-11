package com.github.meteorcraft;

import com.github.meteorcraft.entity.rocket.AbstractRocketEntity;
import com.github.meteorcraft.items.RocketItem;
import com.github.meteorcraft.trinkets.OxygenTankItem;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.github.meteorcraft.MeteorCraft.MOD_ID;

public class MeteorItems {
    public static final ItemGroup GROUP = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "group"),
            () -> new ItemStack(register(new Identifier(MOD_ID, "meteorcraft"), new Item(new FabricItemSettings()))));

    public static final Item RAW_TIN = register(new Identifier(MOD_ID, "raw_tin"), new Item(new FabricItemSettings().group(GROUP)));
    public static final Item TIN_INGOT = register(new Identifier(MOD_ID, "tin_ingot"), new Item(new FabricItemSettings().group(GROUP)));
    public static final Item OXYGEN_TANK = register(new Identifier(MOD_ID, "oxygen_tank"), new OxygenTankItem(new FabricItemSettings().group(GROUP).maxCount(1)));
    public static final Item TIER1_ROCKET = register(new Identifier(MOD_ID, "tier1_rocket"), new RocketItem(new FabricItemSettings().group(GROUP).maxCount(1), AbstractRocketEntity.RocketTier.TIER1));
    public static final Item STEEL_INGOT = register(new Identifier(MOD_ID, "steel_ingot"), new Item(new FabricItemSettings().group(GROUP)));
    public static final Item ROCKET_PLATE = register(new Identifier(MOD_ID, "rocket_plate"), new Item(new FabricItemSettings().group(GROUP)));
    public static final Item RAW_ALUMINUM = register(new Identifier(MOD_ID, "raw_aluminum"), new Item(new FabricItemSettings().group(GROUP)));
    public static final Item TIN_ALUMINUM = register(new Identifier(MOD_ID, "aluminum_ingot"), new Item(new FabricItemSettings().group(GROUP)));
    public static final Item COMPRESSED_IRON = register(new Identifier(MOD_ID, "compressed_iron"), new Item(new FabricItemSettings().group(GROUP)));
    public static final Item COMPRESSED_STEEL = register(new Identifier(MOD_ID, "compressed_steel"), new Item(new FabricItemSettings().group(GROUP)));

    private static Item register(Identifier identifier, Item item) {
        Registry.register(Registry.ITEM, identifier, item);
        return item;
    }

    public static void call() {
    }

    public static Item getRocketItem(AbstractRocketEntity.RocketTier tier) {
        return switch (tier) {
            case TIER1 -> TIER1_ROCKET;
            default -> throw new IllegalStateException("Unexpected value: " + tier);
        };
    }
}
