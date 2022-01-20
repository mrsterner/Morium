package dev.mrsterner.alchrimea.client.renderer;

import dev.mrsterner.alchrimea.common.body.BodyParts;
import dev.mrsterner.alchrimea.common.registry.AlchrimeaComponents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class MouthItemFeatureRenderer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>>  {

    public MouthItemFeatureRenderer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context, EntityModelLoader loader) {
        super(context);
    }



    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity player, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if(!player.isInvisible() && !AlchrimeaComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.RIGHTARM)){
            matrices.push();
            getContextModel().head.rotate(matrices);
            matrices.translate(0,-0.2,-0.5);
            ItemStack itemStack = player.getEquippedStack(EquipmentSlot.MAINHAND);
            MinecraftClient.getInstance().getHeldItemRenderer().renderItem(player, itemStack, ModelTransformation.Mode.GROUND, false, matrices, vertexConsumers, light);
            matrices.pop();
        }
        if(!player.isInvisible() && !AlchrimeaComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.LEFTARM)){
            matrices.push();
            getContextModel().head.rotate(matrices);
            matrices.translate(0,-0.2,-0.5);
            ItemStack itemStack = player.getEquippedStack(EquipmentSlot.OFFHAND);
            MinecraftClient.getInstance().getHeldItemRenderer().renderItem(player, itemStack, ModelTransformation.Mode.GROUND, false, matrices, vertexConsumers, light);
            matrices.pop();
        }

    }
}
