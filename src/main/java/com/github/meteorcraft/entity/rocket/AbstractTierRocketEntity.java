package com.github.meteorcraft.entity.rocket;

import com.github.meteorcraft.screen.RocketScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.model.EntityModelLayer;
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

public abstract class AbstractTierRocketEntity extends AbstractRocketEntity {
    protected boolean launched = false;
    private final RocketTier tier;

    public AbstractTierRocketEntity(EntityType<? extends LivingEntity> entityType, World world, RocketTier tier) {
        super(entityType, world);
        this.tier = tier;
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
                    MinecraftClient.getInstance().execute(() -> MinecraftClient.getInstance().setScreen(new RocketScreen(tier)));
                }
                remove(RemovalReason.CHANGED_DIMENSION);
            }
        }
        super.tick();
    }

    @Override
    protected void ridingStart() {
        launched = true;
    }
}
