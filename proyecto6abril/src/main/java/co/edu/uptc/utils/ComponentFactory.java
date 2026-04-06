package co.edu.uptc.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ComponentFactory {

    public static JButton createButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.addActionListener(action);
        return button;
    }

    public static JLabel createLabel(String text) {
        return new JLabel(text);
    }

    public static JTextField createTextField(int columns) {
        return new JTextField(columns);
    }

    public static JPanel createGridPanel(int rows, int cols, int hGap, int vGap) {
        JPanel panel = new JPanel(new GridLayout(rows, cols, hGap, vGap));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return panel;
    }

    public static JPanel createBorderPanel() {
        return new JPanel(new BorderLayout(10, 10));
    }
    
    private ComponentFactory() {}
}