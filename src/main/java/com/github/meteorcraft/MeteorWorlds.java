package com.github.meteorcraft;

import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class MeteorWorlds {
    public static boolean isMoon(World world) {
        return world.getRegistryKey().getValue().equals(Identifier.of("meteorcraft:moon"));
    }

    public static boolean isOverworld(World world) {
        return world.getRegistryKey().getValue().equals(Identifier.of("minecraft:overworld"));
    }
}
