package com.xsasakihaise.hellasgardens;

import com.xsasakihaise.hellasgardens.registry.ModBlocks;
import com.xsasakihaise.hellasgardens.registry.ModItems;
import com.xsasakihaise.hellasgardens.registry.ModLootModifiers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import com.xsasakihaise.hellascontrol.CoreCheck;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Root entry point for the Hellas Gardens sidemod.
 * <p>
 * The mod focuses on registering new flora content (crops, bushes, trees and
 * related block items) together with the loot modifiers that distribute those
 * plants across the world. The constructor wires every registry into the
 * Forge/NeoForge mod lifecycle and kicks off client-only rendering setup.
 */
@Mod(HellasGardens.MOD_ID)
public class HellasGardens {
    /** The identifier shared across every registry entry of the mod. */
    public static final String MOD_ID = "hellasgardens";
    private static final Logger LOGGER = LogManager.getLogger("HellasGardens");
    private static final String ENTITLEMENT_KEY = MOD_ID;
    private static volatile boolean ENABLED = false;
    private static volatile String DISABLE_REASON = "UNINITIALIZED";

    /**
     * Creates the mod instance and registers the block, item and loot modifier
     * registries on the mod event bus.
     */
    public HellasGardens() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModLootModifiers.LOOT_MODIFIERS.register(modEventBus);
        modEventBus.addListener(this::onCommonSetup);

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> Client::init);
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(this::initGate);
    }

    private void initGate() {
        if (FMLEnvironment.dist != Dist.DEDICATED_SERVER) {
            ENABLED = true;
            DISABLE_REASON = "OK (non-dedicated)";
            return;
        }

        if (!ModList.get().isLoaded("hellascontrol")) {
            ENABLED = false;
            DISABLE_REASON = "HellasControl missing";
            LOGGER.warn("[HellasGardens] disabled: {}", DISABLE_REASON);
            return;
        }

        try {
            CoreCheck.verifyCoreLoaded();
            CoreCheck.verifyEntitled(ENTITLEMENT_KEY);

            ENABLED = true;
            DISABLE_REASON = "OK";
            LOGGER.info("[HellasGardens] enabled (license OK) entitlement='{}'", ENTITLEMENT_KEY);
        } catch (Exception e) {
            ENABLED = false;
            DISABLE_REASON = "License invalid";
            LOGGER.warn("[HellasGardens] disabled: {} entitlement='{}'", DISABLE_REASON, ENTITLEMENT_KEY, e);
        }
    }

    private static class Client {
        /**
         * Registers client-side handlers once the physical client environment is
         * detected. This currently only hooks the client setup callback below.
         */
        static void init() {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(Client::onClientSetup);
        }

        /**
         * Ensures every transparent plant block renders with the appropriate
         * cutout layer so their textures remain crisp and non-opaque.
         */
        private static void onClientSetup(final FMLClientSetupEvent event) {
            if (!ENABLED) {
                return;
            }
            event.enqueueWork(() -> {
                RenderType layer = RenderType.cutout();
                ModBlocks.getCutoutBlocks().forEach(block -> RenderTypeLookup.setRenderLayer(block.get(), layer));
            });
        }
    }
}
