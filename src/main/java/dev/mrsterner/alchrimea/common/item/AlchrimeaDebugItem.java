package dev.mrsterner.alchrimea.common.item;

import dev.mrsterner.alchrimea.common.body.BodyParts;
import dev.mrsterner.alchrimea.common.registry.AlchrimeaComponents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class AlchrimeaDebugItem extends Item {
    public DebugMode mode = DebugMode.HEAD;
    public AlchrimeaDebugItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if(user.isSneaking() && !world.isClient){
            mode = mode.next();
        }else if(!user.isSneaking()){
            switch (mode){
                case HEAD -> AlchrimeaComponents.BODY_COMPONENT.get(user).setBodyPart(BodyParts.HEAD, !AlchrimeaComponents.BODY_COMPONENT.get(user).getBodyPart().get(BodyParts.HEAD));
                case TORSO -> AlchrimeaComponents.BODY_COMPONENT.get(user).setBodyPart(BodyParts.TORSO, !AlchrimeaComponents.BODY_COMPONENT.get(user).getBodyPart().get(BodyParts.TORSO));
                case LEFTARM -> AlchrimeaComponents.BODY_COMPONENT.get(user).setBodyPart(BodyParts.LEFTARM, !AlchrimeaComponents.BODY_COMPONENT.get(user).getBodyPart().get(BodyParts.LEFTARM));
                case RIGHTARM -> AlchrimeaComponents.BODY_COMPONENT.get(user).setBodyPart(BodyParts.RIGHTARM, !AlchrimeaComponents.BODY_COMPONENT.get(user).getBodyPart().get(BodyParts.RIGHTARM));
                case LEFTLEG -> AlchrimeaComponents.BODY_COMPONENT.get(user).setBodyPart(BodyParts.LEFTLEG, !AlchrimeaComponents.BODY_COMPONENT.get(user).getBodyPart().get(BodyParts.LEFTLEG));
                case RIGHTLEG -> AlchrimeaComponents.BODY_COMPONENT.get(user).setBodyPart(BodyParts.RIGHTLEG, !AlchrimeaComponents.BODY_COMPONENT.get(user).getBodyPart().get(BodyParts.RIGHTLEG));
                case EYES -> AlchrimeaComponents.BODY_COMPONENT.get(user).setBodyPart(BodyParts.EYES, !AlchrimeaComponents.BODY_COMPONENT.get(user).getBodyPart().get(BodyParts.EYES));
                case STOMACH -> AlchrimeaComponents.BODY_COMPONENT.get(user).setBodyPart(BodyParts.STOMACH, !AlchrimeaComponents.BODY_COMPONENT.get(user).getBodyPart().get(BodyParts.STOMACH));
            }
            user.calculateDimensions();
        }
        return super.use(world, user, hand);
    }

    public enum DebugMode {
        HEAD,
        TORSO,
        LEFTARM,
        RIGHTARM,
        LEFTLEG,
        RIGHTLEG,
        EYES,
        STOMACH;
        private static DebugMode[] vals = values();
        public DebugMode next(){
            return vals[(this.ordinal()+1)% vals.length];
        }
    }
}
