package com.xsasakihaise.hellasgardens.registry;

import com.xsasakihaise.hellasgardens.HellasGardens;
import com.xsasakihaise.hellasgardens.loot.NetherChestLootModifier;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.RegistryObject;

/**
 * Registers Forge global loot modifier serializers that integrate the new
 * plants into vanilla loot tables.
 */
public class ModLootModifiers {
    /** Deferred register that owns all global loot modifiers for this mod. */
    public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIERS =
        DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, HellasGardens.MOD_ID);

    /** Serializer that injects Hellas plants into Nether-themed loot tables. */
    public static final RegistryObject<GlobalLootModifierSerializer<NetherChestLootModifier>> NETHER_CHEST_LOOT =
        LOOT_MODIFIERS.register("nether_chest_loot", NetherChestLootModifier.Serializer::new);

    private ModLootModifiers() {
    }
}
