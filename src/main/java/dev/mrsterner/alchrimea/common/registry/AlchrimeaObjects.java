package dev.mrsterner.alchrimea.common.registry;

import dev.mrsterner.alchrimea.Alchrimea;
import dev.mrsterner.alchrimea.common.block.*;
import dev.mrsterner.alchrimea.common.block.cedar.*;
import dev.mrsterner.alchrimea.common.item.AlchrimeaDebugItem;
import dev.mrsterner.alchrimea.common.item.MagnumOpusItem;
import dev.mrsterner.alchrimea.common.world.generator.tree.CedarSaplingGenerator;
import dev.mrsterner.alchrimea.mixin.BlocksMixin;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.minecraft.item.TallBlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

import static net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings.copyOf;

public class AlchrimeaObjects {
    private static final Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();
    private static final Map<Item, Identifier> ITEMS = new LinkedHashMap<>();

    public static final Item PHILOSOPHERS_STONE = register("philosophers_stone", new Item(gen()));
    public static final Item ICHOR = register("ichor", new Item(gen()));
    public static final Item ICHORCLOTH = register("ichorcloth", new Item(gen()));
    public static final Item ICHORIUM = register("ichorium", new Item(gen()));
    public static final Item ICHORIUM_PICKAXE = register("ichorium_pickaxe", new Item(gen()));
    public static final Item ICHORIUM_AXE = register("ichorium_axe", new Item(gen()));
    public static final Item ICHORIUM_SWORD = register("ichorium_sword", new Item(gen()));
    public static final Item ICHORIUM_SHOVEL = register("ichorium_shovel", new Item(gen()));
    public static final Item ICHORIUM_HOE = register("ichorium_hoe", new Item(gen()));
    public static final Item SOUL_OF_GREED = register("soul_of_greed", new Item(gen()));
    public static final Item SOUL_OF_PRIDE = register("soul_of_pride", new Item(gen()));
    public static final Item SOUL_OF_WRATH = register("soul_of_wrath", new Item(gen()));
    public static final Item SOUL_OF_ENVY = register("soul_of_envy", new Item(gen()));
    public static final Item SOUL_OF_LUST = register("soul_of_lust", new Item(gen()));
    public static final Item SOUL_OF_GLUTTONY = register("soul_of_gluttony", new Item(gen()));
    public static final Item SOUL_OF_SLOTH = register("soul_of_sloth", new Item(gen()));
    public static final Item SOUL_OF_NETHER = register("soul_of_nether", new Item(gen()));
    public static final Item SOUL_OF_END = register("soul_of_end", new Item(gen()));
    public static final Item OUROBOURUS = register("ouroboros", new Item(gen()));


    public static final Block LIMESTONE = register("limestone", new Block(copyOf(Blocks.STONE)), true);
    public static final Block COBBLED_LIMESTONE = register("cobbled_limestone", new Block(copyOf(Blocks.COBBLESTONE)), true);
    public static final Block POLISHED_LIMESTONE = register("polished_limestone", new Block(copyOf(LIMESTONE)), true);
    public static final Block LIMESTONE_PILLAR = register("limestone_pillar", new LimestonePillar(copyOf(LIMESTONE)), true);

    public static final Block STRIPPED_CEDAR_LOG = register("stripped_cedar_log", new PillarBlock(copyOf(Blocks.OAK_LOG)), true);
    public static final Block STRIPPED_CEDAR_WOOD = register("stripped_cedar_wood", new PillarBlock(copyOf(STRIPPED_CEDAR_LOG)), true);
    public static final Block CEDAR_LOG = register("cedar_log", new CedarLogBlock(() -> STRIPPED_CEDAR_LOG, MapColor.BROWN, copyOf(STRIPPED_CEDAR_LOG)), true);
    public static final Block CEDAR_WOOD = register("cedar_wood", new CedarLogBlock(() -> STRIPPED_CEDAR_WOOD, MapColor.BROWN, copyOf(STRIPPED_CEDAR_LOG)), true);
    public static final Block CEDAR_PLANKS = register("cedar_planks", new Block(copyOf(Blocks.OAK_PLANKS)), true);
    public static final Block CEDAR_STAIRS = register("cedar_stairs", new CedarStairsBlock(CEDAR_PLANKS, copyOf(Blocks.OAK_STAIRS)), true);
    public static final Block CEDAR_SLAB = register("cedar_slab", new SlabBlock(copyOf(Blocks.OAK_SLAB)), true);
    public static final Block CEDAR_FENCE = register("cedar_fence", new FenceBlock(copyOf(Blocks.OAK_FENCE)), true);
    public static final Block CEDAR_FENCE_GATE = register("cedar_fence_gate", new FenceGateBlock(copyOf(Blocks.OAK_FENCE_GATE)), true);
    public static final Block CEDAR_LEAVES = register("cedar_leaves", BlocksMixin.callCreateLeavesBlock(BlockSoundGroup.GRASS), true);
    public static final Block CEDAR_SAPLING = register("cedar_sapling", new CedarSaplingBlock(new CedarSaplingGenerator(), copyOf(Blocks.OAK_SAPLING)), true);
    public static final Block POTTED_CEDAR_SAPLING = register("potted_cedar_sapling", new FlowerPotBlock(CEDAR_SAPLING, copyOf(Blocks.POTTED_OAK_SAPLING)), false);
    public static final Block CEDAR_PRESSURE_PLATE = register("cedar_pressure_plate", new CedarPressurePlateBlock(copyOf(Blocks.OAK_PRESSURE_PLATE)), true);
    public static final Block CEDAR_BUTTON = register("cedar_button", new CedarButtonBlock(copyOf(Blocks.OAK_BUTTON)), true);
    public static final Block CEDAR_TRAPDOOR = register("cedar_trapdoor", new CedarTrapdoorBlock(copyOf(Blocks.OAK_TRAPDOOR)), true);
    public static final Block CEDAR_DOOR = register("cedar_door", new CedarDoorBlock(copyOf(Blocks.OAK_DOOR)), false);
    public static final Item CEDAR_DOOR_ITEM = register("cedar_door", new TallBlockItem(CEDAR_DOOR, gen()));
    public static final Block CEDAR_CHEST = register("cedar_chest", new CedarChestBlock(copyOf(Blocks.CHEST), () -> AlchrimeaEntityTypes.CEDAR_CHEST_BLOCK_ENTITY, false), true);
    public static final Block TRAPPED_CEDAR_CHEST = register("trapped_cedar_chest", new CedarChestBlock(copyOf(Blocks.CHEST), () -> AlchrimeaEntityTypes.CEDAR_CHEST_BLOCK_ENTITY, true), true);
    private static final Identifier CEDAR_SIGN_TEXTURE = new Identifier(Alchrimea.MODID, "entity/sign/cedar");
    public static final CedarSignBlock CEDAR_SIGN = register("cedar_sign", new CedarSignBlock(CEDAR_SIGN_TEXTURE, copyOf(Blocks.OAK_SIGN)), false);
    public static final Block CEDAR_WALL_SIGN = register("cedar_wall_sign", new CedarWallSignBlock(CEDAR_SIGN_TEXTURE, copyOf(Blocks.OAK_WALL_SIGN)), false);
    public static final Item CEDAR_SIGN_ITEM = register("cedar_sign", new SignItem(gen().maxCount(16), CEDAR_SIGN, CEDAR_WALL_SIGN));

