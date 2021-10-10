package com.github.meteorcraft.items;

import com.github.meteorcraft.MeteorEntityTypes;
import com.github.meteorcraft.entity.rocket.AbstractRocketEntity;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;

public class RocketItem extends Item {
    private final AbstractRocketEntity.RocketTier tier;

    public RocketItem(FabricItemSettings settings, AbstractRocketEntity.RocketTier tier) {
        super(settings);
        this.tier = tier;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        context.getStack().decrement(1);
        AbstractRocketEntity entity = AbstractRocketEntity.getRocketEntityType(tier).create(context.getWorld());
        //noinspection ConstantConditions
        entity.teleport(context.getBlockPos().getX() + 0.5, context.getBlockPos().getY() + 2, context.getBlockPos().getZ() + 0.5);
        context.getWorld().spawnEntity(entity);
        return super.useOnBlock(context);
    }
}
