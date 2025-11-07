package com.xsasakihaise.hellasgardens;

import com.xsasakihaise.hellascontrol.api.CoreCheck;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(HellasGardens.MOD_ID)
public final class HellasGardens {
    public static final String MOD_ID = "hellasgardens";

    public HellasGardens() {
        CoreCheck.verifyCoreLoaded();

        if (FMLEnvironment.dist == Dist.DEDICATED_SERVER) {
            CoreCheck.verifyEntitled("gardens");
        }

        if (!ModList.get().isLoaded("hellascontrol")) {
            return;
        }

        // Register licensed content here when available.
    }
}
