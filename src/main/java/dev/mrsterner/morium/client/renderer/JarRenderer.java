package dev.mrsterner.morium.client.renderer;

import dev.mrsterner.morium.Morium;
import dev.mrsterner.morium.client.model.JarModel;
import dev.mrsterner.morium.client.renderlayer.MoriumRenderLayer;
import dev.mrsterner.morium.common.block.entity.JarBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
        return MoriumRenderLayer.getJarLayer(getTextureLocation(animatable));
    }

    @Override
    public void render(BlockEntity tile, float partialTicks, MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int combinedLightIn, int combinedOverlayIn) {
        super.render(tile, partialTicks, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
        if(tile instanceof JarBlockEntity jarBlockEntity){
            matrixStackIn.push();
            matrixStackIn.translate(0.5F,-0.05,0.5F);
            if(!jarBlockEntity.getStack(0).isEmpty()){
                int i;
                if(jarBlockEntity.getStack(0).getCount() < 21){
                    i = 1;
                }else if(jarBlockEntity.getStack(0).getCount() >= 21 && jarBlockEntity.getStack(0).getCount() < 42){
                    i = 2;
                }else{
                    i = 3;
                }
                Identifier LAYER = new Identifier(Morium.MODID, "textures/block/jar/"+  getModelPath(jarBlockEntity.getStack(0).getItem())+".png");
                render(getGeoModelProvider().getModel(new Identifier(Morium.MODID, "geo/"+ getModelPath(jarBlockEntity.getStack(0).getItem())+"_"+i+".geo.json")), jarBlockEntity, partialTicks,
                    RenderLayer.getEntityTranslucent(LAYER), matrixStackIn, bufferIn, bufferIn.getBuffer(
                        RenderLayer.getEntityTranslucent(LAYER)), combinedLightIn, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
            }
            matrixStackIn.pop();
        }
    }
}
