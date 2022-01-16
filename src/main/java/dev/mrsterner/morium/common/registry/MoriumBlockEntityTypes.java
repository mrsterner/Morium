package dev.mrsterner.morium.common.registry;

import dev.mrsterner.morium.Morium;
import dev.mrsterner.morium.common.block.entity.MoriumBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class MoriumBlockEntityTypes {
    public static final Map<BlockEntityType<?>, Identifier> BLOCK_ENTITY_TYPES = new LinkedHashMap<>();

    public static final BlockEntityType<MoriumBlockEntity> MORIUM_CRYSTAL_BLOCK_ENTITY = register("morium_crystal_block_entity", FabricBlockEntityTypeBuilder.create(MoriumBlockEntity::new,
        MoriumObjects.MORIUM_CRYSTAL_1,
        MoriumObjects.MORIUM_CRYSTAL_2,
        MoriumObjects.MORIUM_CRYSTAL_3,
        MoriumObjects.MORIUM_CRYSTAL_4,
        MoriumObjects.MORIUM_CRYSTAL_5,
        MoriumObjects.MORIUM_CRYSTAL_6
    ).build(null));


    public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> type) {
        BLOCK_ENTITY_TYPES.put(type, new Identifier(Morium.MODID, name));
        return type;
    }

    public static void registerBlockEntities(){
        BLOCK_ENTITY_TYPES.keySet().forEach(blockEntityType -> Registry.register(Registry.BLOCK_ENTITY_TYPE, BLOCK_ENTITY_TYPES.get(blockEntityType), blockEntityType));
    }
}
