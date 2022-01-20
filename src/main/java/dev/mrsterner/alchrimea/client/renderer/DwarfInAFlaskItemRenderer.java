package dev.mrsterner.alchrimea.client.renderer;

import dev.mrsterner.alchrimea.client.model.DwarfInAFlaskItemModel;
import dev.mrsterner.alchrimea.client.renderlayer.AlchrimeaRenderLayer;
import dev.mrsterner.alchrimea.common.block.AlchrimeaBlockItem;
import dev.mrsterner.alchrimea.common.block.entity.EmeraldTabletBlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class DwarfInAFlaskItemRenderer extends GeoItemRenderer<AlchrimeaBlockItem> {
    public DwarfInAFlaskItemRenderer() {
        super(new DwarfInAFlaskItemModel());
    }

    @Override
    public RenderLayer getRenderType(AlchrimeaBlockItem animatable, float partialTicks, MatrixStack stack, VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {
        return RenderLayer.getEntityTranslucent(getTextureLocation(animatable));
    }
}
