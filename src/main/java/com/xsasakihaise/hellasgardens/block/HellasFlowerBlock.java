package com.xsasakihaise.hellasgardens.block;

import net.minecraft.block.FlowerBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.potion.Effects;

/**
 * Decorative flower that mirrors vanilla flowers but applies a consistent set
 * of lightweight plant properties.
 */
public class HellasFlowerBlock extends FlowerBlock {
    public HellasFlowerBlock() {
        super(Effects.SATURATION, 5, Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.CROP));
    }

    // FlowerBlock already stores the effect supplied in the constructor, so no extra overrides are required.
}
