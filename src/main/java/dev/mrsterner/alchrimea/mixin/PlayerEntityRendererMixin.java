package dev.mrsterner.alchrimea.mixin;

import dev.mrsterner.alchrimea.client.renderer.MouthItemFeatureRenderer;
import dev.mrsterner.alchrimea.common.body.BodyParts;
import dev.mrsterner.alchrimea.common.registry.AlchrimeaComponents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
	public PlayerEntityRendererMixin(EntityRendererFactory.Context ctx, PlayerEntityModel<AbstractClientPlayerEntity> model, float shadowRadius) {
		super(ctx, model, shadowRadius);
	}

	@Shadow
	protected abstract void setModelPose(AbstractClientPlayerEntity player);

	@Shadow
	private static BipedEntityModel.ArmPose getArmPose(AbstractClientPlayerEntity player, Hand hand) {
		throw new AssertionError();
	}

	@Inject(method = "<init>", at = @At("TAIL"))
	private void PlayerEntityRenderer(EntityRendererFactory.Context ctx, boolean slim, CallbackInfo callbackInfo) {
		addFeature(new MouthItemFeatureRenderer(this, ctx.getModelLoader()));
	}

	@Inject(method = "getPositionOffset", at = @At("RETURN"), cancellable = true)
	public void noOffset(AbstractClientPlayerEntity abstractClientPlayerEntity, float f, CallbackInfoReturnable<Vec3d> callbackInfo){
		if(!AlchrimeaComponents.BODY_COMPONENT.get(abstractClientPlayerEntity).hasBodyPart(BodyParts.LEFTLEG) && !AlchrimeaComponents.BODY_COMPONENT.get(abstractClientPlayerEntity).hasBodyPart(BodyParts.RIGHTLEG)){
			callbackInfo.setReturnValue(super.getPositionOffset(abstractClientPlayerEntity, f));
		}
	}


	@Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/PlayerEntityRenderer;setModelPose(Lnet/minecraft/client/network/AbstractClientPlayerEntity;)V"), method = "render")
	public void setModelPoseRedirect(PlayerEntityRenderer playerEntityRenderer, AbstractClientPlayerEntity player) {
			PlayerEntityModel<AbstractClientPlayerEntity> playerEntityModel = this.getModel();
			if (player.isSpectator()) {
				playerEntityModel.setVisible(false);
				playerEntityModel.head.visible = true;
				playerEntityModel.hat.visible = true;
			} else {
				playerEntityModel.setVisible(true);
				playerEntityModel.hat.visible = player.isPartVisible(PlayerModelPart.HAT);
				playerEntityModel.jacket.visible = player.isPartVisible(PlayerModelPart.JACKET);
				playerEntityModel.leftPants.visible = player.isPartVisible(PlayerModelPart.LEFT_PANTS_LEG);
				playerEntityModel.rightPants.visible = player.isPartVisible(PlayerModelPart.RIGHT_PANTS_LEG);
				playerEntityModel.leftSleeve.visible = player.isPartVisible(PlayerModelPart.LEFT_SLEEVE);
				playerEntityModel.rightSleeve.visible = player.isPartVisible(PlayerModelPart.RIGHT_SLEEVE);
				playerEntityModel.sneaking = (AlchrimeaComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.LEFTLEG) || AlchrimeaComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.RIGHTLEG)) && player.isInSneakingPose();
				BipedEntityModel.ArmPose armPose = getArmPose(player, Hand.MAIN_HAND);
				BipedEntityModel.ArmPose armPose2 = getArmPose(player, Hand.OFF_HAND);
				if (armPose.isTwoHanded()) {
					armPose2 = player.getOffHandStack().isEmpty() ? BipedEntityModel.ArmPose.EMPTY : BipedEntityModel.ArmPose.ITEM;
				}

				if (player.getMainArm() == Arm.RIGHT) {
					playerEntityModel.rightArmPose = armPose;
					playerEntityModel.leftArmPose = armPose2;
				} else {
					playerEntityModel.rightArmPose = armPose2;
					playerEntityModel.leftArmPose = armPose;
				}
			}
		if(!AlchrimeaComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.HEAD)){
			playerEntityModel.head.visible = false;
		}
		if(!AlchrimeaComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.LEFTARM)){
			playerEntityModel.leftArm.visible = false;
		}
		if(!AlchrimeaComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.RIGHTARM)){
			playerEntityModel.rightArm.visible = false;
		}
		if(!AlchrimeaComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.LEFTLEG)){
			playerEntityModel.leftLeg.visible = false;
		}
		if(!AlchrimeaComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.RIGHTLEG)){
			playerEntityModel.rightLeg.visible = false;
		}
		if(!AlchrimeaComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.TORSO)){
			playerEntityModel.body.visible = false;
		}
		}


/*
	@Inject(at = @At("TAIL"), method = "setModelPose")
	private void init(AbstractClientPlayerEntity player, CallbackInfo ci) {
		PlayerEntityModel<AbstractClientPlayerEntity> playerEntityModel = this.getModel();
		if(!MOComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.HEAD)){
			playerEntityModel.head.visible = false;
		}
		if(!MOComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.LEFTARM)){
			playerEntityModel.leftArm.visible = false;
		}
		if(!MOComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.RIGHTARM)){
			playerEntityModel.rightArm.visible = false;
		}
		if(!MOComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.LEFTLEG)){
			playerEntityModel.leftLeg.visible = false;
		}
		if(!MOComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.RIGHTLEG)){
			playerEntityModel.rightLeg.visible = false;
		}
		if(!MOComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.TORSO)){
			playerEntityModel.body.visible = false;
		}
	}



 */

}
