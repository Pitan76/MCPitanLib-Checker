package net.pitan76.mcpitanlibchecker.forge;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class MCPitanLibCheckerImpl {
    public static boolean isModLoaded(String modid) {
        return ModList.get().isLoaded(modid);
    }

    public static Path getGameDir() {
        return FMLPaths.GAMEDIR.get();
    }

    public static String getMinecraftVersion() {
        return ModList.get().getModContainerById("minecraft").get().getModInfo().getVersion().toString();
    }
}
