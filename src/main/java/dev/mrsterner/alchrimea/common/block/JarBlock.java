package dev.mrsterner.alchrimea.common.block;

import dev.mrsterner.alchrimea.common.block.entity.JarBlockEntity;
import dev.mrsterner.alchrimea.common.registry.AlchrimeaTags;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class JarBlock extends BlockWithEntity {
    public JarBlock(Settings settings) {
        super(settings.nonOpaque().breakInstantly());
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new JarBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            if(world.getBlockEntity(pos) instanceof JarBlockEntity jarBlockEntity){
                ItemStack itemStack = player.getStackInHand(hand);
                if((jarBlockEntity.getStack(0).isEmpty() || (!jarBlockEntity.getStack(0).getItem().equals(itemStack.getItem()) && !player.getStackInHand(hand).equals(Items.AIR)))){
                    if(!jarBlockEntity.getStack(0).isEmpty()){
                        ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), jarBlockEntity.getStack(0));
                        jarBlockEntity.setStack(0, new ItemStack(Items.AIR));
                    }else if(!jarBlockEntity.getStack(1).isEmpty()){
                        ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), jarBlockEntity.getStack(1));
                        jarBlockEntity.setStack(1, new ItemStack(Items.AIR));
                    }
                }
                if(AlchrimeaTags.JAR.contains(player.getStackInHand(hand).getItem()) && (jarBlockEntity.getStack(0).isEmpty() || jarBlockEntity.getStack(0).getItem().equals(player.getStackInHand(hand).getItem()))){
                    if(player.isSneaking()){
                        while(itemStack.getCount() > 0 && jarBlockEntity.getStack(0).getCount() < 64){
                            if(jarBlockEntity.getStack(0).isOf(Items.AIR)){
                                jarBlockEntity.setStack(0, itemStack.split(1));
                            }else{
                                jarBlockEntity.getStack(0).increment(1);
                                itemStack.decrement(1);
                            }
                        }
                    }else{
                        if(jarBlockEntity.getStack(0).isOf(Items.AIR)){
                            jarBlockEntity.setStack(0, itemStack.split(1));
                        }else{
                            jarBlockEntity.getStack(0).increment(1);
                            itemStack.decrement(1);
                        }
                    }
                }
                jarBlockEntity.sync();
            }
        }

        return ActionResult.success(world.isClient);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if(world.getBlockEntity(pos) instanceof JarBlockEntity jarBlockEntity){
            ItemScatterer.spawn(world, pos, jarBlockEntity.getInventory());
        }
        super.onBreak(world, pos, state, player);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.union(
            createCuboidShape(4, 0, 4, 12, 12, 12),
            createCuboidShape(5, 11, 5, 11, 13, 11),
            createCuboidShape(4.5, 12, 4.5, 11.5, 14, 11.5),
            createCuboidShape(5.5, 14, 5.5, 10.5, 16, 10.5));
    }
}
