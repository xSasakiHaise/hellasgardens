package com.xsasakihaise.hellasgardens.registry;

import com.xsasakihaise.hellasgardens.HellasGardens;
import com.xsasakihaise.hellasgardens.block.HellasBushBlock;
import com.xsasakihaise.hellasgardens.block.HellasCropBlock;
import com.xsasakihaise.hellasgardens.block.HellasFlowerBlock;
import com.xsasakihaise.hellasgardens.block.HellasHerbBlock;
import com.xsasakihaise.hellasgardens.block.HellasSaplingBlock;
import com.xsasakihaise.hellasgardens.block.special.HellasReedBlock;
import com.xsasakihaise.hellasgardens.block.special.HellasVineBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, HellasGardens.MOD_ID);
    private static final List<RegistryObject<? extends Block>> CUTOUT_BLOCKS = new ArrayList<>();

    // Bushes
    public static final RegistryObject<Block> ASHROSE_SHRUB_BUSH = registerBush("ashrose_shrub");
    public static final RegistryObject<Block> BLOODCURRANT_BUSH = registerBush("bloodcurrant_bush");
    public static final RegistryObject<Block> IRONLEAF_BRAMBLE = registerBush("ironleaf_bramble");
    public static final RegistryObject<Block> MOONPETAL_BRIAR = registerBush("moonpetal_briar");
    public static final RegistryObject<Block> POMEGRANATE_BUSH = registerBush("pomegranate_bush");
    public static final RegistryObject<Block> SHADEBERRY_BUSH = registerBush("shadeberry_bush");
    public static final RegistryObject<Block> SOULTHORN_BUSH = registerBush("soulthorn_bush");

    // Crops
    public static final RegistryObject<Block> ASHMELON_CROP = registerCrop("ashmelon");
    public static final RegistryObject<Block> DREAMGOURD_CROP = registerCrop("dreamgourd");
    public static final RegistryObject<Block> ELYSIAN_WHEAT_CROP = registerCrop("elysian_wheat");
    public static final RegistryObject<Block> OBSIDIAN_CORN_CROP = registerCrop("obsidian_corn");
    public static final RegistryObject<Block> SOULBEAN_CROP = registerCrop("soulbean");
    public static final RegistryObject<Block> STYXROOT_CROP = registerCrop("styxroot");
    public static final RegistryObject<Block> WHISPERGRASS_CROP = registerCrop("whispergrass");

    // Flowers
    public static final RegistryObject<Block> ASPHODEL_FLOWER = registerFlower("asphodel_flower");
    public static final RegistryObject<Block> AURORA_IRIS = registerFlower("aurora_iris");
    public static final RegistryObject<Block> ELYSIAN_ROSE = registerFlower("elysian_rose");
    public static final RegistryObject<Block> HECATE_ORCHID = registerFlower("hecate_orchid");
    public static final RegistryObject<Block> MOURNING_HYACINTH = registerFlower("mourning_hyacinth");
    public static final RegistryObject<Block> NARCISSE_BLOOM = registerFlower("narcisse_bloom");
    public static final RegistryObject<Block> POMEGRANATE_BLOSSOM = registerFlower("pomegranate_blossom");
    public static final RegistryObject<Block> STYGIAN_LOTUS = registerFlower("stygian_lotus");
    public static final RegistryObject<Block> EREBON_LILY = registerFlower("erebon_lily");
    public static final RegistryObject<Block> PERSEPHONES_BLOSSOM = registerFlower("persephones_blossom");

    // Herbs
    public static final RegistryObject<Block> AMBROSIA_HERB = registerHerb("ambrosia_herb");
    public static final RegistryObject<Block> EMBER_SAGE = registerHerb("ember_sage");
    public static final RegistryObject<Block> GRAVE_THYME = registerHerb("grave_thyme");
    public static final RegistryObject<Block> IRONWORT = registerHerb("ironwort");
    public static final RegistryObject<Block> LETHEAN_MINT = registerHerb("lethean_mint");
    public static final RegistryObject<Block> NECTAR_LEAF = registerHerb("nectar_leaf");
    public static final RegistryObject<Block> ORELEAF_OREGANO = registerHerb("oreleaf_oregano");
    public static final RegistryObject<Block> SUNBASIL = registerHerb("sunbasil");

    // Trees
    public static final RegistryObject<Block> EBON_FIG_SAPLING = registerSapling("ebon_fig");
    public static final RegistryObject<Block> LUMINARIS_TREE_SAPLING = registerSapling("luminaris_tree");
    public static final RegistryObject<Block> PHANTOM_OLIVE_SAPLING = registerSapling("phantom_olive");
    public static final RegistryObject<Block> POMEGRANATE_TREE_SAPLING = registerSapling("pomegranate_tree");
    public static final RegistryObject<Block> UNDERBLOOM_MAGNOLIA_SAPLING = registerSapling("underbloom_magnolia");
    public static final RegistryObject<Block> VEILWILLOW_SAPLING = registerSapling("veilwillow");
    public static final RegistryObject<Block> WHITE_CYPRESS_SAPLING = registerSapling("white_cypress");
    public static final RegistryObject<Block> HESPERID_APPLE_TREELET_SAPLING = registerSapling("hesperid_apple_treelet");

    // Specials
    public static final RegistryObject<Block> WRAITHVINE = registerVine("wraithvine");
    public static final RegistryObject<Block> CHARONS_REED = registerReed("charons_reed");

    private static RegistryObject<Block> registerCrop(String name) {
        RegistryObject<Block> block = BLOCKS.register(name + "_crop", () -> new HellasCropBlock(ModItems.seedSupplier(name)));
        ModItems.registerCropItems(name, block);
        CUTOUT_BLOCKS.add(block);
        return block;
    }

    private static RegistryObject<Block> registerBush(String name) {
        RegistryObject<Block> block = BLOCKS.register(name + "_bush", () -> new HellasBushBlock(ModItems.produceSupplier(name)));
        ModItems.registerPlantItems(name, block, false);
        CUTOUT_BLOCKS.add(block);
        return block;
    }

    private static RegistryObject<Block> registerHerb(String name) {
        RegistryObject<Block> block = BLOCKS.register(name + "_bush", HellasHerbBlock::new);
        ModItems.registerPlantItems(name, block, false);
        CUTOUT_BLOCKS.add(block);
        return block;
    }

    private static RegistryObject<Block> registerFlower(String name) {
        RegistryObject<Block> block = BLOCKS.register(name + "_flower", HellasFlowerBlock::new);
        ModItems.registerPlantItems(name, block, false);
        CUTOUT_BLOCKS.add(block);
        return block;
    }

    private static RegistryObject<Block> registerSapling(String name) {
        RegistryObject<Block> block = BLOCKS.register(name + "_sapling", HellasSaplingBlock::new);
        ModItems.registerPlantItems(name, block, false);
        CUTOUT_BLOCKS.add(block);
        return block;
    }

    private static RegistryObject<Block> registerVine(String name) {
        RegistryObject<Block> block = BLOCKS.register(name + "_vine", HellasVineBlock::new);
        ModItems.registerPlantItems(name, block, false);
        CUTOUT_BLOCKS.add(block);
        return block;
    }

    private static RegistryObject<Block> registerReed(String name) {
        RegistryObject<Block> block = BLOCKS.register(name + "_reeds", HellasReedBlock::new);
        ModItems.registerPlantItems(name, block, false);
        CUTOUT_BLOCKS.add(block);
        return block;
    }

    public static List<RegistryObject<? extends Block>> getCutoutBlocks() {
        return Collections.unmodifiableList(CUTOUT_BLOCKS);
    }
}
