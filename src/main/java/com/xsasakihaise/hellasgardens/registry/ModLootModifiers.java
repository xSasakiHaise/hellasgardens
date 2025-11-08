package com.xsasakihaise.hellasgardens.registry;

import com.xsasakihaise.hellasgardens.HellasGardens;
import com.xsasakihaise.hellasgardens.loot.NetherChestLootModifier;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.RegistryObject;

public class ModLootModifiers {
    public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIERS =
        DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, HellasGardens.MOD_ID);

    public static final RegistryObject<GlobalLootModifierSerializer<NetherChestLootModifier>> NETHER_CHEST_LOOT =
        LOOT_MODIFIERS.register("nether_chest_loot", NetherChestLootModifier.Serializer::new);

    private ModLootModifiers() {
    }
}
