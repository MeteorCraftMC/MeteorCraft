package com.github.meteorcraft.entity.rocket;

import com.github.meteorcraft.MeteorEntityTypes;
import com.github.meteorcraft.screen.RocketScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
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

public abstract class AbstractRocketEntity extends LivingEntity {
    protected int acceleration = 0;

    public AbstractRocketEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    public static EntityType<AbstractRocketEntity> getRocketEntityType(RocketTier tier) {
        return switch (tier) {
            case LANDER -> MeteorEntityTypes.LANDER_ENTITY;
            case TIER1 -> MeteorEntityTypes.TIER_1_ROCKET_ENTITY;
        };
    }

    @Override
    public Iterable<ItemStack> getArmorItems() {
        return new ArrayList<>();
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    public Arm getMainArm() {
        return Arm.RIGHT;
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        if (hasPassengers()) return ActionResult.PASS;
        else {
            player.startRiding(this);
            ridingStart();
            return ActionResult.success(true);
        }
    }

    protected abstract void ridingStart();

    public enum RocketTier {
        LANDER, TIER1
    }
}
