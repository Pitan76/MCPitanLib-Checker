package net.pitan76.mcpitanlibchecker;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

public class HyperlinkMouseListener extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getButton());
        if (e.getButton() == MouseEvent.BUTTON1) {
            Element element = getHyperlinkElement(e);
            if (element == null) return;
            Object attr = element.getAttributes().getAttribute(HTML.Tag.A);
            if (!(attr instanceof AttributeSet)) return;
            AttributeSet set = (AttributeSet) attr;
            String url = (String) set.getAttribute(HTML.Attribute.HREF);
            if (url == null) return;
            try {
                Desktop.getDesktop().browse(URI.create(url));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private Element getHyperlinkElement(MouseEvent e) {
        JEditorPane editor = (JEditorPane) e.getSource();
        int pos = editor.getUI().viewToModel(editor, e.getPoint());
        if (pos >= 0 && editor.getDocument() instanceof HTMLDocument) {
            HTMLDocument doc = (HTMLDocument) editor.getDocument();
            Element element = doc.getCharacterElement(pos);
            if (element.getAttributes().getAttribute(HTML.Tag.A) != null) {
                return element;
            }
        }
        return null;
    }
}
