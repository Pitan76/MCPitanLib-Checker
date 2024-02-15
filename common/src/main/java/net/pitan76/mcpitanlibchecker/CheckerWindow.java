package net.pitan76.mcpitanlibchecker;

import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CheckerWindow {
    private static final Font FONT = new Font("Arial", Font.PLAIN, 14);

    public static void run() {
        setLookAndFeel();

        JFrame frame = new JFrame("MCPitanLib Checker");
        frame.setVisible(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setSize(360, 240);
        InputStream iconInputStream = MCPitanLibChecker.class.getClassLoader().getResourceAsStream("icon.png");
        if (iconInputStream != null) {
            try {
                frame.setIconImage(ImageIO.read(iconInputStream));
            } catch (IOException ignored) {}
        }
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        JTextPane textPane = getjTextPane();

        frame.getContentPane().add(textPane, BorderLayout.NORTH);

        JLabel modsLabel = new JLabel();
        List<String> modNames = new ArrayList<>();
        List<String> modIds = CheckerUtil.getListDependents(modNames);
        if (!modIds.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (String modName : modNames) {
                sb.append(modName).append("\n");
            }
            modsLabel.setText(sb.toString());
        }
        textPane.insertComponent(modsLabel);


    }

    @NotNull
    private static JTextPane getjTextPane() {

        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setEditable(false);
        textPane.setBackground(null);
        textPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        textPane.setText("<center>MCPitanLib is required to run the following mods.</center>");
        textPane.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);
        textPane.setFont(FONT);
        return textPane;
    }

    public static void setLookAndFeel() {
        // en: Set system properties for font rendering , ja: フォントレンダリングのためのシステムプロパティを設定
        System.setProperty("awt.useSystemAAFontSettings", "lcd");
        System.setProperty("swing.aatext", "true");

        // en: Set the look and feel to the system look and feel , ja: ルックアンドフィールをシステムルックアンドフィールに設定
        try {
            if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                return;
            }

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            for (UIManager.LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
                if (!"GTK+".equals(laf.getName())) continue;
                UIManager.setLookAndFeel(laf.getClassName());
            }


        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored) {}
    }
}