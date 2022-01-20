package dev.mrsterner.alchrimea.client.shader;


import net.minecraft.client.render.*;
import net.minecraft.resource.ResourceManager;
import com.mojang.datafixers.util.Pair;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

public class AlchrimeaShader  {
    private static Shader alchrimeaCrystal;
    private static Shader theThruth;
    private static Shader alchrimeaPylon;


    public static void init(ResourceManager resourceManager, List<Pair<Shader, Consumer<Shader>>> registrations) throws IOException {
        registrations.add(Pair.of(
            new Shader(resourceManager, "alchrimea__moriumcrystal", VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL),
            inst -> alchrimeaCrystal = inst));
        registrations.add(Pair.of(
            new Shader(resourceManager, "alchrimea__thetruth", VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL),
            inst -> theThruth = inst)
        );
        registrations.add(Pair.of(
            new Shader(resourceManager, "alchrimea__pylon", VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL),
            inst -> alchrimeaPylon = inst)
        );
    }
    public static Shader alchrimeaCrystal() {
        return alchrimeaCrystal;
    }

    public static Shader alchrimeaPylon() {
        return alchrimeaPylon;
    }

    public static Shader theTruth() {
        return theThruth;
    }
}
