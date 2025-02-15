package com.github.meteorcraft.entity.rocket;

import com.github.meteorcraft.MeteorEntityTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.VehicleEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.ArrayList;

public abstract class AbstractRocketEntity extends VehicleEntity {
    protected int acceleration = 0;

    public AbstractRocketEntity(EntityType<? extends VehicleEntity> entityType, World world) {
        super(entityType, world);
    }

    public static EntityType<AbstractRocketEntity> getRocketEntityType(RocketTier tier) {
        return switch (tier) {
            case LANDER -> MeteorEntityTypes.LANDER_ENTITY;
            case TIER1 -> MeteorEntityTypes.TIER_1_ROCKET_ENTITY;
        };
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        if (hasPassengers()) return ActionResult.PASS;
        else {
            player.startRiding(this);
            ridingStart();
            return ActionResult.SUCCESS;
        }
    }

    protected abstract void ridingStart();

    public enum RocketTier {
        LANDER, TIER1
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
    }
}
