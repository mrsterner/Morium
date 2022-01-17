package dev.mrsterner.morium.client.renderlayer;

import dev.mrsterner.morium.Morium;
import dev.mrsterner.morium.client.shader.MoriumShader;
import dev.mrsterner.morium.mixin.RenderLayerAccessor;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.function.Function;

public class GlowyRenderLayer extends RenderLayer {
    public GlowyRenderLayer(String name, VertexFormat vertexFormat, VertexFormat.DrawMode drawMode, int expectedBufferSize, boolean hasCrumbling, boolean translucent, Runnable startAction, Runnable endAction) {
        super(name, vertexFormat, drawMode, expectedBufferSize, hasCrumbling, translucent, startAction, endAction);
    }

    private static RenderLayer makeLayer(String name, VertexFormat format, VertexFormat.DrawMode mode, int bufSize, boolean hasCrumbling, boolean sortOnUpload, RenderLayer.MultiPhaseParameters glState) {
        return RenderLayerAccessor.of(name, format, mode, bufSize, hasCrumbling, sortOnUpload, glState);
    }

    private static final Function<Identifier, RenderLayer> MORIUM_CRYSTAL = Util.memoize(texture -> {
        RenderLayer.MultiPhaseParameters glState = RenderLayer.MultiPhaseParameters.builder()
            .shader(new RenderPhase.Shader(MoriumShader::moriumCrystal))
            .texture(new RenderPhase.Texture(texture, false, false))
            .transparency(TRANSLUCENT_TRANSPARENCY)
            .cull(DISABLE_CULLING)
            .lightmap(RenderLayer.ENABLE_LIGHTMAP)
            .overlay(RenderLayer.DISABLE_OVERLAY_COLOR)
            .layering(RenderLayer.NO_LAYERING)
            .build(false);
        return makeLayer(Morium.MODID + "moriumCrystal", VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL, VertexFormat.DrawMode.QUADS, 256, true, true, glState);
    });

    public static RenderLayer getMoriumLayer(Identifier texture) {
        return MORIUM_CRYSTAL.apply(texture);
    }
}
