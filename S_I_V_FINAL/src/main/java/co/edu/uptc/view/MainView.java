package co.edu.uptc.view;

import co.edu.uptc.config.ConfigLoader;
import co.edu.uptc.config.MessageProvider;
import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.interfaces.ViewInterface;
import co.edu.uptc.pojo.AccountingMovement;
import co.edu.uptc.pojo.Person;
import co.edu.uptc.pojo.Product;
import co.edu.uptc.view.menu.AccountingMenu;
import co.edu.uptc.view.menu.PersonMenu;
import co.edu.uptc.view.menu.ProductMenu;

import java.util.List;

public class MainView implements ViewInterface {

    private PresenterInterface presenter;
    private final InputReader reader = new InputReader();

    @Override
    public void setPresenter(PresenterInterface presenter) {
        this.presenter = presenter;
    }

    @Override
    public void start() {
        MessageProvider.load(ConfigLoader.get("language"));
        PersonMenu personMenu = new PersonMenu(presenter, reader);
        ProductMenu productMenu = new ProductMenu(presenter, reader);
        AccountingMenu accountingMenu = new AccountingMenu(presenter, reader);
        runMainLoop(personMenu, productMenu, accountingMenu);
        System.out.println(MessageProvider.get("msg.goodbye"));
    }

    private void runMainLoop(PersonMenu personMenu, ProductMenu productMenu, AccountingMenu accountingMenu) {
        boolean active = true;
        while (active) {
            printMainMenu();
            int option = reader.readOption(1, 4);
            switch (option) {
                case 1 -> personMenu.show();
                case 2 -> productMenu.show();
                case 3 -> accountingMenu.show();
                case 4 -> active = false;
            }
        }
    }

    private void printMainMenu() {
        System.out.println("\n" + MessageProvider.get("menu.main.title"));
        System.out.println(MessageProvider.get("menu.main.persons"));
        System.out.println(MessageProvider.get("menu.main.products"));
        System.out.println(MessageProvider.get("menu.main.accounting"));
        System.out.println(MessageProvider.get("menu.main.exit"));
    }

    @Override
    public void showSuccess(String message) {
        System.out.println("  [OK] " + message);
    }

    @Override
    public void showError(String message) {
        System.out.println("  [ERROR] " + message);
    }

    @Override
    public void displayPersons(List<Person> persons) {
        TablePrinter.printPersons(persons);
    }

    @Override
    public void displayProducts(List<Product> products) {
        TablePrinter.printProducts(products);
    }

    @Override
    public void displayAccountingMovements(List<AccountingMovement> movements, double balance) {
        TablePrinter.printAccountingMovements(movements, balance);
    }
}