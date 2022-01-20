package dev.mrsterner.alchrimea.common.block;

import dev.mrsterner.alchrimea.common.block.entity.JarBlockEntity;
import dev.mrsterner.alchrimea.common.registry.AlchrimeaObjects;
import dev.mrsterner.alchrimea.common.registry.AlchrimeaTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.nbt.NbtCompound;
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

import java.util.List;

import static net.minecraft.block.ShulkerBoxBlock.CONTENTS;

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
            if(world.getBlockEntity(pos) instanceof JarBlockEntity jarBlockEntity && Hand.MAIN_HAND.equals(hand)){
                ItemStack itemStack = player.getStackInHand(hand);
                if(player.getStackInHand(hand).getItem().equals(Items.AIR) || (!jarBlockEntity.getStack(0).getItem().equals(itemStack.getItem()) && !player.getStackInHand(hand).getItem().equals(Items.AIR))){
                    if(!jarBlockEntity.getStack(0).isEmpty()){
                        if(player.isSneaking()){
                            ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), jarBlockEntity.getStack(0));
                            jarBlockEntity.setStack(0, new ItemStack(Items.AIR));
                        }else{
                            if(player.getStackInHand(hand).getItem().equals(Items.AIR)){
                                player.setStackInHand(hand, jarBlockEntity.getStack(0).split(1));
                            }else{
                                ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), jarBlockEntity.getStack(0).split(1));
                            }
                            jarBlockEntity.getStack(0).decrement(1);
                        }
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
                            System.out.println(jarBlockEntity.getStack(0).getCount());
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
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof JarBlockEntity jarBlockEntity) {
            if (!world.isClient && player.isCreative() && !jarBlockEntity.isEmpty()) {
                ItemStack itemStack = new ItemStack(AlchrimeaObjects.JAR);
                blockEntity.setStackNbt(itemStack);
                ItemEntity itemEntity = new ItemEntity(world, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, itemStack);
                itemEntity.setToDefaultPickupDelay();
                world.spawnEntity(itemEntity);
            }
        }
        super.onBreak(world, pos, state, player);

        /*
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if(blockEntity instanceof JarBlockEntity jarBlockEntity){
            jarBlockEntity.setStackNbt(jarBlockEntity.getStack(0));
            ItemEntity itemEntity = new ItemEntity(world, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, new ItemStack(AlchrimeaObjects.JAR));
            itemEntity.setToDefaultPickupDelay();
            world.spawnEntity(itemEntity);
        }

         */
        /*
        if(world.getBlockEntity(pos) instanceof JarBlockEntity jarBlockEntity){
            ItemScatterer.spawn(world, pos, jarBlockEntity.getInventory());
        }

         */
        super.onBreak(world, pos, state, player);
    }



    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, net.minecraft.loot.context.LootContext.Builder builder) {
        BlockEntity blockEntity = (BlockEntity)builder.getNullable(LootContextParameters.BLOCK_ENTITY);
        if (blockEntity instanceof JarBlockEntity jarBlockEntity) {
            builder = builder.putDrop(CONTENTS, (context, consumer) -> {
                for(int i = 0; i < jarBlockEntity.size(); ++i) {
                    consumer.accept(jarBlockEntity.getStack(i));
                }

            });
        }

        return super.getDroppedStacks(state, builder);
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
