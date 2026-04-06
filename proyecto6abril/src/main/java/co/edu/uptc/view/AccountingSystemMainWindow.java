package co.edu.uptc.view;

import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.interfaces.ViewInterface;
import co.edu.uptc.pojo.AccountingMovement;
import co.edu.uptc.pojo.Person;
import co.edu.uptc.pojo.Product;
import co.edu.uptc.view.panels.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AccountingSystemMainWindow extends JFrame implements ViewInterface {

    private PresenterInterface presenter;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    private MenuPanel menuPanel;
    private PersonPanel personPanel;
    private ProductPanel productPanel;
    private AccountingPanel accountingPanel;

    public AccountingSystemMainWindow() {
        setTitle("SIV");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);
    }

    @Override
    public void setPresenter(PresenterInterface presenter) {
        this.presenter = presenter;
        buildPanels();
    }

    private void buildPanels() {
        menuPanel = createMenuPanel();
        personPanel = new PersonPanel(presenter, this::backToMenu);
        productPanel = new ProductPanel(presenter, this::backToMenu);
        accountingPanel = new AccountingPanel(presenter, this::backToMenu);

        addPanelsToCard();
    }

    private MenuPanel createMenuPanel() {
        return new MenuPanel(presenter, new MenuPanel.MenuListener() {
            @Override public void onSelectPersonas() { presenter.listPersons(); showPanel("personas"); }
            @Override public void onSelectProductos() { presenter.listProducts(); showPanel("productos"); }
            @Override public void onSelectContabilidad() { presenter.listAccountingMovements(); showPanel("contabilidad"); }
            @Override public void onExit() { System.exit(0); }
        });
    }

    private void addPanelsToCard() {
        mainPanel.add(menuPanel, "menu");
        mainPanel.add(personPanel, "personas");
        mainPanel.add(productPanel, "productos");
        mainPanel.add(accountingPanel, "contabilidad");
    }

    private void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }

    private void backToMenu() {
        showPanel("menu");
    }

    @Override
    public void start() {
        setLocationRelativeTo(null);
        setVisible(true);
        showPanel("menu");
    }

    @Override
    public void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void displayPersons(List<Person> persons) {
        if (personPanel != null) personPanel.updateData(persons);
    }

    @Override
    public void displayProducts(List<Product> products) {
        if (productPanel != null) productPanel.updateData(products);
    }

    @Override
    public void displayAccountingMovements(List<AccountingMovement> movements, double balance) {
        if (accountingPanel != null) accountingPanel.updateData(movements, balance);
    }
}
