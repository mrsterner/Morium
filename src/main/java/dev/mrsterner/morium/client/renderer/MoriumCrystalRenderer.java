package dev.mrsterner.morium.client.renderer;

import dev.mrsterner.morium.client.model.MoriumCrystalModel;
import dev.mrsterner.morium.client.renderlayer.MoriumRenderLayer;
import dev.mrsterner.morium.common.block.entity.MoriumCrystalBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class MoriumCrystalRenderer extends GeoBlockRenderer<MoriumCrystalBlockEntity> {
    public MoriumCrystalRenderer() {
        super(new MoriumCrystalModel());
    }

    @Override
    public RenderLayer getRenderType(MoriumCrystalBlockEntity animatable, float partialTicks, MatrixStack stack, VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {
        return MoriumRenderLayer.getMoriumLayer(getTextureLocation(animatable));
    }

    @Override
    public void render(BlockEntity tile, float partialTicks, MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int combinedLightIn, int combinedOverlayIn) {
        super.render(tile, partialTicks, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
    }
}
