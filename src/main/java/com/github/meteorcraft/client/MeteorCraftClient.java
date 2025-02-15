package com.github.meteorcraft.client;

import com.github.meteorcraft.MeteorEntityTypes;
import com.github.meteorcraft.entity.rocket.AbstractTierRocketEntity;
import com.github.meteorcraft.entity.rocket.LanderEntity;
import com.github.meteorcraft.entity.rocket.Tier1RocketEntity;
import com.github.meteorcraft.entity.rocket.model.RocketEntityModel;
import com.github.meteorcraft.entity.rocket.renderer.RocketEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

public class MeteorCraftClient implements ClientModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("Meteor Craft");
	private static AbstractTierRocketEntity ridingRocket = null;

    public static void rocketRidingStart(AbstractTierRocketEntity entity) {
		ridingRocket = entity;
    }

	public static void rocketRidingEnd() {
		ridingRocket = null;
	}

	public static boolean isRidingRocket() {
		return ridingRocket != null;
	}

	@Nullable
	public static AbstractTierRocketEntity getRidingRocket() {
		return ridingRocket;
	}

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(MeteorEntityTypes.TIER_1_ROCKET_ENTITY, (context) -> new RocketEntityRenderer(context, Tier1RocketEntity.LAYER));
		EntityModelLayerRegistry.registerModelLayer(Tier1RocketEntity.LAYER, RocketEntityModel::getTexturedModelData);

		EntityRendererRegistry.register(MeteorEntityTypes.LANDER_ENTITY, (context) -> new RocketEntityRenderer(context, LanderEntity.LAYER));
		EntityModelLayerRegistry.registerModelLayer(LanderEntity.LAYER, RocketEntityModel::getTexturedModelData);
	}
}
