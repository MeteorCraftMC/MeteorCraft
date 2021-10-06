package com.github.meteorcraft;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MeteorCraft implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("Meteor Craft");

	@Override
	public void onInitialize() {
		MeteorBlocks.call();
		MeteorOres.call();
	}
}
