package co.edu.uptc.view;

import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.interfaces.ViewInterface;
import co.edu.uptc.view.menu.AccountingMenu;
import co.edu.uptc.view.menu.PersonMenu;
import co.edu.uptc.view.menu.ProductMenu;

public class MainView implements ViewInterface {

    private PresenterInterface presenter;
    private final InputReader reader = new InputReader();

    @Override
    public void setPresenter(PresenterInterface presenter) {
        this.presenter = presenter;
    }

    @Override
    public void start() {
        PersonMenu personMenu       = new PersonMenu(presenter, reader);
        ProductMenu productMenu     = new ProductMenu(presenter, reader);
        AccountingMenu accountingMenu = new AccountingMenu(presenter, reader);

        boolean running = true;
        while (running) {
            System.out.println("\n========= MAIN MENU =========");
            System.out.println("  1. Persons");
            System.out.println("  2. Products");
            System.out.println("  3. Accounting");
            System.out.println("  4. Exit");
            int option = reader.readInt("  Select", 1, 4);
            switch (option) {
                case 1 -> personMenu.show();
                case 2 -> productMenu.show();
                case 3 -> accountingMenu.show();
                case 4 -> running = false;
            }
        }
        System.out.println("Goodbye.");
    }

    @Override
    public void showSuccess(String message) {
        System.out.println("  [OK] " + message);
    }

    @Override
    public void showError(String message) {
        System.out.println("  [ERROR] " + message);
    }
}
