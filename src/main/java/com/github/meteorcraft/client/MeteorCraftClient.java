package com.github.meteorcraft.client;

import com.github.meteorcraft.MeteorEntityTypes;
import com.github.meteorcraft.entity.RocketEntity;
import com.github.meteorcraft.entity.model.RocketEntityModel;
import com.github.meteorcraft.entity.renderer.RocketEntityRenderer;
import com.github.meteorcraft.screen.RocketScreen;
import dev.emi.trinkets.api.client.TrinketRenderer;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

public class MeteorCraftClient implements ClientModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("Meteor Craft");

	@Override
	public void onInitializeClient() {
		KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.examplemod.spook", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_R, // The keycode of the key
				"category.examplemod.test" // The translation key of the keybinding's category.
		));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (keyBinding.wasPressed()) {
				client.setScreen(new RocketScreen());
			}
		});

		EntityRendererRegistry.register(MeteorEntityTypes.ROCKET_ENTITY, RocketEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(RocketEntity.ROCKET_LAYER, RocketEntityModel::getTexturedModelData);
	}
}
