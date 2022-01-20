package dev.mrsterner.alchrimea;

import dev.mrsterner.alchrimea.client.renderer.*;
import dev.mrsterner.alchrimea.common.registry.AlchrimeaEntityTypes;
import dev.mrsterner.alchrimea.common.registry.AlchrimeaObjects;
import dev.mrsterner.alchrimea.common.registry.SpriteIdentifierRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.impl.blockrenderlayer.BlockRenderLayerMapImpl;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.ChestBlockEntityRenderer;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

import static net.minecraft.client.render.TexturedRenderLayers.CHEST_ATLAS_TEXTURE;

public class AlchrimeaClient implements ClientModInitializer {
    public static final SpriteIdentifier CEDAR_CHEST = new SpriteIdentifier(CHEST_ATLAS_TEXTURE, new Identifier(Alchrimea.MODID, "entity/chest/cedar"));
    public static final SpriteIdentifier TRAPPED_CEDAR_CHEST = new SpriteIdentifier(CHEST_ATLAS_TEXTURE, new Identifier(Alchrimea.MODID, "entity/chest/trapped_cedar"));
    public static final SpriteIdentifier CEDAR_CHEST_LEFT = new SpriteIdentifier(CHEST_ATLAS_TEXTURE, new Identifier(Alchrimea.MODID, "entity/chest/cedar_left"));
    public static final SpriteIdentifier TRAPPED_CEDAR_CHEST_LEFT = new SpriteIdentifier(CHEST_ATLAS_TEXTURE, new Identifier(Alchrimea.MODID, "entity/chest/trapped_cedar_left"));
    public static final SpriteIdentifier CEDAR_CHEST_RIGHT = new SpriteIdentifier(CHEST_ATLAS_TEXTURE, new Identifier(Alchrimea.MODID, "entity/chest/cedar_right"));
    public static final SpriteIdentifier TRAPPED_CEDAR_CHEST_RIGHT = new SpriteIdentifier(CHEST_ATLAS_TEXTURE, new Identifier(Alchrimea.MODID, "entity/chest/trapped_cedar_right"));


    @Override
    public void onInitializeClient() {
        GeoItemRenderer.registerItemRenderer(AlchrimeaObjects.DWARF_IN_A_FLASK_BLOCK.asItem(), new DwarfInAFlaskItemRenderer());
        GeoItemRenderer.registerItemRenderer(AlchrimeaObjects.JAR.asItem(), new JarItemRenderer());
        BlockEntityRendererRegistry.register(AlchrimeaEntityTypes.DWARF_IN_A_FLASK_BLOCK_ENTITY, (BlockEntityRendererFactory.Context rendererDispatcherIn) -> new DwarfInAFlaskBlockRenderer());
        BlockRenderLayerMap.INSTANCE.putBlock(AlchrimeaObjects.DWARF_IN_A_FLASK_BLOCK, RenderLayer.getCutout());
        BlockEntityRendererRegistry.register(AlchrimeaEntityTypes.KHEMEIA_CHEST, ChestBlockEntityRenderer::new);
        SpriteIdentifierRegistry.INSTANCE.addIdentifier(CEDAR_CHEST);
        SpriteIdentifierRegistry.INSTANCE.addIdentifier(TRAPPED_CEDAR_CHEST);
        SpriteIdentifierRegistry.INSTANCE.addIdentifier(CEDAR_CHEST_LEFT);
        SpriteIdentifierRegistry.INSTANCE.addIdentifier(TRAPPED_CEDAR_CHEST_LEFT);
        SpriteIdentifierRegistry.INSTANCE.addIdentifier(CEDAR_CHEST_RIGHT);
        SpriteIdentifierRegistry.INSTANCE.addIdentifier(TRAPPED_CEDAR_CHEST_RIGHT);

        // ColorProvider
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) ->
            view != null && pos != null ? BiomeColors.getFoliageColor(view, pos) :
                FoliageColors.getDefaultColor(), AlchrimeaObjects.CEDAR_LEAVES);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex == 0 ? 0x3f4f3b : 0xffffff, AlchrimeaObjects.CEDAR_LEAVES.asItem());

        GeoItemRenderer.registerItemRenderer(AlchrimeaObjects.MAGNUM_OPUS, new MagnumOpusItemRenderer());
        EntityRendererRegistry.register(AlchrimeaEntityTypes.THE_TRUTH, TheTruthEntityRenderer::new);


        BlockEntityRendererRegistry.register(AlchrimeaEntityTypes.MORIUM_CRYSTAL_BLOCK_ENTITY, (BlockEntityRendererFactory.Context rendererDispatcherIn) -> new MoriumCrystalRenderer());
        BlockEntityRendererRegistry.register(AlchrimeaEntityTypes.EMERALD_TABLET_BLOCK_ENTITY, (BlockEntityRendererFactory.Context rendererDispatcherIn) -> new EmeraldTabletRenderer());
        BlockEntityRendererRegistry.register(AlchrimeaEntityTypes.JAR_BLOCK_ENTITY, (BlockEntityRendererFactory.Context rendererDispatcherIn) -> new JarRenderer());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(AlchrimeaObjects.MORIUM_CRYSTAL_AMBER, RenderLayer.getCutout());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(AlchrimeaObjects.MORIUM_CRYSTAL_CARNATION, RenderLayer.getCutout());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(AlchrimeaObjects.MORIUM_CRYSTAL_CERISE, RenderLayer.getCutout());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(AlchrimeaObjects.MORIUM_CRYSTAL_CRIMSON, RenderLayer.getCutout());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(AlchrimeaObjects.MORIUM_CRYSTAL_OCHRE, RenderLayer.getCutout());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(AlchrimeaObjects.MORIUM_CRYSTAL_OLYMPIC, RenderLayer.getCutout());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(AlchrimeaObjects.MORIUM_CRYSTAL_SHAMROCK, RenderLayer.getCutout());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(AlchrimeaObjects.JAR, RenderLayer.getCutoutMipped());
    }
}
