package co.edu.uptc.view.menu;

import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.view.InputReader;

import java.time.LocalDateTime;

public class AccountingMenu {

    private final PresenterInterface presenter;
    private final InputReader reader;

    public AccountingMenu(PresenterInterface presenter, InputReader reader) {
        this.presenter = presenter;
        this.reader = reader;
    }

    public void show() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Accounting Menu ---");
            System.out.println("  1. Add movement");
            System.out.println("  2. List movements");
            System.out.println("  3. Export to CSV");
            System.out.println("  4. Back");
            int option = reader.readInt("  Select", 1, 4);
            switch (option) {
                case 1 -> handleAdd();
                case 2 -> presenter.requestListAccountingMovements();
                case 3 -> presenter.requestExportAccounting();
                case 4 -> running = false;
            }
        }
    }

    private void handleAdd() {
        String description = reader.readString("  Description");
        var type     = reader.readMovementType();
        double amount = reader.readPositiveDouble("  Amount");
        LocalDateTime dateTime = reader.readDateTime("  Date and time");
        presenter.requestAddAccountingMovement(description, type, amount, dateTime);
    }
}
