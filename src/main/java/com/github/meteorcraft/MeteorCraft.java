package com.github.meteorcraft;

import com.github.meteorcraft.entity.RocketEntity;
import com.github.meteorcraft.entity.model.RocketEntityModel;
import com.github.meteorcraft.entity.renderer.RocketEntityRenderer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MeteorCraft implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("Meteor Craft");

	@Override
	public void onInitialize() {
		MeteorBlocks.call();
		MeteorOres.call();
		MeteorItems.call();
		MeteorBlockEntityTypes.call();
		MeteorEntityTypes.call();

		EntityRendererRegistry.register(MeteorEntityTypes.ROCKET_ENTITY, RocketEntityRenderer::new);

		EntityModelLayerRegistry.registerModelLayer(RocketEntity.ROCKET_LAYER, RocketEntityModel::getTexturedModelData);
	}
}
