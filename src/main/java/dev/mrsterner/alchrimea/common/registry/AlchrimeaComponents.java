package dev.mrsterner.alchrimea.common.registry;

import dev.mrsterner.alchrimea.Alchrimea;
import dev.mrsterner.alchrimea.common.block.entity.MoriumCrystalBlockEntity;
import dev.mrsterner.alchrimea.common.component.*;
import dev.onyxstudios.cca.api.v3.block.BlockComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.block.BlockComponentInitializer;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class AlchrimeaComponents implements EntityComponentInitializer, BlockComponentInitializer {
    public static final ComponentKey<ApotheosisComponent> APOTHEOSIS = ComponentRegistry.getOrCreate(new Identifier(Alchrimea.MODID, "apotheosis"), ApotheosisComponent.class);
    public static final ComponentKey<MoriumComponent> MORIUM = ComponentRegistry.getOrCreate(new Identifier(Alchrimea.MODID, "alchrimea"), MoriumComponent.class);
    public static final ComponentKey<BodyComponent> BODY_COMPONENT = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(Alchrimea.MODID, "body"), BodyComponent.class);
    public static final ComponentKey<IMind> MIND_COMPONENT = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(Alchrimea.MODID, "mind"), IMind.class);


    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(APOTHEOSIS, ApotheosisComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        registry.beginRegistration(PlayerEntity.class, BODY_COMPONENT).respawnStrategy(RespawnCopyStrategy.ALWAYS_COPY).end(BodyComponent::new);
        registry.beginRegistration(PlayerEntity.class, MIND_COMPONENT).impl(MindComponent.class).end(MindComponent::new);

    }

    @Override
    public void registerBlockComponentFactories(BlockComponentFactoryRegistry registry) {
        registry.registerFor(MoriumCrystalBlockEntity.class, MORIUM, MoriumComponent::new);
    }
}
