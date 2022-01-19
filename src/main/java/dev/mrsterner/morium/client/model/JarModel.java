package dev.mrsterner.morium.client.model;


import dev.mrsterner.morium.Morium;
import dev.mrsterner.morium.common.block.entity.EmeraldTabletBlockEntity;
import dev.mrsterner.morium.common.block.entity.JarBlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.resource.GeckoLibCache;
import software.bernie.shadowed.eliotlash.molang.MolangParser;

public class JarModel extends AnimatedGeoModel<JarBlockEntity> {

    public String getBlockFromEntity(JarBlockEntity jarBlockEntity){
        return Registry.BLOCK.getKey(jarBlockEntity.getCachedState().getBlock()).get().getValue().getPath();
    }

    @Override
    public Identifier getModelLocation(JarBlockEntity object) {
        return new Identifier(Morium.MODID, "geo/jar.geo.json");
    }

    @Override
    public Identifier getTextureLocation(JarBlockEntity object) {
        return new Identifier(Morium.MODID, "textures/block/"+getBlockFromEntity(object)+".png");
    }

    @Override
    public Identifier getAnimationFileLocation(JarBlockEntity animatable) {
        return new Identifier(Morium.MODID, "animations/jar.animation.json");
    }

}
