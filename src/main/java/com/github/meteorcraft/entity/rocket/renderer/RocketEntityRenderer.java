package com.github.meteorcraft.entity.rocket.renderer;

import com.github.meteorcraft.entity.rocket.AbstractRocketEntity;
import com.github.meteorcraft.entity.rocket.model.RocketEntityModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;

public class RocketEntityRenderer extends EntityRenderer<AbstractRocketEntity> {
    private final RocketEntityModel model;

    public RocketEntityRenderer(EntityRendererFactory.Context context, EntityModelLayer layer) {
        super(context);
        model = new RocketEntityModel(context.getPart(layer));
    }

    @Override
    public Identifier getTexture(AbstractRocketEntity entity) {
        return new Identifier("meteorcraft", "textures/entity/test.png");
    }

    @Override
    public void render(AbstractRocketEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
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
