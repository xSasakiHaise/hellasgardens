# HellasGardens Content Overview

This module implements the HellasGardens plant suite for Forge 1.16.5.

## Adding Textures
All textures are referenced but intentionally absent. Supply PNGs in the paths documented below (remove the existing `.keep` markers when replacing them):

```
assets/hellasgardens/textures/block/
  bushes/<species>/{seed,sprout,grown_no_flower,fruiting}.png
  crops/<species>/{seed_sprout,vegetative,budding,harvest}.png
  flowers/<species>/{seed,sprout,bud,bloom}.png
  herbs/<species>/{seed,growth,harvestable}.png
  trees/<species>/{sapling,log,leaves,leaves_unripe,leaves_ripe,leaves_flower}.png
  special/vines/<species>/{seed,sprout,bloom_forming,full_bloom_or_fruit}.png
  special/reeds/<species>/{seed,sprout,bloom_forming,full_bloom_or_fruit}.png
assets/hellasgardens/textures/item/
  <species>_{seed,produce}.png
```

Replace `<species>` with the identifiers listed in the registries (e.g., `ashmelon`, `wraithvine`).

## Registry Naming
* Blocks use suffixes: `_crop`, `_bush`, `_flower`, `_sapling`, `_vine`, `_reeds`.
* Seeds are `<species>_seed` and produce items are `<species>_produce` across every category.

## Behaviour Summary
* Crops grow through eight ages and harvest at age 7.
* Bushes age from 0–3 and can be harvested via right-click at age 3 for produce.
* Herbs use three growth stages and drop seeds plus produce when fully grown.
* Flowers drop themselves on break and apply a saturation effect when used for suspicious stew.
* Saplings grow placeholder trees via `HellasTree` (3–5 block log pillar with leaf canopy).
* Vines attach like vanilla vines and use CUTOUT render layer.
* Reeds behave like sugar cane with a maximum height of two blocks.

All blocks/items are registered through Forge `DeferredRegister` in the `hellasgardens` namespace.
