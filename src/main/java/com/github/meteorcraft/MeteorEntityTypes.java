package com.github.meteorcraft;

import com.github.meteorcraft.entity.RocketEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unchecked")
public class MeteorEntityTypes {
    private static final String MOD_ID = "meteorcraft";
    public static final EntityType<RocketEntity> ROCKET_ENTITY = (EntityType<RocketEntity>) register(
            new Identifier(MOD_ID, "rocket"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, RocketEntity::new).build()
    );

    private static EntityType<?> register(Identifier identifier, EntityType<?> entry) {
        return Registry.register(Registry.ENTITY_TYPE, identifier, entry);
    }

    public static void call() {
    }
}
