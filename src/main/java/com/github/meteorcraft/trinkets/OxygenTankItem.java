package com.github.meteorcraft.trinkets;

import com.github.meteorcraft.MeteorWorlds;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class OxygenTankItem extends TrinketItem {
    public OxygenTankItem(Settings settings) {
        super(settings);
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (MeteorWorlds.isMoon(entity.world)) {
            if (stack.getDamage() < getMaxDamage() - 1) {
                stack.damage(1, entity, livingEntity -> {
                });
            }
        }
    }
}