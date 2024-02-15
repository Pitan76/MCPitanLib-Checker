package net.pitan76.mcpitanlibchecker.fabric;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class MCPitanLibCheckerImpl {
    public static boolean isModLoaded(String modid) {
        return FabricLoader.getInstance().isModLoaded(modid);
    }

    public static Path getGameDir() {
        return FabricLoader.getInstance().getGameDir();
    }
}
