package com.github.meteorcraft.screen;

import com.github.meteorcraft.MeteorEntityTypes;
import com.github.meteorcraft.MeteorWorlds;
import com.github.meteorcraft.client.MeteorCraftClient;
import com.github.meteorcraft.entity.rocket.AbstractRocketEntity;
import com.github.meteorcraft.entity.rocket.LanderEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.SpawnReason;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import org.lwjgl.system.CallbackI;

import java.util.Set;

public class RocketScreen extends Screen {
    private ButtonWidget launchButton;
    private PlanetType type = PlanetType.NONE;
    private final AbstractRocketEntity.RocketTier tier;
    private int key = 0;

    public RocketScreen(AbstractRocketEntity.RocketTier tier) {
        super(Text.of("Instrument Panel"));
        this.tier = tier;
    }

    @Override
    protected void init() {
//        assert client != null;
//        assert client.player != null;
//
//        addDrawableChild(new TexturedButtonWidget(width / 2 - 12, height / 2 - 12, 25, 25, 0, 0, 25, Identifier.of("meteorcraft", "textures/environment/sun.png"), 25, 50, buttonWidget -> {
//            launchButton.active = true;
//            type = PlanetType.SUN;
//        }));
//        addDrawableChild(new TexturedButtonWidget(width / 2 + 30, height / 2 + 30, 20, 20, 0, 0, 20, Identifier.of("meteorcraft", "textures/environment/earth.png"), 20, 40, buttonWidget -> {
//            launchButton.active = true;
//            System.out.println("earth");
//            type = PlanetType.EARTH;
//        }));
//        addDrawableChild(new TexturedButtonWidget(width / 2 + 55, height / 2 + 45, 15, 15, 0, 0, 15, Identifier.of("meteorcraft", "textures/environment/moon.png"), 15, 30, buttonWidget -> {
//            launchButton.active = true;
//            type = PlanetType.MOON;
//        }));
//
//        launchButton = addDrawableChild(new ButtonWidget(width - 105, height - 25, 100, 20, new TranslatableText("gui.meteorcraft.launch"), buttonWidget -> {
//            switch (type) {
//                case NONE -> {
//                }
//                case LEAF -> {
//                    client.setScreen(new ConfirmChatLinkScreen((confirmed) -> {
//                        if (confirmed) {
//                            Util.getOperatingSystem().open("https://github.com/WintChoco/leaf-mirror/");
//                        }
//
//                        this.client.setScreen(this);
//                    }, "https://github.com/WintChoco/leaf-mirror/", true));
//                }
//                case SUN -> {
//                    if (MeteorWorlds.isMoon(client.player.getEntityWorld())) {
//                        if (key == 4) {
//                            type = PlanetType.LEAF;
//                        }
//                    }
//                }
//                case EARTH -> {
//                    move("minecraft:overworld");
//                }
//                case MOON -> {
//                    move("meteorcraft:moon");
//                }
//            }
//        }));
//        launchButton.active = false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (type.equals(PlanetType.SUN)) {
            switch (key) {
                case 0 -> {
                    if (keyCode == 76) {
                        key = 1;
                        return true;
                    }
                }
                case 1 -> {
                    if (keyCode == 69) {
                        key = 2;
                        return true;
                    } else key = 0;
                }
                case 2 -> {
                    if (keyCode == 65) {
                        key = 3;
                        return true;
                    } else key = 0;
                }
                case 3 -> {
                    if (keyCode == 70) {
                        key = 4;
                        return true;
                    } else key = 0;
                }
            }
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private void move(String s) {
        assert client != null;
        assert client.world != null;
        assert client.player != null;
        //TODO if is multiplayer, send packet to server
        if (client.world.isClient()) {
            MinecraftServer server = client.getServer();
            assert server != null;
            RegistryKey<World> key = null;
            for (RegistryKey<World> worldRegistryKey : server.getWorldRegistryKeys()) {
                if (worldRegistryKey.getValue().equals(Identifier.of(s))) {
                    key = worldRegistryKey;
                }
            }
            ServerWorld world = server.getWorld(key);
            ServerPlayerEntity player = server.getPlayerManager().getPlayer(client.player.getUuid());
            assert world != null;
            assert player != null;

            player.teleport(world, player.getX(), 5000, player.getZ(), Set.of(), 0, 0, true);
            LanderEntity lander = (LanderEntity) MeteorEntityTypes.LANDER_ENTITY.create(world, SpawnReason.TRIGGERED);

            assert lander != null;
            lander.setTier(tier);
            world.spawnEntity(lander);
            lander.teleport(0, 5000, 0, false);
            player.startRiding(lander);
        }
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        context.drawTexture(RenderLayer::getGuiTextured, Identifier.of("meteorcraft", "textures/environment/stars.png"), 0, 0, 0, 0, width, height, width, height);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        context.drawCenteredTextWithShadow(textRenderer, Text.translatable("gui.meteorcraft.target", type.getName()), width / 2, height - 60, 0xFFFFFF);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    public enum PlanetType {
        NONE("gui.meteorcraft.none"), SUN("gui.meteorcraft.sun"), EARTH("gui.meteorcraft.earth"), MOON("gui.meteorcraft.moon"), LEAF("gui.leaf.name");

        private final Text name;

        PlanetType(String name) {
            this.name = Text.of(name);
        }

        public Text getName() {
            return name;
        }
    }
}
