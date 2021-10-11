package com.github.meteorcraft;

import com.github.meteorcraft.entity.rocket.AbstractRocketEntity;
import com.github.meteorcraft.items.RocketItem;
import com.github.meteorcraft.trinkets.OxygenTankItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.github.meteorcraft.MeteorCraft.MOD_ID;

public class MeteorItems {
    public static final Item RAW_TIN = register(new Identifier(MOD_ID, "raw_tin"), new Item(new FabricItemSettings().group(ItemGroup.MISC)));
    public static final Item TIN_INGOT = register(new Identifier(MOD_ID, "tin_ingot"), new Item(new FabricItemSettings().group(ItemGroup.MISC)));
    public static final Item OXYGEN_TANK = register(new Identifier(MOD_ID, "oxygen_tank"), new OxygenTankItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1)));
    public static final Item TIER1_ROCKET = register(new Identifier(MOD_ID, "tier1_rocket"), new RocketItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1), AbstractRocketEntity.RocketTier.TIER1));

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
