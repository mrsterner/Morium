package dev.mrsterner.morium;

import dev.mrsterner.morium.client.renderer.EmeraldTabletRenderer;
import dev.mrsterner.morium.client.renderer.MoriumCrystalRenderer;
import dev.mrsterner.morium.common.registry.MoriumBlockEntityTypes;
import dev.mrsterner.morium.common.registry.MoriumObjects;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.impl.blockrenderlayer.BlockRenderLayerMapImpl;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;

public class MoriumClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockEntityRendererRegistry.register(MoriumBlockEntityTypes.MORIUM_CRYSTAL_BLOCK_ENTITY, (BlockEntityRendererFactory.Context rendererDispatcherIn) -> new MoriumCrystalRenderer());
        BlockEntityRendererRegistry.register(MoriumBlockEntityTypes.EMERALD_TABLET_BLOCK_ENTITY, (BlockEntityRendererFactory.Context rendererDispatcherIn) -> new EmeraldTabletRenderer());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(MoriumObjects.MORIUM_CRYSTAL_AMBER, RenderLayer.getCutout());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(MoriumObjects.MORIUM_CRYSTAL_CARNATION, RenderLayer.getCutout());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(MoriumObjects.MORIUM_CRYSTAL_CERISE, RenderLayer.getCutout());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(MoriumObjects.MORIUM_CRYSTAL_CRIMSON, RenderLayer.getCutout());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(MoriumObjects.MORIUM_CRYSTAL_OCHRE, RenderLayer.getCutout());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(MoriumObjects.MORIUM_CRYSTAL_OLYMPIC, RenderLayer.getCutout());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(MoriumObjects.MORIUM_CRYSTAL_SHAMROCK, RenderLayer.getCutout());
    }
}
