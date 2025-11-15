package com.xsasakihaise.hellasgardens.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import java.util.function.Supplier;

/**
 * Standard crop implementation that delegates its seed item to a supplier so
 * registration order does not matter.
 */
public class HellasCropBlock extends CropsBlock {
    private final Supplier<Item> seedSupplier;

    public HellasCropBlock(Supplier<Item> seedSupplier) {
        super(AbstractBlock.Properties.of(net.minecraft.block.material.Material.PLANT)
            .noCollission().randomTicks().instabreak().sound(net.minecraft.block.SoundType.CROP));
        this.seedSupplier = seedSupplier;
    }

    @Override
    protected Item getBaseSeedId() {
        return seedSupplier.get();
    }

    @Override
    public ItemStack getCloneItemStack(IBlockReader world, BlockPos pos, BlockState state) {
        return new ItemStack(getBaseSeedId());
    }
}
