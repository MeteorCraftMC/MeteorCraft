package com.github.meteorcraft.mixin;

import com.github.meteorcraft.MeteorItems;
import com.github.meteorcraft.MeteorWorlds;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.reborn.energy.api.base.SimpleBatteryItem;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public abstract boolean damage(DamageSource source, float amount);

    @Shadow
    public abstract void addExhaustion(float exhaustion);

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        if (MeteorWorlds.isMoon(world)) {
            ItemStack stack = TrinketsApi.getTrinketComponent(this).orElseThrow().getInventory().get("chest").get("tank").getStack(0);
            if (!TrinketsApi.getTrinketComponent(this).orElseThrow().isEquipped(MeteorItems.OXYGEN_TANK) || SimpleBatteryItem.getStoredEnergyUnchecked(stack) == 0) {
                damage(DamageSource.IN_WALL, 1);
            }
        }
    }
}
