package com.github.meteorcraft;

import com.github.meteorcraft.entity.rocket.LanderEntity;
import com.github.meteorcraft.entity.rocket.Tier1RocketEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MeteorCraft implements ModInitializer {
    public static final String MOD_ID = "meteorcraft";
    public static final Logger LOGGER = LogManager.getLogger("Meteor Craft");

    @Override
    public void onInitialize() {
        MeteorItems.call();
        MeteorBlocks.call();
        MeteorOres.call();
        MeteorBlockEntityTypes.call();
        MeteorEntityTypes.call();
        //noinspection ConstantConditions
        FabricDefaultAttributeRegistry.register(MeteorEntityTypes.TIER_1_ROCKET_ENTITY, Tier1RocketEntity.createLivingAttributes());
        FabricDefaultAttributeRegistry.register(MeteorEntityTypes.LANDER_ENTITY, LanderEntity.createLivingAttributes());
    }
}
