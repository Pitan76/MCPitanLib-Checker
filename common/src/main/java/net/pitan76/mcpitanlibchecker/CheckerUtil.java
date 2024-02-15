package net.pitan76.mcpitanlibchecker;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckerUtil {
    public static String[] dependencies = new String[] {
            "mcpitanlibarch",
            "mcpitanlib"
    };

    /**
     * Get list of dependents mod id
     * @param nameList insert mod name to list
     * @return list of dependents mod id
     */
    @ExpectPlatform
    public static List<String> getListDependents(List<String> nameList) {
        return new ArrayList<>();
    }

    /**
     * Get list of dependents mod id
     * @return list of dependents mod id
     */
    public static List<String> getListDependents() {
        return getListDependents(null);
    }
}
