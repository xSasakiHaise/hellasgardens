package com.xsasakihaise.hellasgardens.block;

import com.xsasakihaise.hellasgardens.world.HellasTree;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class HellasSaplingBlock extends SaplingBlock {
    public HellasSaplingBlock() {
        super(new HellasTree(), Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP));
    }
}
