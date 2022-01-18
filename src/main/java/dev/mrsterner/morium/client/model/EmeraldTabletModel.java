package dev.mrsterner.morium.client.model;


import dev.mrsterner.morium.Morium;
import dev.mrsterner.morium.common.block.EmeraldTabletBlock;
import dev.mrsterner.morium.common.block.MoriumCrystalBlock;
import dev.mrsterner.morium.common.block.entity.EmeraldTabletBlockEntity;
import dev.mrsterner.morium.common.block.entity.MoriumCrystalBlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.resource.GeckoLibCache;
import software.bernie.shadowed.eliotlash.molang.MolangParser;

public class EmeraldTabletModel extends AnimatedGeoModel<EmeraldTabletBlockEntity> {

    public String getEntity(EmeraldTabletBlock emeraldTabletBlock){
        return Registry.BLOCK.getKey(emeraldTabletBlock).get().getValue().getPath();
    }

    @Override
    public Identifier getModelLocation(EmeraldTabletBlockEntity object) {
        return new Identifier(Morium.MODID, "geo/emerald_tablet.geo.json");
    }

    @Override
    public Identifier getTextureLocation(EmeraldTabletBlockEntity object) {
        return new Identifier(Morium.MODID, "textures/block/"+getEntity((EmeraldTabletBlock) object.getCachedState().getBlock())+".png");
    }

    @Override
    public Identifier getAnimationFileLocation(EmeraldTabletBlockEntity animatable) {
        return new Identifier(Morium.MODID, "animations/emerald_tablet.animation.json");
    }

    @Override
    public void setMolangQueries(IAnimatable animatable, double currentTick) {
        super.setMolangQueries(animatable, currentTick);
        MolangParser parser = GeckoLibCache.getInstance().parser;
        EmeraldTabletBlockEntity emeraldTabletBlockEntity = (EmeraldTabletBlockEntity)animatable;
        float h;
        for(h = emeraldTabletBlockEntity.floppity - emeraldTabletBlockEntity.tabletRotation; h >= 3.1415927F; h -= 6.2831855F) {
        }

        while(h < -3.1415927F) {
            h += 6.2831855F;
        }
        float k = emeraldTabletBlockEntity.tabletRotation + h * emeraldTabletBlockEntity.partial;
        parser.setValue("query.rotation", (k*180/3.1415927) + 90);

    }
}
