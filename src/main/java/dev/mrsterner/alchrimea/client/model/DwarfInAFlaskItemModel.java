package dev.mrsterner.alchrimea.client.model;

import dev.mrsterner.alchrimea.common.block.AlchrimeaBlockItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DwarfInAFlaskItemModel extends AnimatedGeoModel<AlchrimeaBlockItem> {
    @Override
    public Identifier getAnimationFileLocation(AlchrimeaBlockItem entity) {
        return new Identifier(dev.mrsterner.alchrimea.Alchrimea.MODID, "animations/dwarf_in_a_flask.animation.json");
    }

    @Override
    public Identifier getModelLocation(AlchrimeaBlockItem animatable) {
        return new Identifier(dev.mrsterner.alchrimea.Alchrimea.MODID, "geo/dwarf_in_a_flask.geo.json");
    }

    @Override
    public Identifier getTextureLocation(AlchrimeaBlockItem entity) {
        return new Identifier(dev.mrsterner.alchrimea.Alchrimea.MODID, "textures/block/dwarf_in_a_flask.png");
    }
}
