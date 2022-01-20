package dev.mrsterner.alchrimea.common.block.entity;

import dev.mrsterner.alchrimea.common.registry.AlchrimeaEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class MoriumCrystalBlockEntity extends BlockEntity implements IAnimatable {
    AnimationFactory factory = new AnimationFactory(this);
    public MoriumCrystalBlockEntity(BlockPos pos, BlockState state) {
        super(AlchrimeaEntityTypes.MORIUM_CRYSTAL_BLOCK_ENTITY, pos, state);
    }



    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controllerName", 0, this::predicate));
    }

    public <E extends BlockEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        AnimationBuilder builder = new AnimationBuilder();
        builder.addAnimation("animation.alchrimea_crystal.ring", true);
        event.getController().setAnimation(builder);
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
