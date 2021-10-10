package com.github.meteorcraft.entity.rocket;

import com.github.meteorcraft.screen.RocketScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.ArrayList;

public class Tier1RocketEntity extends AbstractTierRocketEntity {
    public static final EntityModelLayer LAYER = new EntityModelLayer(new Identifier("meteorcraft", "tier1_rocket"), "main");

    public Tier1RocketEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world, RocketTier.TIER1);
    }
}
