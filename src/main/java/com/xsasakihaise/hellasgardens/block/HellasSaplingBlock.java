package com.xsasakihaise.hellasgardens.block;

import com.xsasakihaise.hellasgardens.world.HellasTree;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockState;

import java.util.function.Supplier;

public class HellasSaplingBlock extends SaplingBlock {
    public HellasSaplingBlock(Supplier<BlockState> logState, Supplier<BlockState> leavesState) {
        super(new HellasTree(logState, leavesState), Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP));
    }
}
