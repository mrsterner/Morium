package dev.mrsterner.alchrimea.common.block.cedar;

import dev.mrsterner.alchrimea.common.registry.AlchrimeaEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.util.math.BlockPos;

import java.util.function.Supplier;

public class CedarChestBlock extends AlchrimeaChestBlock {
    public CedarChestBlock(Settings settings, Supplier<BlockEntityType<? extends ChestBlockEntity>> supplier, boolean trapped) {
        super(settings, supplier, trapped);
    }
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CedarChestBlockEntity(AlchrimeaEntityTypes.CEDAR_CHEST_BLOCK_ENTITY, pos, state, trapped);
    }
}
