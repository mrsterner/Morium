package dev.mrsterner.morium.common.block.entity;

import dev.mrsterner.morium.common.registry.MoriumBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.block.entity.EnchantingTableBlockEntityRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EmeraldTabletBlockEntity extends BlockEntity implements IAnimatable {
    public float partial;
    public boolean hasPlayer;
    AnimationFactory factory = new AnimationFactory(this);
    public float tabletRotation;
    public int ticks;
    public float flippity;
    public float floppity;
    public EmeraldTabletBlockEntity(BlockPos pos, BlockState state) {
        super(MoriumBlockEntityTypes.EMERALD_TABLET_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, EmeraldTabletBlockEntity blockEntity) {
        PlayerEntity playerEntity = world.getClosestPlayer((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, 3.0D, false);
        blockEntity.tabletRotation = blockEntity.floppity;
        if (playerEntity != null) {
            double d = playerEntity.getX() - ((double)pos.getX() + 0.5D);
            double e = playerEntity.getZ() - ((double)pos.getZ() + 0.5D);
            blockEntity.flippity = (float) MathHelper.atan2(e, d);
            blockEntity.hasPlayer = true;
        }else{
            blockEntity.hasPlayer = false;
        }
        while(blockEntity.floppity >= 3.1415927F) {
            blockEntity.floppity -= 6.2831855F;
        }

        while(blockEntity.floppity < -3.1415927F) {
            blockEntity.floppity += 6.2831855F;
        }

        while(blockEntity.flippity >= 3.1415927F) {
            blockEntity.flippity -= 6.2831855F;
        }

        while(blockEntity.flippity < -3.1415927F) {
            blockEntity.flippity += 6.2831855F;
        }

        float d;
        for(d = blockEntity.flippity - blockEntity.floppity; d >= 3.1415927F; d -= 6.2831855F) {
        }

        while(d < -3.1415927F) {
            d += 6.2831855F;
        }
        blockEntity.floppity += d * 0.4F;
        ++blockEntity.ticks;
    }


    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controllerName", 40, this::predicate));
    }

    public <E extends BlockEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        AnimationBuilder builder = new AnimationBuilder();
        if(hasPlayer){
            builder.addAnimation("animation.emerald_tablet.hover_stop", true);
        }else{
            builder.addAnimation("animation.emerald_tablet.idle", true);
        }

        event.getController().setAnimation(builder);
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
