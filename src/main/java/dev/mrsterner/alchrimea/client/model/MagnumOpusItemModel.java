package dev.mrsterner.alchrimea.client.model;

import dev.mrsterner.alchrimea.common.item.MagnumOpusItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MagnumOpusItemModel extends AnimatedGeoModel<MagnumOpusItem> {
    @Override
    public Identifier getAnimationFileLocation(MagnumOpusItem entity) {
        return new Identifier(dev.mrsterner.alchrimea.Alchrimea.MODID, "animations/magnum_opus.animation.json");
    }

    @Override
    public Identifier getModelLocation(MagnumOpusItem animatable) {
        return new Identifier(dev.mrsterner.alchrimea.Alchrimea.MODID, "geo/magnum_opus.geo.json");
    }

    @Override
    public Identifier getTextureLocation(MagnumOpusItem entity) {
        return new Identifier(dev.mrsterner.alchrimea.Alchrimea.MODID, "textures/item/magnum_opus.png");
    }
}
