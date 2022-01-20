package dev.mrsterner.alchrimea.common.component;

import dev.mrsterner.alchrimea.common.body.BodyParts;
import dev.mrsterner.alchrimea.common.registry.AlchrimeaComponents;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;

import java.util.LinkedHashMap;
import java.util.Map;

public class BodyComponent implements IBody, AutoSyncedComponent, PlayerComponent<BodyComponent> {
    private final Map<BodyParts, Boolean> bodyParts = new LinkedHashMap(){{
        put(BodyParts.HEAD, true);
        put(BodyParts.TORSO, true);
        put(BodyParts.LEFTLEG, true);
        put(BodyParts.RIGHTLEG, true);
        put(BodyParts.LEFTARM, true);
        put(BodyParts.RIGHTARM, true);
        put(BodyParts.EYES, true);
        put(BodyParts.STOMACH, true);
    }};
    private final PlayerEntity player;

    public BodyComponent(PlayerEntity player) {
        this.player = player;
    }


    @Override
    public void setBodyPart(BodyParts bodyPart, boolean b) {
        bodyParts.replace(bodyPart, b);
        AlchrimeaComponents.BODY_COMPONENT.sync(player);
    }

    @Override
    public Map<BodyParts, Boolean> getBodyPart() {
        return bodyParts;
    }

    @Override
    public boolean hasBodyPart(BodyParts bodyPart) {
        return bodyParts.get(bodyPart);
    }

    public void serverTick() {

    }

    public float getHeight(){
        if(!AlchrimeaComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.RIGHTLEG) && !AlchrimeaComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.LEFTLEG)){
            if(!AlchrimeaComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.TORSO) && !AlchrimeaComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.LEFTARM) && !AlchrimeaComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.RIGHTARM)){
                return 0.55F;
            }
            return 1.2F;
        }
        return 1.8F;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        int index = 0;
        NbtList bodyPartList = tag.getList("BodyParts", NbtType.COMPOUND);
        for(Map.Entry<BodyParts, Boolean> entry : bodyParts.entrySet()){
            NbtCompound bodyCompund = bodyPartList.getCompound(index);
            setBodyPart(entry.getKey().fromString(bodyCompund.getString("BodyPart")), entry.getValue());
            index++;
        }
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.put("BodyParts", toNbtBody());
    }


    public NbtList toNbtBody() {
        NbtList bodyList = new NbtList();
        bodyParts.forEach((bodyParts, aBoolean) -> {
            NbtCompound bodyCompound = new NbtCompound();
            bodyCompound.putString("BodyPart", bodyParts.toString());
            bodyCompound.putBoolean("Has", aBoolean);
            bodyList.add(bodyCompound);

        });
        return bodyList;
    }
}
