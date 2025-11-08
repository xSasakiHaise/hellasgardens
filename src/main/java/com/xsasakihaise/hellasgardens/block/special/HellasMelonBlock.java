package com.xsasakihaise.hellasgardens.block.special;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.MelonBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class HellasMelonBlock extends MelonBlock {
    public HellasMelonBlock() {
        super(AbstractBlock.Properties.of(Material.VEGETABLE).strength(1.0F).sound(SoundType.WOOD));
    }
}
