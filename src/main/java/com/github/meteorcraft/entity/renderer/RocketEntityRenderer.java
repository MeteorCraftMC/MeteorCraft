package com.github.meteorcraft.entity.renderer;

import com.github.meteorcraft.entity.RocketEntity;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class RocketEntityRenderer extends EntityRenderer<RocketEntity> {
    public RocketEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(RocketEntity entity) {
        return null;
    }
}
