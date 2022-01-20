package dev.mrsterner.alchrimea.common.block.cedar;

import dev.mrsterner.alchrimea.common.registry.AlchrimeaEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.util.math.BlockPos;

public class CedarChestBlockEntity extends ChestBlockEntity {
    public final boolean trapped;

    public CedarChestBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState, boolean trapped) {
        super(blockEntityType, blockPos, blockState);
        this.trapped = trapped;
    }

    public CedarChestBlockEntity(BlockPos pos, BlockState state) {
        this(AlchrimeaEntityTypes.CEDAR_CHEST_BLOCK_ENTITY ,pos, state, false);
    }
}
