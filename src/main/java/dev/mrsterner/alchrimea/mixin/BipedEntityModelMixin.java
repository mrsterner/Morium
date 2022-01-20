package dev.mrsterner.alchrimea.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(BipedEntityModel.class)
public abstract class BipedEntityModelMixin<T extends LivingEntity> extends AnimalModel<T> implements ModelWithArms, ModelWithHead {

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/model/BipedEntityModel;animateArms(Lnet/minecraft/entity/LivingEntity;F)V"), method = "setAngles", cancellable = true)
    private void noSneakAnimation(T livingEntity, float f, float g, float h, float i, float j, CallbackInfo info) {
        /*
        if(livingEntity instanceof PlayerEntity player && !MOComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.LEFTLEG) && !MOComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.RIGHTLEG)){
            info.cancel();
        }

         */

    }
}
