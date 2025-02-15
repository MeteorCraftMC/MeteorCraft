package com.github.meteorcraft;

import com.github.meteorcraft.entity.rocket.AbstractRocketEntity;
import com.github.meteorcraft.entity.rocket.LanderEntity;
import com.github.meteorcraft.entity.rocket.Tier1RocketEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import static com.github.meteorcraft.MeteorCraft.MOD_ID;

@SuppressWarnings("unchecked")
public class MeteorEntityTypes {
    public static final EntityType<AbstractRocketEntity> TIER_1_ROCKET_ENTITY = (EntityType<AbstractRocketEntity>) register(
            Identifier.of(MOD_ID, "tier1_rocket"),
            EntityType.Builder.create(Tier1RocketEntity::new, SpawnGroup.MISC)
    );
    public static final EntityType<AbstractRocketEntity> LANDER_ENTITY = (EntityType<AbstractRocketEntity>) register(
            Identifier.of(MOD_ID, "lander"),
            EntityType.Builder.create(LanderEntity::new, SpawnGroup.MISC)
    );

    private static EntityType<?> register(Identifier identifier, EntityType.Builder<?> type) {
        RegistryKey<EntityType<?>> key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, identifier);
        return Registry.register(Registries.ENTITY_TYPE, key, type.build(key));
    }

    public static void call() {
    }
}
