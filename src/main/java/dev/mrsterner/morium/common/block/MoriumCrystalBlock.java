package dev.mrsterner.morium.common.block;

import dev.mrsterner.morium.common.block.entity.MoriumBlockEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class MoriumCrystalBlock extends BlockWithEntity {
    public MoriumCrystalBlock(Settings settings) {
        super(settings.nonOpaque().luminance(state -> 10).emissiveLighting((state, world, pos) -> true));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MoriumBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
