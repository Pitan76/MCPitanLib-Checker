package net.pitan76.mcpitanlibchecker.forge;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.IModInfo;
import net.pitan76.mcpitanlibchecker.CheckerUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckerUtilImpl {
    public static List<String> getListDependents(List<String> nameList) {
        List<String> list = new ArrayList<>();
        List<String> dependencyList = Arrays.asList(CheckerUtil.dependencies);

        for (IModInfo mod : ModList.get().getMods()) {
            for (IModInfo.ModVersion dependency : mod.getDependencies()) {
                if (!dependencyList.contains(dependency.getModId())) continue;
                System.out.println(mod.getDisplayName() + " is dependent on " + dependency.getModId());
                if (nameList != null)
                    nameList.add(mod.getDisplayName());

                list.add(mod.getModId());
                break;
            }
        }
        return list;
    }
}
