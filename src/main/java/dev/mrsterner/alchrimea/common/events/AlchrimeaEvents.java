package dev.mrsterner.alchrimea.common.events;

import dev.mrsterner.alchrimea.common.body.BodyParts;
import dev.mrsterner.alchrimea.common.registry.AlchrimeaComponents;
import dev.mrsterner.alchrimea.common.util.AlchrimeaUtils;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.server.world.ServerWorld;

public class AlchrimeaEvents implements ServerTickEvents.StartWorldTick {

    public void init() {
        ServerTickEvents.START_WORLD_TICK.register(this);
    }

    @Override
    public void onStartTick(ServerWorld serverWorld) {
        serverWorld.getPlayers().stream().forEach(serverPlayerEntity -> {
            if(AlchrimeaUtils.ifMissingLegs(serverPlayerEntity)){
                serverPlayerEntity.dropItem(serverPlayerEntity.getEquippedStack(EquipmentSlot.LEGS).getItem(), 1);
                serverPlayerEntity.getEquippedStack(EquipmentSlot.LEGS).decrement(1);
                serverPlayerEntity.dropItem(serverPlayerEntity.getEquippedStack(EquipmentSlot.FEET).getItem(), 1);
                serverPlayerEntity.getEquippedStack(EquipmentSlot.FEET).decrement(1);
            }
            if(!AlchrimeaComponents.BODY_COMPONENT.get(serverPlayerEntity).hasBodyPart(BodyParts.TORSO)){
                serverPlayerEntity.dropItem(serverPlayerEntity.getEquippedStack(EquipmentSlot.CHEST).getItem(), 1);
                serverPlayerEntity.getEquippedStack(EquipmentSlot.CHEST).decrement(1);
            }
            if(!AlchrimeaComponents.BODY_COMPONENT.get(serverPlayerEntity).hasBodyPart(BodyParts.HEAD)){
                serverPlayerEntity.dropItem(serverPlayerEntity.getEquippedStack(EquipmentSlot.HEAD).getItem(), 1);
                serverPlayerEntity.getEquippedStack(EquipmentSlot.HEAD).decrement(1);
            }
        });
    }
}
