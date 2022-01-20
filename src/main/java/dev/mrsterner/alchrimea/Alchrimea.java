package dev.mrsterner.alchrimea;

import dev.mrsterner.alchrimea.common.events.AlchrimeaEvents;
import dev.mrsterner.alchrimea.common.registry.AlchrimeaEntityTypes;
import dev.mrsterner.alchrimea.common.registry.AlchrimeaObjects;
import dev.mrsterner.alchrimea.common.registry.AlchrimeaWorldGenerators;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;

public class Alchrimea implements ModInitializer {

    public static final String MODID = "alchrimea";
    public static final ItemGroup MORIUUM_GROUP = FabricItemGroupBuilder.build(new Identifier(MODID, MODID), () -> new ItemStack(Items.ANDESITE));
    public static final Logger LOGGER = LogManager.getLogger("alchrimea");
    AlchrimeaEvents alchrimeaEvents = new AlchrimeaEvents();
    public static AlchimiaConfig config;

    @Override
    public void onInitialize() {
        AutoConfig.register(AlchimiaConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(AlchimiaConfig.class).getConfig();
        AlchrimeaObjects.register();
        AlchrimeaEntityTypes.registerBlockEntities();
        alchrimeaEvents.init();
        AlchrimeaWorldGenerators.init();

        LOGGER.info("Example is getting ready...");
    }
}
