package com.github.meteorcraft;

import com.github.meteorcraft.entity.rocket.AbstractRocketEntity;
import com.github.meteorcraft.entity.rocket.LanderEntity;
import com.github.meteorcraft.entity.rocket.Tier1RocketEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.github.meteorcraft.MeteorCraft.MOD_ID;

@SuppressWarnings("unchecked")
public class MeteorEntityTypes {
    public static final EntityType<AbstractRocketEntity> TIER_1_ROCKET_ENTITY = (EntityType<AbstractRocketEntity>) register(
            new Identifier(MOD_ID, "tier1_rocket"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, Tier1RocketEntity::new).build()
    );
    public static final EntityType<AbstractRocketEntity> LANDER_ENTITY = (EntityType<AbstractRocketEntity>) register(
            new Identifier(MOD_ID, "lander"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, LanderEntity::new).build()
    );

    private static EntityType<?> register(Identifier identifier, EntityType<?> entry) {
        return Registry.register(Registry.ENTITY_TYPE, identifier, entry);
    }

    public static void call() {
    }
}
