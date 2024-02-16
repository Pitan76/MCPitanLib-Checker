package net.pitan76.mcpitanlibchecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CheckerConsole {
    public static void run() {
        System.out.println(Messages.HEADER);

        List<String> modNames = new ArrayList<>();
        List<String> modIds = CheckerUtil.getListDependents(modNames);
        if (!modIds.isEmpty()) {
            for (String modName : modNames) {
                System.out.println("- " + modName);
            }
        }

        System.out.println(Messages.FOOTER1);
        System.out.println(Messages.FOOTER2);

        System.out.println(Messages.DO_DOWNLOAD);

        try {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();

            if (input.equalsIgnoreCase("Y")) {
                DownloadUtil.downloadFromModrinth(MCPitanLibChecker.MCPitanLibProjectID);
                if (! MCPitanLibChecker.isModLoaded("architectury"))
                    DownloadUtil.downloadFromModrinth(MCPitanLibChecker.ArchitecturyAPIProjectID);
                if (MCPitanLibChecker.loader.equalsIgnoreCase("fabric")
                        && !MCPitanLibChecker.isModLoaded("fabric"))
                    DownloadUtil.downloadFromModrinth(MCPitanLibChecker.FabricAPIProjectID);

                MCPitanLibChecker.extraCheck();

                System.out.println(Messages.DOWNLOADED_MSG);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
