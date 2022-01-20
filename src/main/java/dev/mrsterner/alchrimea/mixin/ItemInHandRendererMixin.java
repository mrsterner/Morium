package dev.mrsterner.alchrimea.mixin;

import dev.mrsterner.alchrimea.Alchrimea;
import dev.mrsterner.alchrimea.client.renderer.JarItemRenderer;
import dev.mrsterner.alchrimea.common.block.JarBlock;
import dev.mrsterner.alchrimea.common.block.entity.JarBlockEntity;
import dev.mrsterner.alchrimea.common.registry.AlchrimeaObjects;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.GeoModelProvider;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

@Mixin(HeldItemRenderer.class)
public class ItemInHandRendererMixin {

    @Inject(method = "renderItem", at = @At("HEAD"), cancellable = true)
    private void renderFirstPersonItem(LivingEntity livingEntity, ItemStack stack, ModelTransformation.Mode transformType, boolean leftHanded, MatrixStack matrixStack, VertexConsumerProvider buffers, int light, CallbackInfo ci) {
        if(livingEntity instanceof PlayerEntity player && player.getMainHandStack().isOf(AlchrimeaObjects.MAGNUM_OPUS) && stack.getItem().equals(AlchrimeaObjects.JAR.asItem())){
            System.out.println(stack.getNbt());
            if(stack.hasNbt()){
                NbtCompound nbtCompound = stack.getNbt();
                nbtCompound.getKeys().forEach(s -> System.out.println("NBT: "+s));
            }
            matrixStack.push();
            matrixStack.translate(0.5F,0.0,0.5F);
            Identifier LAYER = new Identifier(dev.mrsterner.alchrimea.Alchrimea.MODID, "textures/block/jar/ink_sac.png");



            matrixStack.pop();
            }




        /*
        if (livingEntity instanceof PlayerEntity player && player.getMainHandStack().isOf(AlchrimeaObjects.MAGNUM_OPUS)) {
            dev.mrsterner.alchrimea.client.renderer.MagnumOpusRendererOld.doRender(stack, leftHanded, poseStack, buffers, light, ClientTickHandler.partialTicks);
            ci.cancel();
        }

         */
    }
}
