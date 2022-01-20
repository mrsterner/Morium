package dev.mrsterner.alchrimea.common.item;

import dev.mrsterner.alchrimea.common.registry.AlchrimeaObjects;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nonnull;

public class MagnumOpusItem extends Item implements IAnimatable, ISyncable {

    public final AnimationFactory factory = new AnimationFactory(this);
    public boolean holdsItem = false;
    public boolean change = false;
    private static final int ANIM_OPEN = 1;
    private static final int ANIM_CLOSED = 0;
    public MagnumOpusItem(Settings settings) {
        super(settings);
        GeckoLibNetwork.registerSyncable(this);
    }

    @Nonnull
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user instanceof ServerPlayerEntity player) {
            /*
            Book book = BookRegistry.INSTANCE.books.get(Registry.ITEM.getId(this));
            PatchouliAPI.get().openBookGUI(player, book.id);

             */
            //user.playSound(PatchouliSounds.getSound(book.openSound, PatchouliSounds.BOOK_OPEN), 1, (float) (0.7 + Math.random() * 0.4));
        }
        return super.use(world, user, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(entity instanceof PlayerEntity player && !world.isClient ){
            this.holdsItem = selected;
            /*
            if(selected){
                setAnimation(stack,world, player, ANIM_OPEN);
                change = false;
            }else if(!selected){
                setAnimation(stack,world, player, ANIM_CLOSED);
                change = true;
            }

             */
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private void setAnimation(ItemStack stack, World world,PlayerEntity player, int animation){
        if(!world.isClient) {
            final int id = GeckoLibUtil.guaranteeIDForStack(stack, (ServerWorld) world);
            final AnimationController<?> controller = GeckoLibUtil.getControllerForID(this.factory, id, "controllerName");
            controller.markNeedsReload();
            GeckoLibNetwork.syncAnimation(player, this, id, animation);
            for (PlayerEntity otherPlayer : PlayerLookup.tracking(player)) {
                GeckoLibNetwork.syncAnimation(otherPlayer, this, id, animation);
            }
        }
    }

    private <E extends Item & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(holdsItem){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.magnum_opus.opening", false).addAnimation("animation.magnum_opus.open", true));
        }else{
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.magnum_opus.closing", false).addAnimation("animation.magnum_opus.closed", true));
        }


        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controllerName", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public void onAnimationSync(int id, int state) {
        /*
        if (state == ANIM_OPEN) {
            final AnimationController<?> controller = GeckoLibUtil.getControllerForID(this.factory, id, "controllerName");
            if (controller.getCurrentAnimation() == null || controller.getAnimationState() == AnimationState.Stopped) {
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.magnum_opus.opening", false).addAnimation("animation.magnum_opus.open", true));

            }

        }
        if(state == ANIM_CLOSED) {
            final AnimationController<?> controller = GeckoLibUtil.getControllerForID(this.factory, id, "controllerName");
            if (controller.getCurrentAnimation() == null || controller.getAnimationState() == AnimationState.Stopped) {
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.magnum_opus.closed", false));
            }
        }

         */
    }
}
