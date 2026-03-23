package co.edu.uptc.presenter;

import co.edu.uptc.config.ConfigLoader;
import co.edu.uptc.config.MessageProvider;
import co.edu.uptc.interfaces.ModelInterface;
import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.interfaces.ViewInterface;
import co.edu.uptc.model.ValidationService;
import co.edu.uptc.pojo.*;
import co.edu.uptc.util.TablePrinter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Presenter implements PresenterInterface {

    private ViewInterface view;
    private ModelInterface model;
    private final ValidationService validator = new ValidationService();

    @Override
    public void setView(ViewInterface view) { this.view = view; }

    @Override
    public void setModel(ModelInterface model) { this.model = model; }

    @Override
    public void requestAddPerson(String firstName, String lastName, Gender gender, LocalDate birthDate) {
        try {
            if (!validator.isValidFirstName(firstName)) {
                String msg = MessageProvider.get("msg.error.name", validator.getNameMin(), validator.getNameMax());
                view.showError(msg);
                return;
            }
            if (!validator.isValidLastName(lastName)) {
                String msg = MessageProvider.get("msg.error.lastname", validator.getLastNameMin(), validator.getLastNameMax());
                view.showError(msg);
                return;
            }
            model.addPerson(firstName, lastName, gender, birthDate);
            view.showSuccess(MessageProvider.get("msg.success.added"));
        } catch (Exception e) {
            view.showError(e.getMessage());
        }
    }

    @Override
    public void requestRemovePerson() {
        try {
            Person removed = model.removePerson();
            String msg = MessageProvider.get("msg.success.removed") + ": " + formatPerson(removed);
            view.showSuccess(msg);
        } catch (Exception e) {
            view.showError(e.getMessage());
        }
    }

    private String formatPerson(Person p) {
        return p.getFirstName() + " " + p.getLastName() + " (" + p.getGender().getDisplayValue() + ")";
    }

    @Override
    public void requestListPersons() {
        TablePrinter.printPersons(model.getAllPersons());
    }

    @Override
    public void requestExportPersons() {
        executeExport("persons", () -> model.exportPersonsToCsv());
    }

    @Override
    public void requestAddProduct(String description, MeasurementUnit unit, double price) {
        try {
            if (!validator.isNotBlank(description)) {
                view.showError(MessageProvider.get("msg.error.blank"));
                return;
            }
            if (!validator.isValidPrice(price)) {
                String msg = MessageProvider.get("msg.error.price", validator.getPriceMax());
                view.showError(msg);
                return;
            }
            model.addProduct(description, unit, price);
            view.showSuccess(MessageProvider.get("msg.success.added"));
        } catch (Exception e) {
            view.showError(e.getMessage());
        }
    }

    @Override
    public void requestRemoveProduct() {
        try {
            Product removed = model.removeProduct();
            String msg = MessageProvider.get("msg.success.removed") + ": " + removed.getDescription() + " (" + removed.getUnit() + ")";
            view.showSuccess(msg);
        } catch (Exception e) {
            view.showError(e.getMessage());
        }
    }

    @Override
    public void requestListProducts() {
        TablePrinter.printProducts(model.getAllProducts());
    }

    @Override
    public void requestExportProducts() {
        executeExport("products", () -> model.exportProductsToCsv());
    }

    @Override
    public void requestAddAccountingMovement(String description, MovementType type, double amount, LocalDateTime dateTime) {
        try {
            if (!validator.isNotBlank(description)) {
                view.showError(MessageProvider.get("msg.error.blank"));
                return;
            }
            if (!validator.isPositiveAmount(amount)) {
                view.showError(MessageProvider.get("msg.error.amount"));
                return;
            }
            model.addAccountingMovement(description, type, amount, dateTime);
            view.showSuccess(MessageProvider.get("msg.success.added"));
        } catch (Exception e) {
            view.showError(e.getMessage());
        }
    }

    @Override
    public void requestRemoveAccountingMovement() {
        try {
            AccountingMovement removed = model.removeAccountingMovement();
            String msg = MessageProvider.get("msg.success.removed") + ": " + removed.getDescription() + " (" + removed.getAmount() + ")";
            view.showSuccess(msg);
        } catch (Exception e) {
            view.showError(e.getMessage());
        }
    }

    @Override
    public void requestListAccountingMovements() {
        TablePrinter.printAccountingMovements(model.getAllAccountingMovements(), model.getAccountingBalance());
    }

    @Override
    public void requestExportAccounting() {
        executeExport("accounting", () -> model.exportAccountingToCsv());
    }

    private void executeExport(String type, Runnable exporter) {
        try {
            exporter.run();
            String filepath = ConfigLoader.get(type + ".file");
            String msg = MessageProvider.get("msg.success.exported") + ": " + filepath;
            view.showSuccess(msg);
        } catch (Exception e) {
            view.showError(MessageProvider.get("msg.error.export") + ": " + e.getMessage());
        }
    }
}
