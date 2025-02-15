package com.github.meteorcraft.mixin;

import com.github.meteorcraft.MeteorItems;
import com.github.meteorcraft.MeteorWorlds;
import com.github.meteorcraft.client.MeteorCraftClient;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.reborn.energy.api.base.SimpleEnergyItem;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }
    @Shadow
    public abstract void sendMessage(Text message, boolean actionBar);

    @Shadow public abstract boolean damage(ServerWorld world, DamageSource source, float amount);

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        if (MeteorWorlds.isMoon(getWorld())) {
            ItemStack stack = TrinketsApi.getTrinketComponent(this).orElseThrow().getInventory().get("chest").get("tank").getStack(0);
            if (!TrinketsApi.getTrinketComponent(this).orElseThrow().isEquipped(MeteorItems.OXYGEN_TANK) || SimpleEnergyItem.getStoredEnergyUnchecked(stack) == 0) {
//                damage(getWorld(), new DamageSource(RegistryEntry.1.0F);
                System.out.println("Oxygen tank is empty!");
            }
        }

        if (this.equals(MinecraftClient.getInstance().player)) {
            if (MeteorCraftClient.isRidingRocket()) {
                assert MeteorCraftClient.getRidingRocket() != null;
                if (MeteorCraftClient.getRidingRocket().isLaunching()) {
                    sendMessage(Text.translatable("gui.meteorcraft.hold_space"), true);
                } else if (!MeteorCraftClient.getRidingRocket().isLaunched()) {
                    sendMessage(Text.translatable("gui.meteorcraft.press_space"), true);
                } else {
                    sendMessage(Text.translatable("gui.meteorcraft.launched"), true);
                }
            }
        }
    }
}
