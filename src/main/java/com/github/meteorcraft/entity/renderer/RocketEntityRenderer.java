package com.github.meteorcraft.entity.renderer;

import com.github.meteorcraft.entity.RocketEntity;
import com.github.meteorcraft.entity.model.RocketEntityModel;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;

public class RocketEntityRenderer extends EntityRenderer<RocketEntity> {
    private final RocketEntityModel model;

    public RocketEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        model = new RocketEntityModel(context.getPart(RocketEntity.ROCKET_LAYER));
    }

    @Override
    public Identifier getTexture(RocketEntity entity) {
        return new Identifier("meteorcraft", "textures/entity/test.png");
    }

    @Override
    public void render(RocketEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        RocketEntityModel boatEntityModel = model;
        matrices.translate(0, -1, 0);
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90.0F));
        boatEntityModel.setAngles(entity, tickDelta, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(boatEntityModel.getLayer(getTexture(entity)));
        boatEntityModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);

        matrices.pop();

        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}
