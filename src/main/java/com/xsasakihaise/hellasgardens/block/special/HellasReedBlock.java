package com.xsasakihaise.hellasgardens.block.special;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.Constants;

import java.util.Random;

public class HellasReedBlock extends SugarCaneBlock {
    public HellasReedBlock() {
        super(AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP));
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isAreaLoaded(pos, 1)) {
            return;
        }
        if (world.isEmptyBlock(pos.above())) {
            int height = 1;
            while (world.getBlockState(pos.below(height)).is(this)) {
                height++;
            }
            if (height < 2) {
                int age = state.getValue(AGE);
                if (age >= 3) {
                    if (ForgeHooks.onCropsGrowPre(world, pos.above(), this.defaultBlockState(), true)) {
                        world.setBlock(pos.above(), this.defaultBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
                        world.setBlock(pos, state.setValue(AGE, 0), Constants.BlockFlags.BLOCK_UPDATE);
                        ForgeHooks.onCropsGrowPost(world, pos.above(), this.defaultBlockState());
                    }
                } else {
                    if (ForgeHooks.onCropsGrowPre(world, pos, state, true)) {
                        world.setBlock(pos, state.setValue(AGE, age + 1), Constants.BlockFlags.BLOCK_UPDATE);
                        ForgeHooks.onCropsGrowPost(world, pos, state);
                    }
                }
            }
        }
    }
}
