package dev.mrsterner.alchrimea.common.block.cedar;

import dev.mrsterner.alchrimea.common.registry.AlchrimeaEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.stat.Stat;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BlockView;

import java.util.function.Supplier;

public class AlchrimeaChestBlock extends ChestBlock {
    protected final boolean trapped;

    public AlchrimeaChestBlock(Settings settings, Supplier<BlockEntityType<? extends ChestBlockEntity>> supplier, boolean trapped) {
        super(settings, supplier);
        this.trapped = trapped;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AlchrimeaChestBlockEntity(AlchrimeaEntityTypes.KHEMEIA_CHEST, pos, state, getType(this), trapped);
    }

    @Override
    protected Stat<Identifier> getOpenStat() {
        return trapped ? Stats.CUSTOM.getOrCreateStat(Stats.TRIGGER_TRAPPED_CHEST) : super.getOpenStat();
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return trapped || super.emitsRedstonePower(state);
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return trapped ? MathHelper.clamp(ChestBlockEntity.getPlayersLookingInChestCount(world, pos), 0, 15) : super.getWeakRedstonePower(state, world, pos, direction);
    }

    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return trapped ? (direction == Direction.UP ? state.getWeakRedstonePower(world, pos, direction) : 0) : super.getStrongRedstonePower(state, world, pos, direction);
    }

    public static AlchrimeaChestBlockEntity.Type getType(Block block) {
        return AlchrimeaChestBlockEntity.Type.CEDAR;
    }
}
