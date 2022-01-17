package dev.mrsterner.morium.common.registry;

import dev.mrsterner.morium.Morium;
import dev.mrsterner.morium.common.block.entity.MoriumCrystalBlockEntity;
import dev.mrsterner.morium.common.component.ApotheosisComponent;
import dev.mrsterner.morium.common.component.MoriumComponent;
import dev.onyxstudios.cca.api.v3.block.BlockComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.block.BlockComponentInitializer;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.minecraft.util.Identifier;

public class MoriumComponents implements EntityComponentInitializer, BlockComponentInitializer {
    public static final ComponentKey<ApotheosisComponent> APOTHEOSIS = ComponentRegistry.getOrCreate(new Identifier(Morium.MODID, "apotheosis"), ApotheosisComponent.class);
    public static final ComponentKey<MoriumComponent> MORIUM = ComponentRegistry.getOrCreate(new Identifier(Morium.MODID, "morium"), MoriumComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(APOTHEOSIS, ApotheosisComponent::new, RespawnCopyStrategy.ALWAYS_COPY);

    }

    @Override
    public void registerBlockComponentFactories(BlockComponentFactoryRegistry registry) {
        registry.registerFor(MoriumCrystalBlockEntity.class, MORIUM, MoriumComponent::new);
    }
}
