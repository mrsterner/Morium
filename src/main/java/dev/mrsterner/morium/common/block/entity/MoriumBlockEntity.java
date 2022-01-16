package dev.mrsterner.morium.common.block.entity;

import dev.mrsterner.morium.common.registry.MoriumBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class MoriumBlockEntity extends BlockEntity {
    public MoriumBlockEntity(BlockPos pos, BlockState state) {
        super(MoriumBlockEntityTypes.MORIUM_CRYSTAL_BLOCK_ENTITY, pos, state);
    }
}
