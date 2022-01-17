package dev.mrsterner.morium.client.shader;


import net.minecraft.client.render.*;
import net.minecraft.resource.ResourceManager;
import com.mojang.datafixers.util.Pair;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

public class MoriumShader  {
    private static Shader moriumCrystal;

    public static void init(ResourceManager resourceManager, List<Pair<Shader, Consumer<Shader>>> registrations) throws IOException {
        registrations.add(Pair.of(
            new Shader(resourceManager, "morium__moriumcrystal", VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL),
            inst -> moriumCrystal = inst));
    }
    public static Shader moriumCrystal() {
        return moriumCrystal;
    }
}
