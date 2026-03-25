package co.edu.uptc.presenter;

import co.edu.uptc.config.ConfigLoader;
import co.edu.uptc.config.MessageProvider;
import co.edu.uptc.interfaces.ModelInterface;
import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.interfaces.ViewInterface;
import co.edu.uptc.pojo.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Presenter implements PresenterInterface {

    private ViewInterface view;
    private ModelInterface model;

    @Override
    public void setView(ViewInterface view) { this.view = view; }

    @Override
    public void setModel(ModelInterface model) { this.model = model; }

    @Override
    public void requestAddPerson(String firstName, String lastName, Gender gender, LocalDate birthDate) {
        try {
            model.addPerson(firstName, lastName, gender, birthDate);
            view.showSuccess(MessageProvider.get("msg.success.added"));
        } catch (IllegalArgumentException e) {
            view.showError(resolveMessage(e.getMessage()));
        }
    }

    @Override
    public void requestRemovePerson() {
        try {
            Person removed = model.removePerson();
            view.showSuccess(MessageProvider.get("msg.success.removed") + ": " + formatPerson(removed));
        } catch (IllegalStateException e) {
            view.showError(resolveMessage(e.getMessage()));
        }
    }

    private String formatPerson(Person p) {
        return p.getFirstName() + " " + p.getLastName() + " (" + p.getGender().getDisplayValue() + ")";
    }

    @Override
    public void requestListPersons() {
        view.displayPersons(model.getAllPersons());
    }

    @Override
    public void requestExportPersons() {
        executeExport("persons.file", () -> model.exportPersonsToCsv());
    }

    @Override
    public void requestAddProduct(String description, MeasurementUnit unit, double price) {
        try {
            model.addProduct(description, unit, price);
            view.showSuccess(MessageProvider.get("msg.success.added"));
        } catch (IllegalArgumentException e) {
            view.showError(resolveMessage(e.getMessage()));
        }
    }

    @Override
    public void requestRemoveProduct() {
        try {
            Product removed = model.removeProduct();
            view.showSuccess(MessageProvider.get("msg.success.removed") + ": " + removed.getDescription());
        } catch (IllegalStateException e) {
            view.showError(resolveMessage(e.getMessage()));
        }
    }

    @Override
    public void requestListProducts() {
        view.displayProducts(model.getAllProducts());
    }

    @Override
    public void requestExportProducts() {
        executeExport("products.file", () -> model.exportProductsToCsv());
    }

    @Override
    public void requestAddAccountingMovement(String description, MovementType type, double amount, LocalDateTime dateTime) {
        try {
            model.addAccountingMovement(description, type, amount, dateTime);
            view.showSuccess(MessageProvider.get("msg.success.added"));
        } catch (IllegalArgumentException e) {
            view.showError(resolveMessage(e.getMessage()));
        }
    }

    @Override
    public void requestListAccountingMovements() {
        view.displayAccountingMovements(model.getAllAccountingMovements(), model.getAccountingBalance());
    }

    @Override
    public void requestExportAccounting() {
        executeExport("accounting.file", () -> model.exportAccountingToCsv());
    }

    private void executeExport(String fileKey, Runnable exporter) {
        try {
            exporter.run();
            view.showSuccess(MessageProvider.get("msg.success.exported") + ": " + ConfigLoader.get(fileKey));
        } catch (Exception e) {
            view.showError(MessageProvider.get("msg.error.export") + ": " + e.getMessage());
        }
    }

    private String resolveMessage(String rawKey) {
        if (rawKey == null) return MessageProvider.get("msg.error.blank");
        String[] parts = rawKey.split(":");
        if (parts.length == 3) return MessageProvider.get(parts[0], parts[1], parts[2]);
        if (parts.length == 2) return MessageProvider.get(parts[0], parts[1]);
        return MessageProvider.get(parts[0]);
    }
}