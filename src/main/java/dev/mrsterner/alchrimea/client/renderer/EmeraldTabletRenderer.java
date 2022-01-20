package dev.mrsterner.alchrimea.client.renderer;

import dev.mrsterner.alchrimea.client.model.EmeraldTabletModel;
import dev.mrsterner.alchrimea.client.renderlayer.AlchrimeaRenderLayer;
import dev.mrsterner.alchrimea.common.block.entity.EmeraldTabletBlockEntity;
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
        return AlchrimeaRenderLayer.getAlchrimeaLayer(getTextureLocation(animatable));
    }

    @Override
    public void render(BlockEntity tile, float partialTicks, MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int combinedLightIn, int combinedOverlayIn) {
        super.render(tile, partialTicks, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
        if(tile instanceof EmeraldTabletBlockEntity emeraldTabletBlockEntity){
            emeraldTabletBlockEntity.partial = partialTicks;
        }
    }
}
