package dev.mrsterner.alchrimea.common.registry;

import dev.mrsterner.alchrimea.Alchrimea;
import dev.mrsterner.alchrimea.mixin.SimpleBlockStateProviderMixin;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.RarityFilterPlacementModifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.FeatureSize;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.SpruceFoliagePlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

public class AlchrimeaWorldGenerators {
    private static final FeatureSize EMPTY_SIZE = new TwoLayersFeatureSize(0, 0, 0);

    public static final ConfiguredFeature<TreeFeatureConfig, ?> CEDAR_TREE = Feature.TREE.configure(new TreeFeatureConfig.Builder(SimpleBlockStateProviderMixin.callInit(AlchrimeaObjects.CEDAR_LOG.getDefaultState()), new StraightTrunkPlacer(12, 0, 1),
    SimpleBlockStateProviderMixin.callInit(AlchrimeaObjects.CEDAR_LEAVES.getDefaultState()), new SpruceFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), ConstantIntProvider.create(1)), EMPTY_SIZE).ignoreVines().build());

    public static final PlacedFeature CEDAR_TREE_WITH_CHANCE = CEDAR_TREE.withPlacement(VegetationPlacedFeatures.modifiersWithWouldSurvive(RarityFilterPlacementModifier.of(10), AlchrimeaObjects.CEDAR_SAPLING));


    public static void init() {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Alchrimea.MODID, "cedar_tree"), CEDAR_TREE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Alchrimea.MODID, "cedar_tree"), CEDAR_TREE_WITH_CHANCE);
        BiomeModification worldGen = BiomeModifications.create(new Identifier(Alchrimea.MODID, "world_features"));
        worldGen.add(ModificationPhase.ADDITIONS, BiomeSelectors.categories(Biome.Category.FOREST), context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, CEDAR_TREE_WITH_CHANCE));

    }
}
