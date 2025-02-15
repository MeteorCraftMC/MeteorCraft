package com.github.meteorcraft.entity.rocket;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.ArrayList;

public class Tier1RocketEntity extends AbstractTierRocketEntity {
    public static final EntityModelLayer LAYER = new EntityModelLayer(Identifier.of("meteorcraft", "tier1_rocket"), "main");

    public Tier1RocketEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world, RocketTier.TIER1);
    }
}
