package com.xsasakihaise.hellasgardens.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.Constants;

import java.util.Random;

public class HellasHerbBlock extends BushBlock implements net.minecraft.block.IGrowable {
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 2);

    public HellasHerbBlock() {
        super(Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP));
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 2;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.getValue(AGE) < 2 && world.getRawBrightness(pos.above(), 0) >= 9 && random.nextInt(4) == 0) {
            if (ForgeHooks.onCropsGrowPre(world, pos, state, true)) {
                world.setBlock(pos, state.setValue(AGE, state.getValue(AGE) + 1), Constants.BlockFlags.BLOCK_UPDATE);
                ForgeHooks.onCropsGrowPost(world, pos, state);
            }
        }
    }

    @Override
    public boolean canGrow(IBlockReader world, BlockPos pos, BlockState state, boolean isClient) {
        return state.getValue(AGE) < 2;
    }

    @Override
    public boolean canUseBonemeal(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int age = state.getValue(AGE);
        if (age < 2) {
            world.setBlock(pos, state.setValue(AGE, age + 1), Constants.BlockFlags.BLOCK_UPDATE);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
