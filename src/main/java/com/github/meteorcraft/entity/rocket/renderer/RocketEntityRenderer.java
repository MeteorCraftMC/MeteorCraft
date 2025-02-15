package com.github.meteorcraft.entity.rocket.renderer;

import com.github.meteorcraft.entity.rocket.AbstractRocketEntity;
import com.github.meteorcraft.entity.rocket.model.RocketEntityModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class RocketEntityRenderer extends EntityRenderer<AbstractRocketEntity, EntityRenderState> {
    private final RocketEntityModel model;

    public RocketEntityRenderer(EntityRendererFactory.Context context, EntityModelLayer layer) {
        super(context);
        shadowRadius = 0.5F;
        model = new RocketEntityModel(context.getPart(layer));
    }

    @Override
    public void render(EntityRenderState entityRenderState, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light) {
        matrixStack.push();

        RocketEntityModel rocketEntityModel = model;
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0F));
        rocketEntityModel.setAngles(entityRenderState);
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(rocketEntityModel.getLayer(Identifier.of("meteorcraft", "textures/entity/test.png")));
        rocketEntityModel.render(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV);

        matrixStack.pop();

        super.render(entityRenderState, matrixStack, vertexConsumerProvider, light);
    }

    @Override
    public EntityRenderState createRenderState() {
        return new EntityRenderState();
    }
}
