package dev.mrsterner.morium.client.renderer;

import dev.mrsterner.morium.client.model.EmeraldTabletModel;
import dev.mrsterner.morium.client.model.MoriumCrystalModel;
import dev.mrsterner.morium.client.renderlayer.GlowyRenderLayer;
import dev.mrsterner.morium.common.block.entity.EmeraldTabletBlockEntity;
import dev.mrsterner.morium.common.block.entity.MoriumCrystalBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class EmeraldTabletRenderer extends GeoBlockRenderer<EmeraldTabletBlockEntity> {
    public EmeraldTabletRenderer() {
        super(new EmeraldTabletModel());
    }

    @Override
    public RenderLayer getRenderType(EmeraldTabletBlockEntity animatable, float partialTicks, MatrixStack stack, VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {
        return GlowyRenderLayer.getMoriumLayer(getTextureLocation(animatable));
    }
}
