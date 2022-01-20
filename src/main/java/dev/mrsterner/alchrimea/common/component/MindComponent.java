package dev.mrsterner.alchrimea.common.component;

import dev.mrsterner.alchrimea.common.alchemy.AlchemyKnowledge;
import dev.mrsterner.alchrimea.common.alchemy.AlchemyType;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public class MindComponent implements IMind, AutoSyncedComponent, PlayerComponent<MindComponent> {
    private PlayerEntity user;
    public MindComponent(PlayerEntity user) {
        this.user = user;
    }

    @Override
    public AlchemyType getAlchemyType() {
        return null;
    }

    @Override
    public void setAlchemyType(AlchemyType alchemyType) {

    }

    @Override
    public void addAlchemyKnowledge(AlchemyKnowledge alchemyKnowledge) {

    }

    @Override
    public void serverTick() {

    }

    @Override
    public void readFromNbt(NbtCompound tag) {

    }

    @Override
    public void writeToNbt(NbtCompound tag) {

    }
}
