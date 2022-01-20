package dev.mrsterner.alchrimea.client.renderer;

import dev.mrsterner.alchrimea.client.model.JarModel;
import dev.mrsterner.alchrimea.client.renderlayer.AlchrimeaRenderLayer;
import dev.mrsterner.alchrimea.common.block.entity.JarBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class JarRenderer extends GeoBlockRenderer<JarBlockEntity> {
    public JarRenderer() {
        super(new JarModel());
    }

    public String getModelPath(Item item){
        return Registry.ITEM.getKey(item).get().getValue().getPath();
    }

    @Override
    public RenderLayer getRenderType(JarBlockEntity animatable, float partialTicks, MatrixStack stack, VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {
        return AlchrimeaRenderLayer.getJarLayer(getTextureLocation(animatable));
    }

    @Override
    public void render(BlockEntity tile, float partialTicks, MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int combinedLightIn, int combinedOverlayIn) {
        super.render(tile, partialTicks, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
        if(tile instanceof JarBlockEntity jarBlockEntity){
            matrixStackIn.push();
            matrixStackIn.translate(0.5F,0.0,0.5F);
            if(!jarBlockEntity.getStack(0).isEmpty()){
                int i;
                if(jarBlockEntity.getStack(0).getCount() <= 32){
                    i = 1;
                }else if(jarBlockEntity.getStack(0).getCount() > 32 && jarBlockEntity.getStack(0).getCount() < 64){
                    i = 2;
                }else{
                    i = 3;
                }
                Identifier LAYER = new Identifier(dev.mrsterner.alchrimea.Alchrimea.MODID, "textures/block/jar/"+  getModelPath(jarBlockEntity.getStack(0).getItem())+".png");
                render(getGeoModelProvider().getModel(new Identifier(dev.mrsterner.alchrimea.Alchrimea.MODID, "geo/"+ getModelPath(jarBlockEntity.getStack(0).getItem())+"_"+i+".geo.json")), jarBlockEntity, partialTicks,
                    RenderLayer.getEntityTranslucent(LAYER), matrixStackIn, bufferIn, bufferIn.getBuffer(
                        RenderLayer.getEntityTranslucent(LAYER)), combinedLightIn, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
            }
            matrixStackIn.pop();
        }
    }
}
