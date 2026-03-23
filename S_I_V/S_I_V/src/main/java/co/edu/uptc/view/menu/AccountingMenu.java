package co.edu.uptc.view.menu;

import co.edu.uptc.config.MessageProvider;
import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.view.InputReader;

public class AccountingMenu {

    private final PresenterInterface presenter;
    private final InputReader reader;

    public AccountingMenu(PresenterInterface presenter, InputReader reader) {
        this.presenter = presenter;
        this.reader = reader;
    }

    public void show() {
        boolean active = true;
        while (active) {
            printMenu();
            int option = reader.readOption(1, 5);
            switch (option) {
                case 1 -> handleAdd();
                case 2 -> presenter.requestRemoveAccountingMovement();
                case 3 -> presenter.requestListAccountingMovements();
                case 4 -> presenter.requestExportAccounting();
                case 5 -> active = false;
            }
        }
    }

    private void printMenu() {
        System.out.println("\n" + MessageProvider.get("menu.accounting.title"));
        System.out.println(MessageProvider.get("menu.accounting.add"));
        System.out.println(MessageProvider.get("menu.accounting.remove"));
        System.out.println(MessageProvider.get("menu.accounting.list"));
        System.out.println(MessageProvider.get("menu.accounting.export"));
        System.out.println(MessageProvider.get("menu.accounting.back"));
    }

    private void handleAdd() {
        String description = reader.readString("prompt.description");
        var type = reader.readMovementType();
        double amount = reader.readPositiveDouble("prompt.amount");
        var dateTime = reader.readDateTime();
        presenter.requestAddAccountingMovement(description, type, amount, dateTime);
    }
}
