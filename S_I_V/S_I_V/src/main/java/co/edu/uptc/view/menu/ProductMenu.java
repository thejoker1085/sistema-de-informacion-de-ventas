package co.edu.uptc.view.menu;

import co.edu.uptc.config.MessageProvider;
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
        boolean active = true;
        while (active) {
            printMenu();
            int option = reader.readOption(1, 5);
            switch (option) {
                case 1 -> handleAdd();
                case 2 -> presenter.requestRemoveProduct();
                case 3 -> presenter.requestListProducts();
                case 4 -> presenter.requestExportProducts();
                case 5 -> active = false;
            }
        }
    }

    private void printMenu() {
        System.out.println("\n" + MessageProvider.get("menu.products.title"));
        System.out.println(MessageProvider.get("menu.products.add"));
        System.out.println(MessageProvider.get("menu.products.remove"));
        System.out.println(MessageProvider.get("menu.products.list"));
        System.out.println(MessageProvider.get("menu.products.export"));
        System.out.println(MessageProvider.get("menu.products.back"));
    }

    private void handleAdd() {
        String description = reader.readString("prompt.description");
        var unit = reader.readMeasurementUnit();
        double price = reader.readPositiveDouble("prompt.price");
        presenter.requestAddProduct(description, unit, price);
    }
}
