package com.github.meteorcraft;

import com.github.meteorcraft.block.OxygenCollectorBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unchecked")
public class MeteorBlockEntityTypes {
    private static final String MOD_ID = "meteorcraft";
    public static final BlockEntityType<OxygenCollectorBlockEntity> OXYGEN_COLLECTOR =
            (BlockEntityType<OxygenCollectorBlockEntity>) register(new Identifier(MOD_ID,"oxygen_collector"), FabricBlockEntityTypeBuilder
            .create(OxygenCollectorBlockEntity::new, MeteorBlocks.OXYGEN_COLLECTOR).build(null));

    private static BlockEntityType<?> register(Identifier identifier, BlockEntityType<?> entry) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE,identifier, entry);
    }

    public static void call() {
    }
}
