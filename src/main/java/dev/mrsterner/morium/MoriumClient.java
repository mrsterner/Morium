package dev.mrsterner.morium;

import dev.mrsterner.morium.common.registry.MoriumObjects;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.impl.blockrenderlayer.BlockRenderLayerMapImpl;
import net.minecraft.client.render.RenderLayer;

public class MoriumClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMapImpl.INSTANCE.putBlock(MoriumObjects.MORIUM_CRYSTAL_1, RenderLayer.getTranslucent());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(MoriumObjects.MORIUM_CRYSTAL_2, RenderLayer.getTranslucent());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(MoriumObjects.MORIUM_CRYSTAL_3, RenderLayer.getTranslucent());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(MoriumObjects.MORIUM_CRYSTAL_4, RenderLayer.getTranslucent());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(MoriumObjects.MORIUM_CRYSTAL_5, RenderLayer.getTranslucent());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(MoriumObjects.MORIUM_CRYSTAL_6, RenderLayer.getTranslucent());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(MoriumObjects.MORIUM_CRYSTAL_7, RenderLayer.getTranslucent());
    }
}
