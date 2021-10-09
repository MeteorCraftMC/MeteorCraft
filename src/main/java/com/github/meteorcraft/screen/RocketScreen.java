package com.github.meteorcraft.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;

public class RocketScreen extends Screen {
    private ButtonWidget launchButton;
    private PlanetType type = PlanetType.NONE;

    public RocketScreen() {
        super(new LiteralText("Instrument Panel"));
    }

    @Override
    protected void init() {
        addDrawableChild(new TexturedButtonWidget(width / 2 - 12, height / 2 - 12, 25, 25, 0, 0, 25, new Identifier("meteorcraft", "textures/environment/sun.png"), 25, 50, buttonWidget -> {
            launchButton.active = true;
            type = PlanetType.SUN;
        }));
        addDrawableChild(new TexturedButtonWidget(width / 2 + 30, height / 2 + 30, 20, 20, 0, 0, 20, new Identifier("meteorcraft", "textures/environment/earth.png"), 20, 40, buttonWidget -> {
            launchButton.active = true;
            System.out.println("earth");
            type = PlanetType.EARTH;
        }));
        addDrawableChild(new TexturedButtonWidget(width / 2 + 55, height / 2 + 45, 15, 15, 0, 0, 15, new Identifier("meteorcraft", "textures/environment/moon.png"), 15, 30, buttonWidget -> {
            launchButton.active = true;
            type = PlanetType.MOON;
        }));

        launchButton = addDrawableChild(new ButtonWidget(width - 105, height - 25, 100, 20, new LiteralText("Launch"), buttonWidget -> {
            switch (type) {
                case NONE -> {
                }
                case SUN -> {
                }
                case EARTH -> {
                    move("minecraft:overworld");
                }
                case MOON -> {
                   move("meteorcraft:moon");
                }
            }
        }));
        launchButton.active = false;
    }

    private void move(String s) {
        //TODO if is multiplayer, send packet to server
        if (MinecraftClient.getInstance().world.isClient()) {
            MinecraftServer server = MinecraftClient.getInstance().getServer();
            RegistryKey<World> key = null;
            for (RegistryKey<World> worldRegistryKey : server.getWorldRegistryKeys()) {
                if (worldRegistryKey.getValue().equals(new Identifier(s))) {
                    key = worldRegistryKey;
                }
            }
            server.getPlayerManager().getPlayer(MinecraftClient.getInstance().player.getUuid()).teleport(server.getWorld(key), 0, 100, 0, 0, 0);
        }
    }

    @Override
    public void renderBackground(MatrixStack matrices) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, new Identifier("meteorcraft", "textures/environment/stars.png"));

        this.drawTexture(matrices, 0, 0, 0, 0, width, height);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);

        drawCenteredText(matrices, textRenderer, new LiteralText("Target: " + type.getName()), width / 2, height - 60, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }

    public enum PlanetType {
        NONE("NONE"), SUN("Sun"), EARTH("Earth"), MOON("Moon");

        private final String name;

        PlanetType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }
}
