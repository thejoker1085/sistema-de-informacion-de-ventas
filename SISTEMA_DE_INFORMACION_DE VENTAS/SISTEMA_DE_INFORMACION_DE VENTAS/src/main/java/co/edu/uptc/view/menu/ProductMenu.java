package co.edu.uptc.view.menu;

import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.view.InputReader;

public class ProductMenu {

    private final PresenterInterface presenter;
    private final InputReader reader;

    public ProductMenu(PresenterInterface presenter, InputReader reader) {
        this.presenter = presenter;
        this.reader = reader;
    }

    public void show() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Products Menu ---");
            System.out.println("  1. Add product");
            System.out.println("  2. List products");
            System.out.println("  3. Export to CSV");
            System.out.println("  4. Back");
            int option = reader.readInt("  Select", 1, 4);
            switch (option) {
                case 1 -> handleAdd();
                case 2 -> presenter.requestListProducts();
                case 3 -> presenter.requestExportProducts();
                case 4 -> running = false;
            }
        }
    }

    private void handleAdd() {
        String description = reader.readString("  Description");
        var unit  = reader.readMeasurementUnit();
        double price = reader.readPositiveDouble("  Price");
        presenter.requestAddProduct(description, unit, price);
    }
}
