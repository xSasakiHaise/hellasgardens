package com.xsasakihaise.hellasgardens.loot;

import com.google.gson.JsonObject;
import com.xsasakihaise.hellasgardens.registry.ModBlocks;
import com.xsasakihaise.hellasgardens.registry.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class NetherChestLootModifier extends LootModifier {
    private static final Set<ResourceLocation> TARGET_LOOT_TABLES = new LinkedHashSet<>(Arrays.asList(
        new ResourceLocation("minecraft", "chests/nether_bridge"),
        new ResourceLocation("minecraft", "chests/bastion_bridge"),
        new ResourceLocation("minecraft", "chests/bastion_hoglin_stable"),
        new ResourceLocation("minecraft", "chests/bastion_other"),
        new ResourceLocation("minecraft", "chests/bastion_treasure"),
        new ResourceLocation("minecraft", "chests/ruined_portal")
    ));

    private final float chancePerItem;

    public NetherChestLootModifier(ILootCondition[] conditionsIn, float chancePerItem) {
        super(conditionsIn);
        this.chancePerItem = chancePerItem;
    }

    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        ResourceLocation tableId = context.getQueriedLootTableId();
        if (tableId == null || !TARGET_LOOT_TABLES.contains(tableId)) {
            return generatedLoot;
        }

        Random random = context.getRandom();
        List<ItemStack> extraLoot = new ArrayList<>();

        Set<Item> items = new LinkedHashSet<>();
        ModItems.getSeedItems().forEach(item -> addItem(items, item.get()));
        ModItems.getProduceItems().forEach(item -> addItem(items, item.get()));
        ModBlocks.BLOCKS.getEntries().forEach(block -> addItem(items, block.get().asItem()));

        for (Item item : items) {
            if (random.nextFloat() < this.chancePerItem) {
                extraLoot.add(new ItemStack(item));
            }
        }

        generatedLoot.addAll(extraLoot);
        return generatedLoot;
    }

    private void addItem(Set<Item> items, Item item) {
        if (item != null && item != Items.AIR) {
            items.add(item);
        }
    }

    public static class Serializer extends GlobalLootModifierSerializer<NetherChestLootModifier> {
        @Override
        public NetherChestLootModifier read(ResourceLocation location, JsonObject object, ILootCondition[] conditions) {
            float chance = JSONUtils.getAsFloat(object, "chance", 0.05F);
            return new NetherChestLootModifier(conditions, chance);
        }

        @Override
        public JsonObject write(NetherChestLootModifier instance) {
            JsonObject object = this.makeConditions(instance.conditions);
            object.addProperty("chance", instance.chancePerItem);
            return object;
        }
    }
}
