package com.github.meteorcraft;

import com.github.meteorcraft.block.OxygenCollectorBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.github.meteorcraft.MeteorCraft.MOD_ID;

@SuppressWarnings("unchecked")
public class MeteorBlockEntityTypes {
    public static final BlockEntityType<OxygenCollectorBlockEntity> OXYGEN_COLLECTOR =
            (BlockEntityType<OxygenCollectorBlockEntity>) register(
                    Identifier.of(MOD_ID, "oxygen_collector"),
                    FabricBlockEntityTypeBuilder.create(OxygenCollectorBlockEntity::new, MeteorBlocks.OXYGEN_COLLECTOR).build(null)); // TODO

    private static BlockEntityType<?> register(Identifier identifier, BlockEntityType<?> entry) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, identifier, entry);
    }

    public static void call() {
    }
}
