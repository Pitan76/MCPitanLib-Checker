package net.pitan76.mcpitanlibchecker;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;

public class CheckerConsole {
    public static void run() {
        JFrame frame = new JFrame("MCPitanLib Checker");
        frame.setVisible(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setSize(300, 200);
        InputStream iconInputStream = MCPitanLibChecker.class.getClassLoader().getResourceAsStream("icon.png");
        if (iconInputStream != null) {
            try {
                frame.setIconImage(ImageIO.read(iconInputStream));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

    }
}
