package com.github.meteorcraft.entity.rocket;

import com.github.meteorcraft.MeteorItems;
import com.github.meteorcraft.MeteorWorlds;
import com.github.meteorcraft.items.RocketItem;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LanderEntity extends AbstractRocketEntity {
    public static final EntityModelLayer LAYER = new EntityModelLayer(new Identifier("meteorcraft", "lander"), "main");
    private RocketTier tier;

    protected boolean launched = false;

    public LanderEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    public void setTier(RocketTier tier) {
        this.tier = tier;
    }

    @Override
    public void tick() {
        acceleration++;
        setVelocity(0, -acceleration / 100D, 0);
        if (MeteorWorlds.isOverworld(world)) {
            for (Entity entity : getPassengerList()) {
                if (!entity.getEntityWorld().getBlockState(entity.getBlockPos().add(0, -acceleration / 100D * 5, 0)).isAir()) {
                    entity.stopRiding();
                    if (entity instanceof LivingEntity livingEntity) {
                        livingEntity.addScoreboardTag("slowfalling");
                        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 1000000, 0, false, false), entity);
                    }
                }
            }
        }
        super.tick();
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        remove(RemovalReason.DISCARDED);
        if (tier != null) {
            dropItem(() -> MeteorItems.getRocketItem(tier));
            return ActionResult.SUCCESS;
        } else {
            return ActionResult.FAIL;
        }
    }

    @Override
    protected void ridingStart() {
        launched = true;
    }
}
