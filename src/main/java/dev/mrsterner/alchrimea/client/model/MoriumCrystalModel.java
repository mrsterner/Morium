package dev.mrsterner.alchrimea.client.model;


import dev.mrsterner.alchrimea.common.block.entity.MoriumCrystalBlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.resource.GeckoLibCache;
import software.bernie.shadowed.eliotlash.molang.MolangParser;

public class MoriumCrystalModel extends AnimatedGeoModel<MoriumCrystalBlockEntity> {

    public String getBlockFromEntity(MoriumCrystalBlockEntity alchrimeaCrystalBlockEntity){
        return Registry.BLOCK.getKey(alchrimeaCrystalBlockEntity.getCachedState().getBlock()).get().getValue().getPath();
    }

    @Override
    public Identifier getModelLocation(MoriumCrystalBlockEntity object) {
        return new Identifier(dev.mrsterner.alchrimea.Alchrimea.MODID, "geo/morium_crystal.geo.json");
    }

    @Override
    public Identifier getTextureLocation(MoriumCrystalBlockEntity object) {
        return new Identifier(dev.mrsterner.alchrimea.Alchrimea.MODID, "textures/block/"+getBlockFromEntity(object)+".png");
    }

    @Override
    public Identifier getAnimationFileLocation(MoriumCrystalBlockEntity animatable) {
        return new Identifier(dev.mrsterner.alchrimea.Alchrimea.MODID, "animations/morium_crystal.animation.json");
    }

    @Override
    public void setMolangQueries(IAnimatable animatable, double currentTick) {
        super.setMolangQueries(animatable, currentTick);
        MolangParser parser = GeckoLibCache.getInstance().parser;
        MoriumCrystalBlockEntity alchrimeaCrystalBlockEntity = (MoriumCrystalBlockEntity)animatable;
        parser.setValue("query.evolutionx", currentTick);
        parser.setValue("query.evolutiony", currentTick);
        parser.setValue("query.evolutionz", currentTick);
        parser.setValue("query.size1", 0.5F);
        parser.setValue("query.size2", 1);
        parser.setValue("query.size3", 1.5F);
    }
}
