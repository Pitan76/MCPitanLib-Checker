package net.pitan76.mcpitanlibchecker;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModDependency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckerUtil {
    public static String[] dependencies = new String[] {
            "mcpitanlibarch",
            "architectury",
    };

    /**
     * Get list of dependents mod id
     * @param nameList insert mod name to list
     * @return list of dependents mod id
     */
    public static List<String> getListDependents(List<String> nameList) {
        List<String> list = new ArrayList<>();
        List<String> dependencyList = Arrays.asList(dependencies);

        for (ModContainer mod : FabricLoader.getInstance().getAllMods()) {
            for (ModDependency dependency : mod.getMetadata().getDependencies()) {
                if (!dependencyList.contains(dependency.getModId())) continue;
                if (nameList != null)
                    nameList.add(mod.getMetadata().getName());

                list.add(mod.getMetadata().getId());
            }
        }
        return list;
    }

    /**
     * Get list of dependents mod id
     * @return list of dependents mod id
     */
    public static List<String> getListDependents() {
        return getListDependents(null);
    }
}
