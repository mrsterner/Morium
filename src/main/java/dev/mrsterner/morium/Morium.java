package dev.mrsterner.morium;

import dev.mrsterner.morium.common.registry.MoriumBlockEntityTypes;
import dev.mrsterner.morium.common.registry.MoriumObjects;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;

public class Morium implements ModInitializer {

    public static final String MODID = "morium";
    public static final ItemGroup MORIUUM_GROUP = FabricItemGroupBuilder.build(new Identifier(MODID, MODID), () -> new ItemStack(Items.ANDESITE));
    public static final Logger LOGGER = LogManager.getLogger("morium");

    @Override
    public void onInitialize() {
        MoriumObjects.register();
        MoriumBlockEntityTypes.registerBlockEntities();

        LOGGER.info("Example is getting ready...");
    }
}
