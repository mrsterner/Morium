package dev.mrsterner.morium.common.registry;

import dev.mrsterner.morium.Morium;
import dev.mrsterner.morium.common.block.EmeraldTabletBlock;
import dev.mrsterner.morium.common.block.MoriumCrystalBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class MoriumObjects {
    private static final Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();
    private static final Map<Item, Identifier> ITEMS = new LinkedHashMap<>();

    public static final Block MORIUM_CRYSTAL_CARNATION = register("morium_crystal_carnation", new MoriumCrystalBlock(FabricBlockSettings.of(Material.STONE)), true);
    public static final Block MORIUM_CRYSTAL_CRIMSON = register("morium_crystal_crimson", new MoriumCrystalBlock(FabricBlockSettings.of(Material.STONE)), true);
    public static final Block MORIUM_CRYSTAL_SHAMROCK = register("morium_crystal_shamrock", new MoriumCrystalBlock(FabricBlockSettings.of(Material.STONE)), true);
    public static final Block MORIUM_CRYSTAL_OLYMPIC = register("morium_crystal_olympic", new MoriumCrystalBlock(FabricBlockSettings.of(Material.STONE)), true);
    public static final Block MORIUM_CRYSTAL_AMBER = register("morium_crystal_amber", new MoriumCrystalBlock(FabricBlockSettings.of(Material.STONE)), true);
    public static final Block MORIUM_CRYSTAL_CERISE = register("morium_crystal_cerise", new MoriumCrystalBlock(FabricBlockSettings.of(Material.STONE)), true);
    public static final Block MORIUM_CRYSTAL_OCHRE = register("morium_crystal_ochre", new MoriumCrystalBlock(FabricBlockSettings.of(Material.STONE)), true);

    public static final Block EMERALD_TABLET = register("emerald_tablet", new EmeraldTabletBlock(FabricBlockSettings.of(Material.STONE)), true);




    public static FabricItemSettings gen() {
        return new FabricItemSettings().group(Morium.MORIUUM_GROUP);
    }


    public static <T extends Block> T register(String name, T block, boolean createItem) {
        BLOCKS.put(block, new Identifier(Morium.MODID, name));
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
