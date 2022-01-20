package dev.mrsterner.alchrimea.common.util;

import dev.mrsterner.alchrimea.common.body.BodyParts;
import dev.mrsterner.alchrimea.common.registry.AlchrimeaComponents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

import java.util.LinkedHashSet;

public class AlchrimeaUtils {

    public static void renderFacingStrip(double px, double py, double pz, float angle, float scale, float alpha, int frames, int strip, int frame, float partialTicks, int color) {
        if (MinecraftClient.getInstance().getCameraEntity() instanceof PlayerEntity player) {
            Tessellator tessellator = Tessellator.getInstance();

            float arX = (float) player.getRotationVecClient().x;
            float arZ = (float) player.getRotationVecClient().z;
            float arYZ = (float) (player.getRotationVecClient().y*player.getRotationVecClient().z);
            float arXY = (float) (player.getRotationVecClient().x*player.getRotationVecClient().y);
            float arXZ = (float) (player.getRotationVecClient().x*player.getRotationVecClient().z);
            double iPX = player.prevX + (player.getX() - player.prevX) * (double)partialTicks;
            double iPY = player.prevY + (player.getY() - player.prevY) * (double)partialTicks;
            double iPZ = player.prevZ + (player.getZ() - player.prevZ) * (double)partialTicks;

            //GL46.glTranslated(-iPX, -iPY, -iPZ);
            tessellator.draw();
            tessellator.getBuffer().light(220);
            tessellator.getBuffer().color(color);
            Vec3d v1 = new Vec3d((double)(-arX * scale - arYZ * scale), (double)(-arXZ * scale), (double)(-arZ * scale - arXY * scale));
            Vec3d v2 = new Vec3d((double)(-arX * scale + arYZ * scale), (double)(arXZ * scale), (double)(-arZ * scale + arXY * scale));
            Vec3d v3 = new Vec3d((double)(arX * scale + arYZ * scale), (double)(arXZ * scale), (double)(arZ * scale + arXY * scale));
            Vec3d v4 = new Vec3d((double)(arX * scale - arYZ * scale), (double)(-arXZ * scale), (double)(arZ * scale - arXY * scale));
            if (angle != 0.0F) {
                Vec3d pvec = new Vec3d(iPX, iPY, iPZ);
                Vec3d tvec = new Vec3d(px, py, pz);
                Vec3d qvec = pvec.subtract(tvec).normalize();
                QuadHelper.setAxis(qvec, (double)angle).rotate(v1);
                QuadHelper.setAxis(qvec, (double)angle).rotate(v2);
                QuadHelper.setAxis(qvec, (double)angle).rotate(v3);
                QuadHelper.setAxis(qvec, (double)angle).rotate(v4);
            }

            float f2 = (float)frame / (float)frames;
            float f3 = (float)(frame + 1) / (float)frames;
            float f4 = (float)strip / (float)frames;
            float f5 = ((float)strip + 1.0F) / (float)frames;
            tessellator.getBuffer().normal(0.0F, 0.0F, -1.0F);
            tessellator.getBuffer().vertex((float) (px + v1.x), (float) (py + v1.y), (float)(pz + v1.z),255,255,255,125, f3, f5, RenderLayer.TRANSLUCENT_BUFFER_SIZE, 100,0,0, -1);
            tessellator.getBuffer().vertex((float) (px + v2.x), (float) (py + v2.y), (float)(pz + v2.z),255,255,255,125, f3, f4, RenderLayer.TRANSLUCENT_BUFFER_SIZE, 100,0,0, -1);
            tessellator.getBuffer().vertex((float) (px + v3.x), (float) (py + v3.y), (float)(pz + v3.z),255,255,255,125, f2, f4, RenderLayer.TRANSLUCENT_BUFFER_SIZE, 100,0,0, -1);
            tessellator.getBuffer().vertex((float) (px + v4.x), (float) (py + v4.y), (float)(pz + v4.z),255,255,255,125, f2, f5, RenderLayer.TRANSLUCENT_BUFFER_SIZE, 100,0,0, -1);
            tessellator.draw();
            //float x, float y, float z, float red, float green, float blue, float alpha, float u, float v, int overlay, int light, float normalX, float normalY, float normalZ
        }

    }

    private static LinkedHashSet<Integer> disabledSlots = new LinkedHashSet<>();
    public static boolean isDisabled(int slot) {
        return disabledSlots.contains(slot);
    }

    public static void lockSlot(int slot) {
        disabledSlots.add(slot);
    }

    public static void unlockSlot(int slot) {
        disabledSlots.remove(slot);
    }

    public static boolean ifMissingArmsLegsTorso(PlayerEntity player){
        return !AlchrimeaComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.TORSO) &&
               !AlchrimeaComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.RIGHTARM) &&
               !AlchrimeaComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.LEFTARM) &&
               !AlchrimeaComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.LEFTLEG) &&
               !AlchrimeaComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.RIGHTLEG);
    }

    public static boolean ifMissingLegs(PlayerEntity player){
        return !AlchrimeaComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.LEFTLEG) &&
               !AlchrimeaComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.RIGHTLEG);
    }

    public static void removeBodyPart(PlayerEntity player, BodyParts bodyParts){
        AlchrimeaComponents.BODY_COMPONENT.get(player).setBodyPart(bodyParts, false);
        switch (bodyParts){
            case STOMACH -> {}
            case EYES -> {}
            case LEFTLEG -> {}
            case RIGHTLEG -> {}
            case LEFTARM -> {
                player.dropItem(player.getOffHandStack(), false);
                lockSlot(40);
            }
            case RIGHTARM -> {player.dropItem(player.getMainHandStack(), false);}
            case TORSO -> {}
            case HEAD -> {}
        }
    }







}
