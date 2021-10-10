package com.github.meteorcraft.entity.rocket;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LanderEntity extends AbstractRocketEntity {
    public static final EntityModelLayer LAYER = new EntityModelLayer(new Identifier("meteorcraft", "lander"), "main");

    protected boolean launched = false;

    public LanderEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        acceleration++;
        setVelocity(0, -acceleration / 100D, 0);
        for (Entity entity : getPassengerList()) {
            if (!entity.getEntityWorld().getBlockState(entity.getBlockPos().add(0, -acceleration / 100D * 100, 0)).isAir()) {
                entity.stopRiding();
                if (entity instanceof LivingEntity livingEntity) {
                    livingEntity.addScoreboardTag("slowfalling");
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 1000000, 0, false, false), entity);
                }
            }
        }
        if (isOnGround()) {
            remove(RemovalReason.DISCARDED);
        }
        super.tick();
    }

    @Override
    protected void ridingStart() {
        launched = true;
    }
}
