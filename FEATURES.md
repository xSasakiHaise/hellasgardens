# HellasGardens

HellasGardens introduces a full suite of stylized flora for the Hellas server network. It focuses on crops, bushes, flowers, herbs, saplings, and themed special plants that enrich world decoration and harvesting loops. The module also injects its content into Nether structures so players can discover seeds and produce through exploration.

## Feature Overview
- **Custom Crops and Bushes** – Adds dozens of growable crops (e.g., Dreamgourd, Styxroot) and berry bushes with bespoke growth logic and produce drops.
- **Herbs and Flowers** – Implements lightweight decorative plants that progress through a few growth stages and can be used for stew effects or ambience.
- **Themed Trees and Saplings** – Provides saplings and accompanying log/leaf blocks for Hellas-specific tree species using the `HellasTree` generator.
- **Special Flora** – Includes vines, reeds with capped height, and melon variants tailored to Hellas biomes.
- **Automated Item Registration** – Every plant registers matching seed and produce items so content appears in inventories, creative tabs, and loot.
- **Nether Loot Integration** – `NetherChestLootModifier` sprinkles Hellas seeds, produce, and blocks into Nether chests with configurable probabilities.

## Technical Overview
- **Entry Point:** `com.xsasakihaise.hellasgardens.HellasGardens` registers blocks, items, and loot modifiers via Forge's mod event bus and configures client render layers for foliage.
- **Registries:** `registry.ModBlocks` and `registry.ModItems` expose `RegistryObject` handles for every block/item pair. Helper methods centralize consistent naming and property configuration.
- **World Generation:** `world.HellasTree` is a minimalist tree grower invoked by `HellasSaplingBlock` to spawn straight trunks with compact leaf canopies when saplings mature.
- **Loot System:** `loot.NetherChestLootModifier` and `registry.ModLootModifiers` plug into Forge's global loot modifier framework to augment Nether chest loot tables with Hellas flora.

## Extending the Mod
- **Adding a Crop/Bush/Flower:** Use the appropriate helper in `ModBlocks` (e.g., `registerCrop`, `registerBush`, `registerFlower`). The helper automatically wires seed/produce items through `ModItems.registerPlantItems`.
- **Creating New Trees:** Register additional logs/leaves in `ModBlocks` and call `registerSapling` with suppliers for the states you want `HellasTree` to place.
- **Integrating with Loot:** Register a new serializer in `ModLootModifiers` and follow the structure of `NetherChestLootModifier.Serializer` to define table filters and probability logic.
- **Referencing Items:** Query `ModItems.getSeedItems()` / `getProduceItems()` or `ModBlocks.BLOCKS.getEntries()` when you need every registered plant for recipes, trades, or loot.

## Dependencies & Environment
- **Minecraft:** 1.16.5
- **Forge:** 36.2.42 (loader range `[36,)` as configured)
- **Pixelmon:** Not directly referenced; this sidemod is world/content focused and can run without Pixelmon-specific APIs. TODO: confirm the exact Pixelmon build used alongside HellasGardens in production.
- **Companion Hellas Mods:** Designed to coexist with other Hellas suite modules but does not require them at compile time.

## Migration Notes
- `registry.ModBlocks` / `ModItems` rely on Forge's `DeferredRegister` APIs; updating to NeoForge or newer Minecraft versions will require revisiting registration signatures.
- `world.HellasTree` uses vanilla `Tree` hooks from 1.16.5. Future worldgen rewrites (1.18+) overhaul tree features, so this logic will need reimplementation using placed/configured features.
- `loot.NetherChestLootModifier` depends on the 1.16 loot modifier serializer contract. Later Minecraft versions may use data-driven loot modifiers or JSON-defined injectors, necessitating a rewrite.
