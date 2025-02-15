package com.github.meteorcraft;

import com.github.meteorcraft.entity.rocket.AbstractRocketEntity;
import com.github.meteorcraft.items.RocketItem;
import com.github.meteorcraft.trinkets.OxygenTankItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.github.meteorcraft.MeteorCraft.MOD_ID;

public class MeteorItems {
    public static final RegistryKey<Item> METEORCRAFT_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "meteorcraft"));
    public static final Item METEORCRAFT_ITEM = register(new Item(new Item.Settings().registryKey(METEORCRAFT_ITEM_KEY)), METEORCRAFT_ITEM_KEY);

    public static final RegistryKey<ItemGroup> GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(MOD_ID, "item_group"));
    public static final ItemGroup GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(METEORCRAFT_ITEM))
            .displayName(Text.translatable("itemGroup.meteorcraft"))
            .build();

    public static final RegistryKey<Item> RAW_TIN_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "raw_tin"));
    public static final Item RAW_TIN = register(new Item(new Item.Settings().registryKey(RAW_TIN_KEY)), RAW_TIN_KEY);

    public static final RegistryKey<Item> TIN_INGOT_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "tin_ingot"));
    public static final Item TIN_INGOT = register(new Item(new Item.Settings().registryKey(TIN_INGOT_KEY)), TIN_INGOT_KEY);

    public static final RegistryKey<Item> OXYGEN_TANK_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "oxygen_tank"));
    public static final Item OXYGEN_TANK = register(new OxygenTankItem(new Item.Settings().maxCount(1).registryKey(OXYGEN_TANK_KEY)), OXYGEN_TANK_KEY);

    public static final RegistryKey<Item> TIER1_ROCKET_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "tier1_rocket"));
    public static final Item TIER1_ROCKET = register(new RocketItem(new Item.Settings().maxCount(1).registryKey(TIER1_ROCKET_KEY), AbstractRocketEntity.RocketTier.TIER1), TIER1_ROCKET_KEY);

    public static final RegistryKey<Item> STEEL_INGOT_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "steel_ingot"));
    public static final Item STEEL_INGOT = register(new Item(new Item.Settings().registryKey(STEEL_INGOT_KEY)), STEEL_INGOT_KEY);

    public static final RegistryKey<Item> ROCKET_PLATE_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "rocket_plate"));
    public static final Item ROCKET_PLATE = register(new Item(new Item.Settings().registryKey(ROCKET_PLATE_KEY)), ROCKET_PLATE_KEY);

    public static final RegistryKey<Item> RAW_ALUMINUM_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "raw_aluminum"));
    public static final Item RAW_ALUMINUM = register(new Item(new Item.Settings().registryKey(RAW_ALUMINUM_KEY)), RAW_ALUMINUM_KEY);

    public static final RegistryKey<Item> ALUMINUM_INGOT_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "aluminum_ingot"));
    public static final Item ALUMINUM_INGOT = register(new Item(new Item.Settings().registryKey(ALUMINUM_INGOT_KEY)), ALUMINUM_INGOT_KEY);

    public static final RegistryKey<Item> COMPRESSED_IRON_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "compressed_iron"));
    public static final Item COMPRESSED_IRON = register(new Item(new Item.Settings().registryKey(COMPRESSED_IRON_KEY)), COMPRESSED_IRON_KEY);

    public static final RegistryKey<Item> COMPRESSED_STEEL_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "compressed_steel"));
    public static final Item COMPRESSED_STEEL = register(new Item(new Item.Settings().registryKey(COMPRESSED_STEEL_KEY)), COMPRESSED_STEEL_KEY);

    private static Item register(Item item, RegistryKey<Item> registryKey) {
        return Registry.register(Registries.ITEM, registryKey.getValue(), item);
    }

    public static void call() {
        Registry.register(Registries.ITEM_GROUP, GROUP_KEY, GROUP);

        ItemGroupEvents.modifyEntriesEvent(GROUP_KEY).register((group) -> {
            group.add(RAW_TIN);
            group.add(TIN_INGOT);
            group.add(OXYGEN_TANK);
            group.add(TIER1_ROCKET);
            group.add(STEEL_INGOT);
            group.add(ROCKET_PLATE);
            group.add(RAW_ALUMINUM);
            group.add(ALUMINUM_INGOT);
            group.add(COMPRESSED_IRON);
            group.add(COMPRESSED_STEEL);
        });
    }

    public static Item getRocketItem(AbstractRocketEntity.RocketTier tier) {
        return switch (tier) {
            case TIER1 -> TIER1_ROCKET;
            default -> throw new IllegalStateException("Unexpected value: " + tier);
        };
    }
}
