package com.github.meteorcraft.items;

import com.github.meteorcraft.entity.rocket.AbstractRocketEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;

public class RocketItem extends Item {
    private final AbstractRocketEntity.RocketTier tier;

    public RocketItem(Item.Settings settings, AbstractRocketEntity.RocketTier tier) {
        super(settings);
        this.tier = tier;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        context.getStack().decrement(1);
        AbstractRocketEntity entity = AbstractRocketEntity.getRocketEntityType(tier).create(context.getWorld(), SpawnReason.SPAWN_ITEM_USE);
        //noinspection ConstantConditions
        entity.requestTeleport(context.getBlockPos().getX() + 0.5, context.getBlockPos().getY() + 2, context.getBlockPos().getZ() + 0.5);
        context.getWorld().spawnEntity(entity);
        return super.useOnBlock(context);
    }
}
