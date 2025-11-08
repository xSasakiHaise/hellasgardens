package com.xsasakihaise.hellasgardens.world;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.Random;

public class HellasTree extends Tree {
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random random, boolean bees) {
        return null;
    }

    @Override
    public boolean growTree(ServerWorld world, ChunkGenerator generator, BlockPos pos, BlockState state, Random random) {
        if (!ForgeEventFactory.saplingGrowTree(world, random, pos)) {
            return false;
        }
        int height = 3 + random.nextInt(3);
        for (int y = 0; y < height; y++) {
            BlockPos logPos = pos.above(y);
            if (!world.getBlockState(logPos).canBeReplaced()) {
                return false;
            }
        }
        world.setBlock(pos, Blocks.AIR.defaultBlockState(), 4);
        BlockState log = Blocks.OAK_LOG.defaultBlockState();
        BlockState leaves = Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true);
        for (int y = 0; y < height; y++) {
            BlockPos logPos = pos.above(y);
            world.setBlock(logPos, log, 19);
        }
        BlockPos top = pos.above(height - 1);
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                for (int dy = 0; dy <= 1; dy++) {
                    if (dx == 0 && dz == 0 && dy == 0) {
                        continue;
                    }
                    BlockPos leafPos = top.offset(dx, dy, dz);
                    if (world.getBlockState(leafPos).canBeReplaced()) {
                        world.setBlock(leafPos, leaves, 19);
                    }
                }
            }
        }
        return true;
    }
}
