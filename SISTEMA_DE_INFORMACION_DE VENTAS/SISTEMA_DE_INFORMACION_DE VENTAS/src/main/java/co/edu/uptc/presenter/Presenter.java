package co.edu.uptc.presenter;

import co.edu.uptc.interfaces.ModelInterface;
import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.interfaces.ViewInterface;
import co.edu.uptc.pojo.*;
import co.edu.uptc.util.TablePrinter;
import co.edu.uptc.util.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Presenter implements PresenterInterface {

    private ViewInterface view;
    private ModelInterface model;

    @Override
    public void setView(ViewInterface view) { this.view = view; }

    @Override
    public void setModel(ModelInterface model) { this.model = model; }

    @Override
    public void requestAddPerson(String firstName, String lastName, Gender gender, LocalDate birthDate) {
        if (!Validator.isValidPersonName(firstName)) { view.showError("Invalid first name."); return; }
        if (!Validator.isValidPersonLastName(lastName)) { view.showError("Invalid last name."); return; }
        model.addPerson(firstName, lastName, gender, birthDate);
        view.showSuccess("Person added successfully.");
    }

    @Override
    public void requestListPersons() {
        List<Person> persons = model.getAllPersons();
        TablePrinter.printHeader(String.format("%-4s %-20s %-20s %-10s %s", "ID", "FIRST NAME", "LAST NAME", "GENDER", "BIRTH DATE"));
        TablePrinter.printRows(persons);
    }

    @Override
    public void requestExportPersons() {
        model.exportPersonsToCsv();
    }

    @Override
    public void requestAddProduct(String description, MeasurementUnit unit, double price) {
        if (!Validator.isNotBlank(description)) { view.showError("Description cannot be blank."); return; }
        if (!Validator.isValidPrice(price)) { view.showError("Invalid price."); return; }
        model.addProduct(description, unit, price);
        view.showSuccess("Product added successfully.");
    }

    @Override
    public void requestListProducts() {
        List<Product> products = model.getAllProducts();
        TablePrinter.printHeader(String.format("%-4s %-30s %-10s %s", "ID", "DESCRIPTION", "UNIT", "PRICE"));
        TablePrinter.printRows(products);
    }

    @Override
    public void requestExportProducts() {
        model.exportProductsToCsv();
    }

    @Override
    public void requestAddAccountingMovement(String description, MovementType type, double amount, LocalDateTime dateTime) {
        if (!Validator.isNotBlank(description)) { view.showError("Description cannot be blank."); return; }
        if (!Validator.isPositiveAmount(amount)) { view.showError("Amount must be positive."); return; }
        model.addAccountingMovement(description, type, amount, dateTime);
        view.showSuccess("Movement added successfully.");
    }

    @Override
    public void requestListAccountingMovements() {
        List<AccountingMovement> movements = model.getAllAccountingMovements();
        TablePrinter.printHeader(String.format("%-4s %-30s %-8s %10s  %s", "ID", "DESCRIPTION", "TYPE", "AMOUNT", "DATE TIME"));
        TablePrinter.printRows(movements);
        TablePrinter.printTotal("Balance", model.getTotalBalance());
    }

    @Override
    public void requestExportAccounting() {
        model.exportAccountingToCsv();
    }
}
