package dev.mrsterner.alchrimea.client.model;


import dev.mrsterner.alchrimea.common.block.entity.JarBlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class JarBlockModel extends AnimatedGeoModel<JarBlockEntity> {

    public String getBlockFromEntity(JarBlockEntity jarBlockEntity){
        return Registry.BLOCK.getKey(jarBlockEntity.getCachedState().getBlock()).get().getValue().getPath();
    }

    @Override
    public Identifier getModelLocation(JarBlockEntity object) {
        return new Identifier(dev.mrsterner.alchrimea.Alchrimea.MODID, "geo/jar.geo.json");
    }

    @Override
    public Identifier getTextureLocation(JarBlockEntity object) {
        return new Identifier(dev.mrsterner.alchrimea.Alchrimea.MODID, "textures/block/"+getBlockFromEntity(object)+".png");
    }

    @Override
    public Identifier getAnimationFileLocation(JarBlockEntity animatable) {
        return new Identifier(dev.mrsterner.alchrimea.Alchrimea.MODID, "animations/jar.animation.json");
    }

}
