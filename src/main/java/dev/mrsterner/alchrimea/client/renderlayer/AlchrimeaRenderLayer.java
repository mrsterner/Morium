package dev.mrsterner.alchrimea.client.renderlayer;

import dev.mrsterner.alchrimea.Alchrimea;
import dev.mrsterner.alchrimea.client.shader.AlchrimeaShader;
import dev.mrsterner.alchrimea.mixin.RenderLayerAccessor;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.function.Function;

public class AlchrimeaRenderLayer extends RenderLayer {
    public AlchrimeaRenderLayer(String name, VertexFormat vertexFormat, VertexFormat.DrawMode drawMode, int expectedBufferSize, boolean hasCrumbling, boolean translucent, Runnable startAction, Runnable endAction) {
        super(name, vertexFormat, drawMode, expectedBufferSize, hasCrumbling, translucent, startAction, endAction);
    }

    private static RenderLayer makeLayer(String name, VertexFormat format, VertexFormat.DrawMode mode, int bufSize, boolean hasCrumbling, boolean sortOnUpload, RenderLayer.MultiPhaseParameters glState) {
        return RenderLayerAccessor.of(name, format, mode, bufSize, hasCrumbling, sortOnUpload, glState);
    }

    private static final Function<Identifier, RenderLayer> THE_TRUTH = Util.memoize(texture -> {
        RenderLayer.MultiPhaseParameters glState = RenderLayer.MultiPhaseParameters.builder()
            .shader(new RenderPhase.Shader(AlchrimeaShader::theTruth))
            .texture(new RenderPhase.Texture(texture, false, false))
            .transparency(TRANSLUCENT_TRANSPARENCY)
            .cull(DISABLE_CULLING)
            .lightmap(ENABLE_LIGHTMAP)
            .overlay(ENABLE_OVERLAY_COLOR)
            .build(true);
        return makeLayer(Alchrimea.MODID + "theTruth", VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL, VertexFormat.DrawMode.QUADS, 256, true, true, glState);
    });

    private static final Function<Identifier, RenderLayer> MORIUM_CRYSTAL = Util.memoize(texture -> {
        RenderLayer.MultiPhaseParameters glState = RenderLayer.MultiPhaseParameters.builder()
            .shader(new RenderPhase.Shader(AlchrimeaShader::alchrimeaCrystal))
            .texture(new RenderPhase.Texture(texture, false, false))
            .transparency(RenderPhase.TRANSLUCENT_TRANSPARENCY)
            .cull(RenderPhase.DISABLE_CULLING)
            .lightmap(RenderLayer.ENABLE_LIGHTMAP)
            .overlay(RenderLayer.DISABLE_OVERLAY_COLOR)
            .layering(RenderLayer.NO_LAYERING)
            .build(false);
        return makeLayer(Alchrimea.MODID + "alchrimeaCrystal", VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL, VertexFormat.DrawMode.QUADS, 256, true, true, glState);
    });

    private static final Function<Identifier, RenderLayer> JAR = Util.memoize(texture -> {
        RenderLayer.MultiPhaseParameters glState = RenderLayer.MultiPhaseParameters.builder()
            .shader(RenderLayer.ENTITY_TRANSLUCENT_SHADER)
            .texture(new RenderPhase.Texture(texture, false, false))
            .transparency(RenderPhase.TRANSLUCENT_TRANSPARENCY)
            .cull(RenderPhase.ENABLE_CULLING)
            .lightmap(RenderLayer.ENABLE_LIGHTMAP)
            .overlay(RenderLayer.ENABLE_OVERLAY_COLOR)
            .layering(RenderLayer.NO_LAYERING)
            .target(RenderLayer.ITEM_TARGET)
            .build(false);
        return makeLayer(Alchrimea.MODID + "jar", VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL, VertexFormat.DrawMode.QUADS, 256, true, true, glState);
    });

    private static final Function<Identifier, RenderLayer> JAR_ITEM = Util.memoize(texture -> {
        RenderLayer.MultiPhaseParameters glState = RenderLayer.MultiPhaseParameters.builder()
            .shader(RenderLayer.ENTITY_TRANSLUCENT_SHADER)
            .texture(new RenderPhase.Texture(texture, false, false))
            .transparency(RenderPhase.TRANSLUCENT_TRANSPARENCY)
            .cull(RenderPhase.DISABLE_CULLING)
            .lightmap(RenderLayer.ENABLE_LIGHTMAP)
            .overlay(RenderLayer.ENABLE_OVERLAY_COLOR).target(RenderLayer.MAIN_TARGET)
            .build(false);

        return makeLayer(Alchrimea.MODID + "jar_item", VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL, VertexFormat.DrawMode.QUADS, 128, false, true, glState);
    });

    private static final Function<Identifier, RenderLayer> JAR_ITEM_2 = Util.memoize(texture -> {
        RenderLayer.MultiPhaseParameters glState = RenderLayer.MultiPhaseParameters.builder()
            .shader(RenderLayer.ENTITY_TRANSLUCENT_SHADER)
            .texture(new RenderPhase.Texture(texture, false, false))
            .transparency(RenderPhase.TRANSLUCENT_TRANSPARENCY)
            .cull(RenderPhase.DISABLE_CULLING)
            .lightmap(RenderLayer.ENABLE_LIGHTMAP)
            .overlay(RenderLayer.ENABLE_OVERLAY_COLOR)
            .target(RenderLayer.ITEM_TARGET)
            .build(false);

        return makeLayer(Alchrimea.MODID + "jar_item_2", VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL, VertexFormat.DrawMode.QUADS, 128, false, true, glState);
    });


    public static RenderLayer getAlchrimeaLayer(Identifier texture) {
        return MORIUM_CRYSTAL.apply(texture);
    }

    public static RenderLayer getJarLayer(Identifier texture) {
        return JAR.apply(texture);
    }

    public static RenderLayer getJarItemLayer(Identifier texture) {
        return JAR_ITEM.apply(texture);
    }

    public static RenderLayer getJarItemLayer2(Identifier texture) {
        return JAR_ITEM_2.apply(texture);
    }



    public static RenderLayer getTheTruthLayer(Identifier texture) {
        return THE_TRUTH.apply(texture);
    }
}