    public static final Block DWARF_IN_A_FLASK_BLOCK = registerBlockItem("dwarf_in_a_flask", new DwarfInAFlaskBlock(FabricBlockSettings.of(Material.GLASS).hardness(2.0F).breakInstantly()), true);

    public static final MagnumOpusItem MAGNUM_OPUS = register("magnum_opus", new MagnumOpusItem(gen().maxCount(1)));
    public static final Item MO_DEBUG_STICK = register("mo_debug_stick", new AlchrimeaDebugItem(gen()));

    public static final Block MORIUM_CRYSTAL_CARNATION = register("morium_crystal_carnation", new MoriumCrystalBlock(FabricBlockSettings.of(Material.STONE)), true);
    public static final Block MORIUM_CRYSTAL_CRIMSON = register("morium_crystal_crimson", new MoriumCrystalBlock(FabricBlockSettings.of(Material.STONE)), true);
    public static final Block MORIUM_CRYSTAL_SHAMROCK = register("morium_crystal_shamrock", new MoriumCrystalBlock(FabricBlockSettings.of(Material.STONE)), true);
    public static final Block MORIUM_CRYSTAL_OLYMPIC = register("morium_crystal_olympic", new MoriumCrystalBlock(FabricBlockSettings.of(Material.STONE)), true);
    public static final Block MORIUM_CRYSTAL_AMBER = register("morium_crystal_amber", new MoriumCrystalBlock(FabricBlockSettings.of(Material.STONE)), true);
    public static final Block MORIUM_CRYSTAL_CERISE = register("morium_crystal_cerise", new MoriumCrystalBlock(FabricBlockSettings.of(Material.STONE)), true);
    public static final Block MORIUM_CRYSTAL_OCHRE = register("morium_crystal_ochre", new MoriumCrystalBlock(FabricBlockSettings.of(Material.STONE)), true);

    public static final Block EMERALD_TABLET = register("emerald_tablet", new EmeraldTabletBlock(FabricBlockSettings.of(Material.STONE)), true);
    public static final Block JAR = registerBlockItem("jar", new JarBlock(FabricBlockSettings.of(Material.STONE)), true);




    public static FabricItemSettings gen() {
        return new FabricItemSettings().group(dev.mrsterner.alchrimea.Alchrimea.MORIUUM_GROUP);
    }

    public static <T extends Item> T register(String name, T item) {
        ITEMS.put(item, new Identifier(Alchrimea.MODID, name));
        return item;
    }

    public static <T extends Block> T registerBlockItem(String name, T block, boolean createItem) {
        BLOCKS.put(block, new Identifier(Alchrimea.MODID, name));
        if (createItem) {
            ITEMS.put(new AlchrimeaBlockItem(block, gen()), BLOCKS.get(block));
        }
        return block;
    }

    public static <T extends Block> T register(String name, T block, boolean createItem) {
        BLOCKS.put(block, new Identifier(dev.mrsterner.alchrimea.Alchrimea.MODID, name));
        if (createItem) {
            ITEMS.put(new BlockItem(block, gen()), BLOCKS.get(block));
        }
        return block;
    }


        public static void register() {
        BLOCKS.keySet().forEach(block -> Registry.register(Registry.BLOCK, BLOCKS.get(block), block));
        ITEMS.keySet().forEach(item -> Registry.register(Registry.ITEM, ITEMS.get(item), item));
    }
}
