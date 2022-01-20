package dev.mrsterner.alchrimea.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

import java.util.Locale;

public class LimestonePillar extends Block implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final EnumProperty<PillarType> PILLAR_TYPE = EnumProperty.of("pillartype", PillarType.class);
    private final VoxelShape SOLO = VoxelShapes.union(createCuboidShape(0,0,0,16,3,16), createCuboidShape(2,3,2,14,13,14), createCuboidShape(0,13,0,16,16,16));
    private final VoxelShape TOP = VoxelShapes.union(createCuboidShape(2,0,2,14,13,14), createCuboidShape(0,13,0,16,16,16));
    private final VoxelShape MIDDLE = VoxelShapes.union(createCuboidShape(2,0,2,14,16,14));
    private final VoxelShape BOTTOM = VoxelShapes.union(createCuboidShape(0,0,0,16,3,16), createCuboidShape(2,3,2,14,16,14));

    public LimestonePillar(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(WATERLOGGED, false).with(PILLAR_TYPE, PillarType.SOLO));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(Properties.WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER).with(PILLAR_TYPE, PillarType.SOLO);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED).add(PILLAR_TYPE);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(Properties.WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        boolean hasUp   = world.getBlockState(pos.up()).getBlock()   instanceof LimestonePillar;
        boolean hasDown = world.getBlockState(pos.down()).getBlock() instanceof LimestonePillar;
        if (state.get(Properties.WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        if (hasUp) {
            if (hasDown) {
                return this.getDefaultState().with(PILLAR_TYPE, PillarType.MIDDLE);
            }
            return this.getDefaultState().with(PILLAR_TYPE, PillarType.BOTTOM);
        } else if (hasDown) {
            return this.getDefaultState().with(PILLAR_TYPE, PillarType.TOP);
        }
        return this.getDefaultState().with(PILLAR_TYPE, PillarType.SOLO);

    }


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(PILLAR_TYPE)){
            case TOP -> this.TOP;
            case SOLO -> this.SOLO;
            case BOTTOM -> this.BOTTOM;
            case MIDDLE -> this.MIDDLE;
        };
    }

    public enum PillarType implements StringIdentifiable {
        SOLO,
        TOP,
        MIDDLE,
        BOTTOM;

        @Override
        public String asString() {
            return name().toLowerCase(Locale.ROOT);
        }
    }
}
