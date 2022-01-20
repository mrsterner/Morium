package dev.mrsterner.alchrimea.client.model;


import dev.mrsterner.alchrimea.common.block.AlchrimeaBlockItem;
import dev.mrsterner.alchrimea.common.block.entity.JarBlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class JarItemModel extends AnimatedGeoModel<AlchrimeaBlockItem> {



    @Override
    public Identifier getModelLocation(AlchrimeaBlockItem object) {
        return new Identifier(dev.mrsterner.alchrimea.Alchrimea.MODID, "geo/jar.geo.json");
    }

    @Override
    public Identifier getTextureLocation(AlchrimeaBlockItem object) {
        return new Identifier(dev.mrsterner.alchrimea.Alchrimea.MODID, "textures/block/jar.png");
    }

    @Override
    public Identifier getAnimationFileLocation(AlchrimeaBlockItem animatable) {
        return new Identifier(dev.mrsterner.alchrimea.Alchrimea.MODID, "animations/jar.animation.json");
    }

}
