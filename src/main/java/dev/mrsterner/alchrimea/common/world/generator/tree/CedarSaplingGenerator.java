package dev.mrsterner.alchrimea.common.world.generator.tree;

import dev.mrsterner.alchrimea.common.registry.AlchrimeaWorldGenerators;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class CedarSaplingGenerator extends SaplingGenerator {
    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean bees) {
        return AlchrimeaWorldGenerators.CEDAR_TREE;
    }
}
