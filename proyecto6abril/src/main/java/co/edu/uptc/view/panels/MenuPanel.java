package co.edu.uptc.view.panels;

import co.edu.uptc.interfaces.PresenterInterface;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    private final PresenterInterface presenter;
    private final MenuListener listener;

    public interface MenuListener { 
        void onSelectPersonas();
        void onSelectProductos();
        void onSelectContabilidad();
        void onExit();
    }

    public MenuPanel(PresenterInterface presenter, MenuListener listener) {
        this.presenter = presenter;
        this.listener = listener;
        buildUI();
    }

    private void buildUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalGlue());
        addTitle();
        addMenuButtons();
        add(Box.createVerticalGlue());
    }

    private void addTitle() {
        JLabel title = new JLabel("SISTEMA DE GESTIÓN UPTC");
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        add(title);
        add(Box.createVerticalStrut(30));
    }

    private void addMenuButtons() {
        addMenuButtonWithGap("Personas", e -> listener.onSelectPersonas());
        addMenuButtonWithGap("Productos", e -> listener.onSelectProductos());
        addMenuButtonWithGap("Contabilidad", e -> listener.onSelectContabilidad());
        addMenuButtonWithGap("Salir", e -> listener.onExit());
    }

    private void addMenuButtonWithGap(String text, ActionListener action) {
        add(buildMenuButton(text, action));
        add(Box.createVerticalStrut(10));
    }

    private JButton buildMenuButton(String text, ActionListener action) {
        JButton btn = new JButton(text);
        btn.setAlignmentX(CENTER_ALIGNMENT);
        btn.setMaximumSize(new java.awt.Dimension(200, 40));
        btn.addActionListener(action);
        return btn;
    }
}