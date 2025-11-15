package com.xsasakihaise.hellasgardens.registry;

import com.xsasakihaise.hellasgardens.HellasGardens;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Item registry companion to {@link ModBlocks}. It exposes all seed, produce
 * and block items derived from the garden-themed blocks so other mods can
 * reference them safely.
 */
public class ModItems {
    /** Deferred register containing every custom item added by the mod. */
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HellasGardens.MOD_ID);

    private static final Map<String, RegistryObject<Item>> SEED_ITEMS = new LinkedHashMap<>();
    private static final Map<String, RegistryObject<Item>> PRODUCE_ITEMS = new LinkedHashMap<>();

    private static final Item.Properties DEFAULT_PROPS = new Item.Properties().tab(ItemGroup.TAB_MISC);
    private static final Item.Properties DEFAULT_BLOCK_PROPS = new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS);

    private ModItems() {
    }

    /**
     * Registers the standard seed + produce items for a crop that reuses the
     * provided block.
     */
    public static void registerCropItems(String name, RegistryObject<? extends Block> block) {
        registerPlantItems(name, block, true);
    }

    /**
     * Adds plant items for berries/herbs/crops, optionally registering the seed
     * as a {@link BlockNamedItem} when the plant is growable like vanilla
     * crops.
     */
    public static void registerPlantItems(String name, RegistryObject<? extends Block> block, boolean crop) {
        RegistryObject<Item> seed = ITEMS.register(name + "_seed", () -> crop
            ? new BlockNamedItem(block.get(), DEFAULT_PROPS)
            : new BlockItem(block.get(), DEFAULT_PROPS));
        RegistryObject<Item> produce = ITEMS.register(name + "_produce", () -> new Item(DEFAULT_PROPS));
        SEED_ITEMS.put(name, seed);
        PRODUCE_ITEMS.put(name, produce);
    }

    /** Registers a plain block item pointing to the supplied block instance. */
    public static void registerBlockItem(String name, Supplier<? extends Block> block) {
        ITEMS.register(name, () -> new BlockItem(block.get(), DEFAULT_BLOCK_PROPS));
    }

    /**
     * Returns a supplier that resolves a seed item lazily (safe before item
     * registry completion).
     */
    public static Supplier<Item> seedSupplier(String name) {
        return () -> {
            RegistryObject<Item> object = SEED_ITEMS.get(name);
            return object == null ? Items.AIR : object.get();
        };
    }

    /**
     * Returns a lazily evaluated supplier for the produce item of a plant.
     */
    public static Supplier<Item> produceSupplier(String name) {
        return () -> {
            RegistryObject<Item> object = PRODUCE_ITEMS.get(name);
            return object == null ? Items.AIR : object.get();
        };
    }

    /** Immutable view over all registered seed items. */
    public static Collection<RegistryObject<Item>> getSeedItems() {
        return Collections.unmodifiableCollection(SEED_ITEMS.values());
    }

    /** Immutable view over all registered produce items. */
    public static Collection<RegistryObject<Item>> getProduceItems() {
        return Collections.unmodifiableCollection(PRODUCE_ITEMS.values());
    }
}
