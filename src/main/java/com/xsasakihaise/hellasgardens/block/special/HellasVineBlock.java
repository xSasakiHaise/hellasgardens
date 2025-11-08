package com.xsasakihaise.hellasgardens.block.special;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.VineBlock;
import net.minecraft.block.material.Material;

public class HellasVineBlock extends VineBlock {
    public HellasVineBlock() {
        super(AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP));
    }
}
