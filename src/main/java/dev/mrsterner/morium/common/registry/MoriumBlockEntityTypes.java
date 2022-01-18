package dev.mrsterner.morium.common.registry;

import dev.mrsterner.morium.Morium;
import dev.mrsterner.morium.common.block.entity.EmeraldTabletBlockEntity;
import dev.mrsterner.morium.common.block.entity.MoriumCrystalBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class MoriumBlockEntityTypes {
    public static final Map<BlockEntityType<?>, Identifier> BLOCK_ENTITY_TYPES = new LinkedHashMap<>();

    public static final BlockEntityType<MoriumCrystalBlockEntity> MORIUM_CRYSTAL_BLOCK_ENTITY = register("morium_crystal_block_entity", FabricBlockEntityTypeBuilder.create(MoriumCrystalBlockEntity::new,
        MoriumObjects.MORIUM_CRYSTAL_AMBER,MoriumObjects.MORIUM_CRYSTAL_CERISE, MoriumObjects.MORIUM_CRYSTAL_CRIMSON, MoriumObjects.MORIUM_CRYSTAL_OLYMPIC, MoriumObjects.MORIUM_CRYSTAL_SHAMROCK, MoriumObjects.MORIUM_CRYSTAL_CARNATION, MoriumObjects.MORIUM_CRYSTAL_OCHRE
    ).build(null));
    public static final BlockEntityType<EmeraldTabletBlockEntity> EMERALD_TABLET_BLOCK_ENTITY = register("emerald_tablet_block_entity", FabricBlockEntityTypeBuilder.create(EmeraldTabletBlockEntity::new,
          MoriumObjects.EMERALD_TABLET
    ).build(null));


    public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> type) {
        BLOCK_ENTITY_TYPES.put(type, new Identifier(Morium.MODID, name));
        return type;
    }

    public static void registerBlockEntities(){
        BLOCK_ENTITY_TYPES.keySet().forEach(blockEntityType -> Registry.register(Registry.BLOCK_ENTITY_TYPE, BLOCK_ENTITY_TYPES.get(blockEntityType), blockEntityType));
    }
}
