package dev.mrsterner.alchrimea.client.renderer;

import dev.mrsterner.alchrimea.client.renderlayer.AlchrimeaRenderLayer;
import dev.mrsterner.alchrimea.client.shader.AlchrimeaShader;
import dev.mrsterner.alchrimea.common.entity.TheTruthEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.Shader;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.DefaultSkinHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;


public class TheTruthEntityRenderer extends BipedEntityRenderer<TheTruthEntity, BipedEntityModel<TheTruthEntity>> {

    public static final float DEFAULT_GRAIN_INTENSITY = 0.05F;
    public static final float DEFAULT_DISFIGURATION = 0.025F;

    private final Model normalModel;
    private final Model slimModel;

    public TheTruthEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new Model(ctx.getPart(EntityModelLayers.PLAYER)), 0F);
        this.normalModel = (Model) this.getModel();
        this.slimModel = new Model(ctx.getPart(EntityModelLayers.PLAYER_SLIM));
    }

    @Override
    public void render(TheTruthEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        Shader shader = AlchrimeaShader.theTruth();

        if (shader != null) {
            float grainIntensity, disfiguration;
            disfiguration = (0.025F + mobEntity.hurtTime * ((1F - 0.15F) / 20F)) / 2F;
            grainIntensity = 0.05F + mobEntity.hurtTime * ((1F - 0.15F) / 10F);

            shader.getUniform("GrainIntensity").set(grainIntensity);
            shader.getUniform("Disfiguration").set(disfiguration);
        }

        var view = MinecraftClient.getInstance().getCameraEntity();
        if (view instanceof AbstractClientPlayerEntity && DefaultSkinHelper.getModel(view.getUuid()).equals("slim")) {
            this.model = slimModel;
        } else {
            this.model = normalModel;
        }
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(TheTruthEntity mobEntity) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (!(mc.getCameraEntity() instanceof AbstractClientPlayerEntity)) {
            return DefaultSkinHelper.getTexture(mobEntity.getUuid());
        }
        return ((AbstractClientPlayerEntity) mc.getCameraEntity()).getSkinTexture();
    }


    @Override
    protected boolean isVisible(TheTruthEntity entity) {
        return true;
    }


    private static class Model extends BipedEntityModel<TheTruthEntity> {
        Model(ModelPart root) {
            super(root, AlchrimeaRenderLayer::getTheTruthLayer);
        }
    }

}
