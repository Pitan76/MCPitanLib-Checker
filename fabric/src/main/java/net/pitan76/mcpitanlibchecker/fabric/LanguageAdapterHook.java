package net.pitan76.mcpitanlibchecker.fabric;

import net.fabricmc.loader.api.LanguageAdapter;
import net.fabricmc.loader.api.ModContainer;
import net.pitan76.mcpitanlibchecker.MCPitanLibChecker;

public class LanguageAdapterHook implements LanguageAdapter {
    @Override
    public <T> T create(ModContainer mod, String value, Class<T> type) {
        throw new UnsupportedOperationException();
    }

    static {
        MCPitanLibChecker.init();
    }
}
