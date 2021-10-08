package com.github.meteorcraft.trinkets;

import com.github.meteorcraft.MeteorWorlds;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.base.SimpleBatteryItem;

import java.util.List;

public class OxygenTankItem extends TrinketItem implements SimpleBatteryItem {
    public final long capacity = 5000; // Need to change
    public final long maxInput = 50; // Need to change
    public final long maxOutput = 50; // Need to change

    public OxygenTankItem(Settings settings) {
        super(settings);
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (MeteorWorlds.isMoon(entity.world)) {
            if (getStoredEnergy(stack) != 0) {
                setStoredEnergy(stack, getStoredEnergy(stack) - 1);
            }
        }
    }

    @Override
    public long getEnergyCapacity() {
        return capacity;
    }

    @Override
    public long getEnergyMaxInput() {
        return maxInput;
    }

    @Override
    public long getEnergyMaxOutput() {
        return maxOutput;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new LiteralText("Energy : "+ getStoredEnergy(stack) + "/" + getEnergyCapacity()));
    }
}