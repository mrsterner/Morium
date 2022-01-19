package dev.mrsterner.morium.common.component;

import dev.mrsterner.morium.common.registry.MoriumComponents;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.sync.PlayerSyncPredicate;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;

import java.util.HashMap;
import java.util.UUID;

public class ApotheosisComponent implements AutoSyncedComponent, ServerTickingComponent {
    private final PlayerEntity player;
    private final int MAX_LEVEL = 20;
    private final int MIN_LEVEL = -20;
    private HashMap<UUID, Integer> villagerEntityList = new HashMap<>();
    public ApotheosisComponent(PlayerEntity player){
        this.player = player;
    }

    @Override
    public void serverTick() {

    }

    @Override
    public void readFromNbt(NbtCompound nbt) {
        NbtList list = nbt.getList("VillagerList", 10);
        for(NbtElement nbtElement : list){
            NbtCompound villagerNbt = (NbtCompound) nbtElement;
            villagerEntityList.put(villagerNbt.getUuid("Villager"), villagerNbt.getInt("Level"));
        }
    }

    @Override
    public void writeToNbt(NbtCompound nbt) {
        NbtList list = new NbtList();
        villagerEntityList.forEach(((uuid, integer) -> {
            NbtCompound villagerNbt = new NbtCompound();
            villagerNbt.putUuid("Villager", uuid);
            villagerNbt.putInt("Level", integer);
            list.add(villagerNbt);
        }));
        nbt.put("VillagerList", list);
    }

    public void addVillager(VillagerEntity villagerEntity){
        if(!getVillagerEntityList().containsKey(villagerEntity.getUuid())){
            getVillagerEntityList().put(villagerEntity.getUuid(), 0);
            MoriumComponents.APOTHEOSIS.sync(player, this, PlayerSyncPredicate.all());
        }
    }

    private void modifyVillager(VillagerEntity villagerEntity, int value){
        if(getVillagerEntityList().containsKey(villagerEntity.getUuid())){
            getVillagerEntityList().put(villagerEntity.getUuid(), value);
            MoriumComponents.APOTHEOSIS.sync(player, this, PlayerSyncPredicate.all());
        }
    }

    public void increaseVillager(VillagerEntity villagerEntity){
        if(getVillager(villagerEntity) < MAX_LEVEL){
            modifyVillager(villagerEntity, getVillager(villagerEntity) + 1);
        }

    }

    public void decreaseVillager(VillagerEntity villagerEntity){
        if(getVillager(villagerEntity) > MIN_LEVEL){
            modifyVillager(villagerEntity, getVillager(villagerEntity) - 1);
        }

    }

    public void removeVillager(VillagerEntity villagerEntity){
        getVillagerEntityList().remove(villagerEntity.getUuid());
        MoriumComponents.APOTHEOSIS.sync(player, this, PlayerSyncPredicate.all());
    }

    public int getVillager(VillagerEntity villagerEntity){
        return getVillagerEntityList().get(villagerEntity.getUuid());
    }


    public HashMap<UUID, Integer> getVillagerEntityList() {
        return villagerEntityList;
    }

    public void setVillagerEntityList(HashMap<UUID, Integer> villagerEntityList) {
        this.villagerEntityList = villagerEntityList;
    }
}
