package com.xsasakihaise.hellasgardens.block;

import net.minecraft.block.FlowerBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;

public class HellasFlowerBlock extends FlowerBlock {
    public HellasFlowerBlock() {
        super(Effects.SATURATION, 5, Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.CROP));
    }

    @Override
    public Effect getEffect() {
        return Effects.SATURATION;
    }
}
