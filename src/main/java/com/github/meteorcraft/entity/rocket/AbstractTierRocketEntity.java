package com.github.meteorcraft.entity.rocket;

import com.github.meteorcraft.client.MeteorCraftClient;
import com.github.meteorcraft.mixin.KeyBindingAccessor;
import com.github.meteorcraft.screen.RocketScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

public abstract class AbstractTierRocketEntity extends AbstractRocketEntity {
    private final RocketTier tier;
    protected boolean launching = false;
    protected boolean launched = false;
    private int launchProgress = 0;

    public AbstractTierRocketEntity(EntityType<? extends LivingEntity> entityType, World world, RocketTier tier) {
        super(entityType, world);
        this.tier = tier;
    }

    public boolean isLaunching() {
        return launching;
    }

    public boolean isLaunched() {
        return launched;
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
        if (!launched) {
            if (getPassengerList().contains(MinecraftClient.getInstance().player)) {
                launching = false;
                if (KeyBindingAccessor.getKEY_TO_BINDINGS().get(InputUtil.fromTranslationKey("key.keyboard.space")).isPressed()) {
                    launching = true;
                    if (launching) {
                        if (MinecraftClient.getInstance().getServer() != null)
                            MinecraftClient.getInstance().getServer().getWorld(getWorld().getRegistryKey()).spawnParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE.getType(), getX(), getY() - 1, getZ(), 10, 0, 0, 0, 0.05);
                        launchProgress++;
                        if (launchProgress == 100) {
                            launching = false;
                            launched = true;
                            if (MinecraftClient.getInstance().getServer() != null)
                                MinecraftClient.getInstance().getServer().getWorld(getWorld().getRegistryKey()).spawnParticles(ParticleTypes.EXPLOSION_EMITTER.getType(), getX(), getY() - 2, getZ(), 1, 0, 0, 0, 0);
                        }
                    }
                }
            } else {
                MeteorCraftClient.rocketRidingEnd();
            }
        }
        super.tick();
    }

    @Override
    protected void ridingStart() {
        MeteorCraftClient.rocketRidingStart(this);
    }
}
