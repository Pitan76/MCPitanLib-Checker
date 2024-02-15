package net.pitan76.mcpitanlibchecker;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.nio.file.Path;

/**
 * Main class of MCPitanLib Checker
 * Reference: <a href="https://github.com/wisp-forest/owo-lib/">owo-lib</a>
 */
public class MCPitanLibChecker {
    public static final String MOD_NAME = "MCPitanLib Checker";
    public static final String MOD_ID = "mcpitanlib_checker";
    public static final String MCPITANLIB_IMPL_ID = "mcpitanlib-impl";
    public static final String MCPitanLibProjectID = "mcpitanlibarch";

    public static Logger LOGGER = LogManager.getLogger();
    public static void log(Level level, String message){
        LOGGER.log(level, "[" + MOD_NAME + "] " + message);
    }

    public static final boolean FORCE_CONSOLE = Boolean.getBoolean("mcpitanlibchecker.console");

    public static void main(String[] args) {
        init("fabric");
    }

    public static String loader = "fabric";

    public static void init(String loader1) {
        loader = loader1;
        if (isModLoaded(MCPITANLIB_IMPL_ID)) return;

        try {
            if (GraphicsEnvironment.isHeadless() || FORCE_CONSOLE) {
                CheckerConsole.run();
            } else {
                CheckerWindow.run();
            }
        } catch (Exception e) {
            LOGGER.error("Error thrown while opening mcpitanlib checker!", e);
        }
    }

    @ExpectPlatform
    public static boolean isModLoaded(String modid) {
        return true;
    }

    @ExpectPlatform
    public static Path getGameDir() {
        return FabricLoader.getInstance().getGameDir();
    }
}