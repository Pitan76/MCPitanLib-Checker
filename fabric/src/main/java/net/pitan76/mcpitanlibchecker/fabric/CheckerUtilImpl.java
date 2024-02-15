package net.pitan76.mcpitanlibchecker.fabric;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModDependency;
import net.pitan76.mcpitanlibchecker.CheckerUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckerUtilImpl {
    public static List<String> getListDependents(List<String> nameList) {
        List<String> list = new ArrayList<>();
        List<String> dependencyList = Arrays.asList(CheckerUtil.dependencies);

        for (ModContainer mod : FabricLoader.getInstance().getAllMods()) {
            for (ModDependency dependency : mod.getMetadata().getDependencies()) {
                if (!dependencyList.contains(dependency.getModId())) continue;
                System.out.println(mod.getMetadata().getName() + " is dependent on " + dependency.getModId());
                if (nameList != null)
                    nameList.add(mod.getMetadata().getName());

                list.add(mod.getMetadata().getId());
                break;
            }
        }
        return list;
    }
}
