package dev.mrsterner.morium.common.component;

import dev.mrsterner.morium.common.block.entity.MoriumCrystalBlockEntity;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.minecraft.nbt.NbtCompound;

import java.util.UUID;

public class MoriumComponent implements AutoSyncedComponent, ServerTickingComponent {
    private UUID playerUuid = null;
    private final MoriumCrystalBlockEntity moriumCrystalBlockEntity;

    public MoriumComponent(MoriumCrystalBlockEntity moriumCrystalBlockEntity) {
        this.moriumCrystalBlockEntity = moriumCrystalBlockEntity;
    }


    @Override
    public void serverTick() {

    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        setPlayerUuid(tag.getString("PlayerUUID").isEmpty() ? null : UUID.fromString(tag.getString("PlayerUUID")));
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        if (this.playerUuid != null) {
            tag.putUuid("PlayerUUID", this.playerUuid);
        }
    }

    public UUID getPlayerUuid() {
        return playerUuid;
    }

    public void setPlayerUuid(UUID playerUuid) {
        this.playerUuid = playerUuid;
    }
}
