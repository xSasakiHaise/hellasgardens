package com.xsasakihaise.hellasgardens;

import com.xsasakihaise.hellasgardens.registry.ModBlocks;
import com.xsasakihaise.hellasgardens.registry.ModItems;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(HellasGardens.MOD_ID)
public class HellasGardens {
    public static final String MOD_ID = "hellasgardens";

    public HellasGardens() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> Client::init);
    }

    private static class Client {
        static void init() {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(Client::onClientSetup);
        }

        private static void onClientSetup(final FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                RenderType layer = RenderType.cutout();
                ModBlocks.getCutoutBlocks().forEach(block -> RenderTypeLookup.setRenderLayer(block.get(), layer));
            });
        }
    }
}
