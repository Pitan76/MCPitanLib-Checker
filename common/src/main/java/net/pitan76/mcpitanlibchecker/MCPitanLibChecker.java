package net.pitan76.mcpitanlibchecker;

import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.Locale;

public class MCPitanLibChecker {
    public static final String MOD_NAME = "MCPitanLib Checker";
    public static final String MOD_ID = "mcpitanlib_checker";
    public static final String MCPITANLIB_ID = "mcpitanlibarch";

    public static Logger LOGGER = LogManager.getLogger();
    public static void log(Level level, String message){
        LOGGER.log(level, "[" + MOD_NAME + "] " + message);
    }

    public static final boolean FORCE_CONSOLE = Boolean.getBoolean("mcpitanlibchecker.console");

    public static void init() {
        if (FabricLoader.getInstance().isModLoaded(MCPITANLIB_ID)) return;

        try {
            if (System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("mac") || GraphicsEnvironment.isHeadless() || FORCE_CONSOLE) {
                CheckerConsole.run();
            } else {
                CheckerWindow.run();
            }
        } catch (Exception e) {
            LOGGER.error("Error thrown while opening mcpitanlib checker!", e);
        }
    }
}