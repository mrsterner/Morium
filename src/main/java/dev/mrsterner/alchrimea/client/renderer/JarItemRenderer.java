package dev.mrsterner.alchrimea.client.renderer;

import dev.mrsterner.alchrimea.Alchrimea;
import dev.mrsterner.alchrimea.client.model.JarItemModel;
import dev.mrsterner.alchrimea.client.renderlayer.AlchrimeaRenderLayer;
import dev.mrsterner.alchrimea.common.block.AlchrimeaBlockItem;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class JarItemRenderer extends GeoItemRenderer<AlchrimeaBlockItem> {
    public JarItemRenderer() {
        super(new JarItemModel());
    }
    private static ModelTransformation.Mode forceTransform = ModelTransformation.Mode.NONE;
    @Override
    public RenderLayer getRenderType(AlchrimeaBlockItem animatable, float partialTicks, MatrixStack stack, VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {
        System.out.println(forceTransform);
        boolean gui = forceTransform == ModelTransformation.Mode.GUI;
        return gui ? AlchrimeaRenderLayer.getJarGui(getTextureLocation(animatable)) : AlchrimeaRenderLayer.getJarFps(getTextureLocation(animatable));
    }

    @Override
    public void render(ItemStack itemStack, ModelTransformation.Mode mode, MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int combinedLightIn, int combinedOverlayIn) {
        forceTransform = mode;
        super.render(itemStack, mode, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
    }

    @Override
    public void render(AlchrimeaBlockItem animatable, MatrixStack stack, VertexConsumerProvider bufferIn, int packedLightIn, ItemStack itemStack) {
        super.render(animatable, stack, bufferIn, packedLightIn, itemStack);
        String location ="magma_cream";
        int i = 1;
        if(itemStack.hasNbt()){
            stack.push();
            stack.translate(0.5F,0.5,0.5F);
            String list = itemStack.getSubNbt("BlockEntityTag").asString();
            if(list.contains("}],id:\"a")){
                location = list.substring(list.indexOf("minecraft:")+10, list.indexOf("}],id:\"a")-1);
                i = Integer.parseInt(list.substring(list.indexOf("Count:")+6, list.indexOf("b,S")));
            }
            int j;
            if(i <= 32){
                j = 1;
            }else if(i < 64){
                j = 2;
            }else{
                j = 3;
            }
            Identifier LAYER = new Identifier(Alchrimea.MODID, "textures/block/jar/" + location + ".png");
            render(getGeoModelProvider().getModel(new Identifier(Alchrimea.MODID, "geo/" + location + "_" + j + ".geo.json")), animatable, 0,
                AlchrimeaRenderLayer.getJarContent(LAYER), stack, bufferIn, bufferIn.getBuffer(
                    AlchrimeaRenderLayer.getJarContent(LAYER)), packedLightIn, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);

            stack.pop();
        }
    }
}
