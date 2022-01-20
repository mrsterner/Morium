package dev.mrsterner.alchrimea.common.registry;

import dev.mrsterner.alchrimea.Alchrimea;
import dev.mrsterner.alchrimea.common.block.cedar.AlchrimeaChestBlockEntity;
import dev.mrsterner.alchrimea.common.block.cedar.CedarChestBlockEntity;
import dev.mrsterner.alchrimea.common.block.entity.DwarfInAFlaskBlockEntity;
import dev.mrsterner.alchrimea.common.block.entity.EmeraldTabletBlockEntity;
import dev.mrsterner.alchrimea.common.block.entity.JarBlockEntity;
import dev.mrsterner.alchrimea.common.block.entity.MoriumCrystalBlockEntity;
import dev.mrsterner.alchrimea.common.entity.TheTruthEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class AlchrimeaEntityTypes {
    private static final Map<BlockEntityType<?>, Identifier> BLOCK_ENTITY_TYPES = new LinkedHashMap<>();
    private static final Map<EntityType<?>, Identifier> ENTITY_TYPES = new LinkedHashMap<>();

    public static final BlockEntityType<MoriumCrystalBlockEntity> MORIUM_CRYSTAL_BLOCK_ENTITY = register("alchrimea_crystal_block_entity", FabricBlockEntityTypeBuilder.create(MoriumCrystalBlockEntity::new,
        AlchrimeaObjects.MORIUM_CRYSTAL_AMBER,AlchrimeaObjects.MORIUM_CRYSTAL_CERISE, AlchrimeaObjects.MORIUM_CRYSTAL_CRIMSON, AlchrimeaObjects.MORIUM_CRYSTAL_OLYMPIC, AlchrimeaObjects.MORIUM_CRYSTAL_SHAMROCK, AlchrimeaObjects.MORIUM_CRYSTAL_CARNATION, AlchrimeaObjects.MORIUM_CRYSTAL_OCHRE
    ).build(null));
    public static final BlockEntityType<EmeraldTabletBlockEntity> EMERALD_TABLET_BLOCK_ENTITY = register("emerald_tablet_block_entity", FabricBlockEntityTypeBuilder.create(EmeraldTabletBlockEntity::new,
          AlchrimeaObjects.EMERALD_TABLET
    ).build(null));
    public static final BlockEntityType<JarBlockEntity> JAR_BLOCK_ENTITY = register("jar_block_entity", FabricBlockEntityTypeBuilder.create(JarBlockEntity::new,
        AlchrimeaObjects.JAR
    ).build(null));

    public static final BlockEntityType<CedarChestBlockEntity> CEDAR_CHEST_BLOCK_ENTITY = register("cedar_chest", FabricBlockEntityTypeBuilder.create(CedarChestBlockEntity::new, AlchrimeaObjects.CEDAR_CHEST, AlchrimeaObjects.TRAPPED_CEDAR_CHEST).build(null));
    public static final BlockEntityType<DwarfInAFlaskBlockEntity> DWARF_IN_A_FLASK_BLOCK_ENTITY = register("dwarf_in_a_flask_block_entity", FabricBlockEntityTypeBuilder.create(DwarfInAFlaskBlockEntity::new, AlchrimeaObjects.DWARF_IN_A_FLASK_BLOCK).build(null));
    public static final EntityType<TheTruthEntity> THE_TRUTH = register("the_truth", FabricEntityTypeBuilder.<TheTruthEntity>create(SpawnGroup.CREATURE, TheTruthEntity::new).dimensions(EntityDimensions.fixed(0.6F, 1.8F)).trackRangeBlocks(10).build());
    public static final BlockEntityType<AlchrimeaChestBlockEntity> KHEMEIA_CHEST = register("alchrimea_chest", FabricBlockEntityTypeBuilder.create(AlchrimeaChestBlockEntity::new, AlchrimeaObjects.CEDAR_CHEST, AlchrimeaObjects.TRAPPED_CEDAR_CHEST).build(null));


    public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> type) {
        BLOCK_ENTITY_TYPES.put(type, new Identifier(dev.mrsterner.alchrimea.Alchrimea.MODID, name));
        return type;
    }

    public static <T extends Entity> EntityType<T> register(String name, EntityType<T> type) {
        ENTITY_TYPES.put(type, new Identifier(Alchrimea.MODID, name));
        return type;
    }
    public static void registerBlockEntities(){
        BLOCK_ENTITY_TYPES.keySet().forEach(blockEntityType -> Registry.register(Registry.BLOCK_ENTITY_TYPE, BLOCK_ENTITY_TYPES.get(blockEntityType), blockEntityType));
        ENTITY_TYPES.keySet().forEach(entityType -> Registry.register(Registry.ENTITY_TYPE, ENTITY_TYPES.get(entityType), entityType));
    }
}
