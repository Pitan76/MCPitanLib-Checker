package net.pitan76.mcpitanlibchecker;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.SharedConstants;
import org.apache.logging.log4j.Level;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class DownloadUtil {
    protected static Gson gson = new Gson();

    public static void downloadFromModrinth(String projectId) throws IOException {
        System.out.println("Downloading MCPitanLib...");

        // Download the file from the URL
        final URL url = new URL("https://api.modrinth.com/v2/project/" + projectId + "/version?game_versions=[%22" + SharedConstants.VERSION_NAME + "%22]&loaders=[%22" + MCPitanLibChecker.loader + "%22]");
        JsonArray res = gson.fromJson(new InputStreamReader(url.openStream()), JsonArray.class);

        JsonObject latestVersion = res.get(0).getAsJsonObject();
        if (latestVersion == null)
            MCPitanLibChecker.LOGGER.error("Failed to download MCPitanLib: No version found");

        JsonObject file = latestVersion.get("files").getAsJsonArray().get(0).getAsJsonObject();
        String downloadUrl = file.get("url").getAsString();
        String fileName = file.get("name").getAsString();

        Path filePath = MCPitanLibChecker.getGameDir().resolve("mods").resolve(fileName);

        MCPitanLibChecker.log(Level.INFO, "Downloading MCPitanLib from " + downloadUrl);

        try {
            URL download = new URL(downloadUrl);
            try (InputStream in = download.openStream()) {
                Files.copy(in, filePath);
            }
            MCPitanLibChecker.log(Level.INFO, "Downloaded MCPitanLib to " + filePath);
        } catch (MalformedURLException e) {
            MCPitanLibChecker.LOGGER.error("Failed to download MCPitanLib: " + e.getMessage());
        }
    }
}
