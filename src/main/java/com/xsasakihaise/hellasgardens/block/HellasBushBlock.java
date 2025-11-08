package com.xsasakihaise.hellasgardens.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.Constants;

import java.util.Random;
import java.util.function.Supplier;

public class HellasBushBlock extends BushBlock implements net.minecraft.block.IGrowable {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private final Supplier<Item> produceSupplier;

    public HellasBushBlock(Supplier<Item> produceSupplier) {
        super(Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP));
        this.produceSupplier = produceSupplier;
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 3;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.getValue(AGE) < 3 && world.getRawBrightness(pos.above(), 0) >= 9 && random.nextInt(5) == 0) {
            if (ForgeHooks.onCropsGrowPre(world, pos, state, true)) {
                world.setBlock(pos, state.setValue(AGE, state.getValue(AGE) + 1), Constants.BlockFlags.BLOCK_UPDATE);
                ForgeHooks.onCropsGrowPost(world, pos, state);
            }
        }
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        int age = state.getValue(AGE);
        if (age == 3) {
            if (!world.isClientSide) {
                int count = 1 + world.random.nextInt(3);
                popResource(world, pos, new ItemStack(produceSupplier.get(), count));
            }
            world.setBlock(pos, state.setValue(AGE, 1), Constants.BlockFlags.BLOCK_UPDATE);
            return ActionResultType.sidedSuccess(world.isClientSide);
        }
        return super.use(state, world, pos, player, hand, hit);
    }

    @Override
    public boolean isValidBonemealTarget(IBlockReader world, BlockPos pos, BlockState state, boolean isClient) {
        return state.getValue(AGE) < 3;
    }

    @Override
    public boolean isBonemealSuccess(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int age = state.getValue(AGE);
        if (age < 3) {
            world.setBlock(pos, state.setValue(AGE, age + 1), Constants.BlockFlags.BLOCK_UPDATE);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
