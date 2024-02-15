package net.pitan76.mcpitanlibchecker;

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
        frame.setLocationByPlatform(true);

        setIcon(frame, MCPitanLibChecker.class.getClassLoader().getResourceAsStream("mcpitanlib_checker_icon.png"));
        setupPanel(frame);

        frame.pack();
        frame.setVisible(true);
        frame.requestFocus();
        synchronized (CheckerWindow.class) {
            try {
                CheckerWindow.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setupPanel(JFrame frame) {
        // Header
        JTextPane textPane = createTextPane("<center><a href=\"https://www.curseforge.com/minecraft/mc-mods/mcpitanlibarch\">MCPitanLib</a>" + Messages.HEADER + "</center>");
        textPane.addMouseListener(new HyperlinkMouseListener());
        frame.getContentPane().add(textPane, BorderLayout.NORTH);

        // Mods
        JTextPane modsPane = createTextPane();
        List<String> modNames = new ArrayList<>();
        List<String> modIds = CheckerUtil.getListDependents(modNames);
        if (!modIds.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("<ul>");
            for (String modName : modNames) {
                sb.append("<li>").append(modName).append("</li>");
            }
            sb.append("</ul>");
            modsPane.setText(sb.toString());
        }
        frame.getContentPane().add(modsPane, BorderLayout.CENTER);

        // Footer
        JTextPane footerPane = createTextPane(5, 5, 5, 5);
        footerPane.setText("<center>" + Messages.FOOTER1 + "<br />" + Messages.FOOTER2 + "</center>");
        frame.getContentPane().add(footerPane, BorderLayout.SOUTH);

        // Buttons
        JPanel btnPanel = getBtnPanel(frame);
        frame.getContentPane().add(btnPanel, BorderLayout.SOUTH);
    }

    private static JPanel getBtnPanel(JFrame frame) {
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // Download button
        JButton downloadButton = new JButton(Messages.DOWNLOAD_BTN);
        downloadButton.addActionListener(e -> {
            try {
                DownloadUtil.downloadFromModrinth(MCPitanLibChecker.MCPitanLibProjectID);
                if (! MCPitanLibChecker.isModLoaded("architectury"))
                    DownloadUtil.downloadFromModrinth(MCPitanLibChecker.ArchitecturyAPIProjectID);
                if (MCPitanLibChecker.loader.equalsIgnoreCase("fabric")
                        && !MCPitanLibChecker.isModLoaded("fabric"))
                    DownloadUtil.downloadFromModrinth(MCPitanLibChecker.FabricAPIProjectID);

                JDialog dialog = new JDialog(frame, "Downloaded MCPitanLib", true);
                dialog.setLayout(new BorderLayout());
                JTextPane textPane = createTextPane(Messages.DOWNLOADED_MSG);
                dialog.getContentPane().add(textPane, BorderLayout.CENTER);
                dialog.setSize(240, 120);
                dialog.setLocationRelativeTo(frame);
                dialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        System.exit(0);
                    }
                });
                JButton closeButton = new JButton(Messages.CLOSE_BTN);
                closeButton.addActionListener(e1 -> dialog.dispose());
                dialog.getContentPane().add(closeButton, BorderLayout.SOUTH);
                dialog.pack();
                dialog.setVisible(true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // Close button
        JButton closeButton = new JButton(Messages.CLOSE_BTN);
        closeButton.addActionListener(e -> {
            frame.dispose();
            synchronized (CheckerWindow.class) {
                CheckerWindow.class.notify();
            }
        });

        btnPanel.add(downloadButton);
        btnPanel.add(closeButton);
        return btnPanel;
    }

    public static JTextPane createTextPane() {
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setEditable(false);
        textPane.setBackground(null);
        textPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        textPane.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);
        textPane.setFont(FONT);
        return textPane;
    }

    public static JTextPane createTextPane(int top, int left, int bottom, int right) {
        JTextPane textPane = createTextPane();
        textPane.setBorder(new EmptyBorder(top, left, bottom, right));
        return textPane;
    }

    public static JTextPane createTextPane(String text) {
        JTextPane textPane = createTextPane();
        textPane.setText(text);
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

    public static void setIcon(JFrame frame, InputStream inputStream) {
        if (inputStream != null) {
            try {
                frame.setIconImage(ImageIO.read(inputStream));
            } catch (IOException ignored) {}
        }
    }
}