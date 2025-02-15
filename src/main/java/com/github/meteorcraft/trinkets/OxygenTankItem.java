package com.github.meteorcraft.trinkets;

import com.github.meteorcraft.MeteorWorlds;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import team.reborn.energy.api.base.SimpleEnergyItem;

import java.util.List;

public class OxygenTankItem extends TrinketItem implements SimpleEnergyItem {
    public final long capacity = 5000; // Need to change
    public final long maxInput = 50; // Need to change
    public final long maxOutput = 50; // Need to change

    public OxygenTankItem(Settings settings) {
        super(settings);
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (MeteorWorlds.isMoon(entity.getWorld())) {
            if (getStoredEnergy(stack) != 0) {
                setStoredEnergy(stack, getStoredEnergy(stack) - 1);
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.of("Energy : "+ getStoredEnergy(stack) + "/" + getEnergyCapacity(stack)));
    }

    @Override
    public long getEnergyCapacity(ItemStack itemStack) {
        return capacity;
    }

    @Override
    public long getEnergyMaxInput(ItemStack itemStack) {
        return maxInput;
    }

    @Override
    public long getEnergyMaxOutput(ItemStack itemStack) {
        return maxOutput;
    }
}