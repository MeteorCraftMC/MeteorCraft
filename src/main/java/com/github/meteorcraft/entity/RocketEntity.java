package com.github.meteorcraft.entity;

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

public class RocketEntity extends LivingEntity {
    public static final EntityModelLayer ROCKET_LAYER = new EntityModelLayer(new Identifier("meteorcraft", "rocket"), "main");
    private boolean launched = false;
    private boolean falling = false;
    private int acceleration = 0;

    public RocketEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
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
            launched = true;
            return ActionResult.success(true);
        }
    }

    @Override
    public void tick() {
        if (launched) {
            acceleration++;
            setVelocity(0, acceleration / 100D, 0);
            if (getY() > 5000) {
                launched = false;
                acceleration = 0;
                if (getPassengerList().contains(MinecraftClient.getInstance().player)) {
                    MinecraftClient.getInstance().execute(() -> MinecraftClient.getInstance().setScreen(new RocketScreen()));
                }
                remove(RemovalReason.CHANGED_DIMENSION);
            }
        }
        if (falling) {
            acceleration++;
            setVelocity(0, -acceleration / 100D, 0);
            for (Entity entity : getPassengerList()) {
                entity.fallDistance = 0;
            }
            if (isOnGround()) {
                remove(RemovalReason.DISCARDED);
            }
        }
        super.tick();
    }

    public void falling() {
        falling = true;
    }
}
