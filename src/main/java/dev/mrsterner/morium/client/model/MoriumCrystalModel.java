package dev.mrsterner.morium.client.model;


import dev.mrsterner.morium.Morium;
import dev.mrsterner.morium.common.block.MoriumCrystalBlock;
import dev.mrsterner.morium.common.block.entity.MoriumCrystalBlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.resource.GeckoLibCache;
import software.bernie.shadowed.eliotlash.molang.MolangParser;

public class MoriumCrystalModel extends AnimatedGeoModel<MoriumCrystalBlockEntity> {

    public String getEntity(MoriumCrystalBlock bearTrapBlockEntity){
        return Registry.BLOCK.getKey(bearTrapBlockEntity).get().getValue().getPath();
    }

    @Override
    public Identifier getModelLocation(MoriumCrystalBlockEntity object) {
        return new Identifier(Morium.MODID, "geo/morium_crystal.geo.json");
    }

    @Override
    public Identifier getTextureLocation(MoriumCrystalBlockEntity object) {
        return new Identifier(Morium.MODID, "textures/block/"+getEntity((MoriumCrystalBlock) object.getCachedState().getBlock())+"_1.png");
    }

    @Override
    public Identifier getAnimationFileLocation(MoriumCrystalBlockEntity animatable) {
        return new Identifier(Morium.MODID, "animations/morium_crystal.animation.json");
    }

    @Override
    public void setMolangQueries(IAnimatable animatable, double currentTick) {
        super.setMolangQueries(animatable, currentTick);
        MolangParser parser = GeckoLibCache.getInstance().parser;
        MoriumCrystalBlockEntity moriumCrystalBlockEntity = (MoriumCrystalBlockEntity)animatable;
        parser.setValue("query.evolutionx", currentTick);
        parser.setValue("query.evolutiony", currentTick);
        parser.setValue("query.evolutionz", currentTick);
        parser.setValue("query.size1", 0.5F);
        parser.setValue("query.size2", 1);
        parser.setValue("query.size3", 1.5F);
    }
}
