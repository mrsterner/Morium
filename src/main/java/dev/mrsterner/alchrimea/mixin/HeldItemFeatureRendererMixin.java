package dev.mrsterner.alchrimea.mixin;

import dev.mrsterner.alchrimea.common.body.BodyParts;
import dev.mrsterner.alchrimea.common.registry.AlchrimeaComponents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(HeldItemFeatureRenderer.class)
public abstract class HeldItemFeatureRendererMixin<T extends LivingEntity, M extends EntityModel<T> & ModelWithArms> extends FeatureRenderer<T, M> {
    HeldItemFeatureRendererMixin(FeatureRendererContext<T, M> context) {
        super(context);
    }


    @Inject(method = "renderItem", at = @At(value = "HEAD"), cancellable = true)
    private void renderItem(LivingEntity entity, ItemStack stack, ModelTransformation.Mode transformationMode, Arm arm, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo callbackInfo) {
        if (entity instanceof PlayerEntity player && !AlchrimeaComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.RIGHTARM) && arm == Arm.RIGHT) {
            callbackInfo.cancel();
        }
        if (entity instanceof PlayerEntity player && !AlchrimeaComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.LEFTARM) && arm == Arm.LEFT) {
            callbackInfo.cancel();
        }
    }
}
