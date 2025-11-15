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
import java.util.function.Supplier;

/**
 * Minimal custom tree generator used by every Hellas sapling. The class is a
 * lighter alternative to full {@link ConfiguredFeature} trees and simply builds
 * a straight trunk with a small leaf canopy using supplied block states.
 */
public class HellasTree extends Tree {
    private final Supplier<BlockState> logState;
    private final Supplier<BlockState> leavesState;

    public HellasTree(Supplier<BlockState> logState, Supplier<BlockState> leavesState) {
        this.logState = logState;
        this.leavesState = leavesState;
    }

    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random random, boolean bees) {
        // We override growTree directly, so returning null prevents vanilla from attempting
        // to spawn a configured feature.
        return null;
    }

    @Override
    /**
     * Generates a short tree if the surrounding area is free. The implementation
     * mirrors the vanilla sapling growth entry point but swaps in the custom log
     * and leaves states.
     */
    public boolean growTree(ServerWorld world, ChunkGenerator generator, BlockPos pos, BlockState state, Random random) {
        if (!ForgeEventFactory.saplingGrowTree(world, random, pos)) {
            return false;
        }
        int height = 3 + random.nextInt(3);
        for (int y = 0; y < height; y++) {
            BlockPos logPos = pos.above(y);
            if (!world.isEmptyBlock(logPos)) {
                return false;
            }
        }
        world.setBlock(pos, Blocks.AIR.defaultBlockState(), 4);
        BlockState log = logState.get();
        if (log == null) {
            log = Blocks.OAK_LOG.defaultBlockState();
        }
        BlockState leaves = leavesState.get();
        if (leaves == null) {
            leaves = Blocks.OAK_LEAVES.defaultBlockState();
        }
        if (leaves.hasProperty(LeavesBlock.PERSISTENT)) {
            leaves = leaves.setValue(LeavesBlock.PERSISTENT, true);
        }
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
                    if (world.isEmptyBlock(leafPos)) {
                        world.setBlock(leafPos, leaves, 19);
                    }
                }
            }
        }
        return true;
    }
}
