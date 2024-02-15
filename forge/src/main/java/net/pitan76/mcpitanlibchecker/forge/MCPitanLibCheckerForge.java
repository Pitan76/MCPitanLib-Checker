package net.pitan76.mcpitanlibchecker.forge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.pitan76.mcpitanlibchecker.MCPitanLibChecker;

@Mod(MCPitanLibChecker.MOD_ID)
public class MCPitanLibCheckerForge {
    public MCPitanLibCheckerForge() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
    }

    public void onClientSetup(final FMLClientSetupEvent e) {
        MCPitanLibChecker.init("forge");
    }
}