package co.edu.uptc.presenter;

import co.edu.uptc.interfaces.ModelInterface;
import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.interfaces.ViewInterface;
import co.edu.uptc.pojo.Gender;
import co.edu.uptc.pojo.MeasurementUnit;
import co.edu.uptc.pojo.MovementType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Presenter implements PresenterInterface {

    private final ModelInterface model;
    private final ViewInterface view;

    public Presenter(ModelInterface model, ViewInterface view) {
        this.model = model;
        this.view = view;
        this.model.loadAccountingData();
        loadInitialData();
    }

    private void loadInitialData() {
        listPersons();
        listProducts();
        listAccountingMovements();
    }

    @Override
    public void addPerson(String firstName, String lastName, Gender gender, LocalDate birthDate) {
        try {
            model.addPerson(firstName, lastName, gender, birthDate);
            view.showSuccess("Persona guardada exitosamente.");
            listPersons();
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }

    @Override
    public void removePerson() {
        try {
            co.edu.uptc.pojo.Person removed = model.removePerson();
            if (removed != null) {
                String message = removed.getFirstName() + " " + removed.getLastName() + " fue removido con éxito.";
                view.showSuccess(message);
            }
            listPersons();
        } catch (IllegalArgumentException | IllegalStateException e) {
            view.showError(e.getMessage());
        }
    }

    @Override
    public void listPersons() {
        view.displayPersons(model.getAllPersons());
    }

    @Override
    public void exportPersons() {
        try {
            model.exportPersonsToCsv("persons.csv");
            view.showSuccess("Personas exportadas exitosamente.");
        } catch (Exception e) {
            view.showError("Error al exportar: " + e.getMessage());
        }
    }

    @Override
    public void addProduct(String description, MeasurementUnit unit, double price) {
        try {
            model.addProduct(description, unit, price);
            view.showSuccess("Producto guardado exitosamente.");
            listProducts();
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }

    @Override
    public void removeProduct() {
        try {
            co.edu.uptc.pojo.Product removed = model.removeProduct();
            if (removed != null) {
                String message = removed.getDescription() + " fue retirado con éxito.";
                view.showSuccess(message);
            }
            listProducts();
        } catch (IllegalArgumentException | IllegalStateException e) {
            view.showError(e.getMessage());
        }
    }

    @Override
    public void listProducts() {
        view.displayProducts(model.getAllProducts());
    }

    @Override
    public void exportProducts() {
        try {
            model.exportProductsToCsv("products.csv");
            view.showSuccess("Productos exportados exitosamente.");
        } catch (Exception e) {
            view.showError("Error al exportar: " + e.getMessage());
        }
    }

    @Override
    public void addAccountingMovement(String description, MovementType type, double amount, LocalDateTime dateTime) {
        try {
            model.addAccountingMovement(description, type, amount, dateTime);
            model.exportAccountingToCsv("accounting.csv"); // ← AGREGAR ESTA LÍNEA para guardar automáticamente
            view.showSuccess("Movimiento agregado exitosamente.");
            view.displayAccountingMovements(model.getAllMovements(), model.getAccountingBalance());
        } catch (Exception e) {
            view.showError(e.getMessage());
        }
    }

    @Override
    public void listAccountingMovements() {
        view.displayAccountingMovements(model.getAllMovements(), model.getAccountingBalance());
    }

    @Override
    public void exportAccountingMovements() {
        try {
            model.exportAccountingToCsv("accounting.csv");
            view.showSuccess("Movimientos exportados exitosamente a la carpeta data.");
        } catch (Exception e) {
            view.showError("Error al exportar: " + e.getMessage());
        }
    }
}