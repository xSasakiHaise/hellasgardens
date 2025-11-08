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
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

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
    public static final RegistryObject<Block> EBON_FIG_LOG = registerLog("ebon_fig");
    public static final RegistryObject<Block> STRIPPED_EBON_FIG_LOG = registerStrippedLog("ebon_fig");
    public static final RegistryObject<Block> EBON_FIG_PLANKS = registerPlanks("ebon_fig");
    public static final RegistryObject<Block> EBON_FIG_LEAVES = registerLeaves("ebon_fig");
    public static final RegistryObject<Block> EBON_FIG_SAPLING = registerSapling("ebon_fig", EBON_FIG_LOG, EBON_FIG_LEAVES);

    public static final RegistryObject<Block> LUMINARIS_TREE_LOG = registerLog("luminaris_tree");
    public static final RegistryObject<Block> STRIPPED_LUMINARIS_TREE_LOG = registerStrippedLog("luminaris_tree");
    public static final RegistryObject<Block> LUMINARIS_TREE_PLANKS = registerPlanks("luminaris_tree");
    public static final RegistryObject<Block> LUMINARIS_TREE_LEAVES = registerLeaves("luminaris_tree");
    public static final RegistryObject<Block> LUMINARIS_TREE_SAPLING = registerSapling("luminaris_tree", LUMINARIS_TREE_LOG, LUMINARIS_TREE_LEAVES);

    public static final RegistryObject<Block> PHANTOM_OLIVE_LOG = registerLog("phantom_olive");
    public static final RegistryObject<Block> STRIPPED_PHANTOM_OLIVE_LOG = registerStrippedLog("phantom_olive");
    public static final RegistryObject<Block> PHANTOM_OLIVE_PLANKS = registerPlanks("phantom_olive");
    public static final RegistryObject<Block> PHANTOM_OLIVE_LEAVES = registerLeaves("phantom_olive");
    public static final RegistryObject<Block> PHANTOM_OLIVE_SAPLING = registerSapling("phantom_olive", PHANTOM_OLIVE_LOG, PHANTOM_OLIVE_LEAVES);

    public static final RegistryObject<Block> UNDERBLOOM_MAGNOLIA_LOG = registerLog("underbloom_magnolia");
    public static final RegistryObject<Block> STRIPPED_UNDERBLOOM_MAGNOLIA_LOG = registerStrippedLog("underbloom_magnolia");
    public static final RegistryObject<Block> UNDERBLOOM_MAGNOLIA_PLANKS = registerPlanks("underbloom_magnolia");
    public static final RegistryObject<Block> UNDERBLOOM_MAGNOLIA_LEAVES = registerLeaves("underbloom_magnolia");
    public static final RegistryObject<Block> UNDERBLOOM_MAGNOLIA_SAPLING = registerSapling("underbloom_magnolia", UNDERBLOOM_MAGNOLIA_LOG, UNDERBLOOM_MAGNOLIA_LEAVES);

    public static final RegistryObject<Block> VEILWILLOW_LOG = registerLog("veilwillow");
    public static final RegistryObject<Block> STRIPPED_VEILWILLOW_LOG = registerStrippedLog("veilwillow");
    public static final RegistryObject<Block> VEILWILLOW_PLANKS = registerPlanks("veilwillow");
    public static final RegistryObject<Block> VEILWILLOW_LEAVES = registerLeaves("veilwillow");
    public static final RegistryObject<Block> VEILWILLOW_SAPLING = registerSapling("veilwillow", VEILWILLOW_LOG, VEILWILLOW_LEAVES);

    public static final RegistryObject<Block> WHITE_CYPRESS_LOG = registerLog("white_cypress");
    public static final RegistryObject<Block> STRIPPED_WHITE_CYPRESS_LOG = registerStrippedLog("white_cypress");
    public static final RegistryObject<Block> WHITE_CYPRESS_PLANKS = registerPlanks("white_cypress");
    public static final RegistryObject<Block> WHITE_CYPRESS_LEAVES = registerLeaves("white_cypress");
    public static final RegistryObject<Block> WHITE_CYPRESS_SAPLING = registerSapling("white_cypress", WHITE_CYPRESS_LOG, WHITE_CYPRESS_LEAVES);

    public static final RegistryObject<Block> HESPERID_APPLE_TREELET_LOG = registerLog("hesperid_apple_treelet");
    public static final RegistryObject<Block> STRIPPED_HESPERID_APPLE_TREELET_LOG = registerStrippedLog("hesperid_apple_treelet");
    public static final RegistryObject<Block> HESPERID_APPLE_TREELET_PLANKS = registerPlanks("hesperid_apple_treelet");
    public static final RegistryObject<Block> HESPERID_APPLE_TREELET_LEAVES = registerLeaves("hesperid_apple_treelet");
    public static final RegistryObject<Block> HESPERID_APPLE_TREELET_SAPLING = registerSapling("hesperid_apple_treelet", HESPERID_APPLE_TREELET_LOG, HESPERID_APPLE_TREELET_LEAVES);

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

    private static RegistryObject<Block> registerSapling(String name, RegistryObject<Block> log, RegistryObject<Block> leaves) {
        return registerSapling(name, () -> log.get().defaultBlockState(), () -> leaves.get().defaultBlockState());
    }

    private static RegistryObject<Block> registerSapling(String name, Supplier<BlockState> logState, Supplier<BlockState> leavesState) {
        RegistryObject<Block> block = BLOCKS.register(name + "_sapling", () -> new HellasSaplingBlock(logState, leavesState));
        ModItems.registerPlantItems(name, block, false);
        CUTOUT_BLOCKS.add(block);
        return block;
    }

    private static RegistryObject<Block> registerSapling(String name) {
        return registerSapling(name, () -> null, () -> null);
    }

    private static RegistryObject<Block> registerLeaves(String name) {
        RegistryObject<Block> block = registerBlock(name + "_leaves", () -> new LeavesBlock(Block.Properties.copy(Blocks.OAK_LEAVES)));
        CUTOUT_BLOCKS.add(block);
        return block;
    }

    private static RegistryObject<Block> registerLog(String name) {
        return registerBlock(name + "_log", () -> new RotatedPillarBlock(Block.Properties.copy(Blocks.OAK_LOG)));
    }

    private static RegistryObject<Block> registerStrippedLog(String name) {
        return registerBlock("stripped_" + name + "_log", () -> new RotatedPillarBlock(Block.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    }

    private static RegistryObject<Block> registerPlanks(String name) {
        return registerBlock(name + "_planks", () -> new Block(Block.Properties.copy(Blocks.OAK_PLANKS)));
    }

    private static RegistryObject<Block> registerBlock(String name, Supplier<Block> blockSupplier) {
        RegistryObject<Block> block = BLOCKS.register(name, blockSupplier);
        ModItems.registerBlockItem(name, block);
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
