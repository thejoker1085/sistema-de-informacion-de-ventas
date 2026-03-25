package co.edu.uptc.interfaces;

import co.edu.uptc.pojo.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface PresenterInterface {

    void setView(ViewInterface view);
    void setModel(ModelInterface model);

    void requestAddPerson(String firstName, String lastName, Gender gender, LocalDate birthDate);
    void requestRemovePerson();
    void requestListPersons();
    void requestExportPersons();

    void requestAddProduct(String description, MeasurementUnit unit, double price);
    void requestRemoveProduct();
    void requestListProducts();
    void requestExportProducts();

    void requestAddAccountingMovement(String description, MovementType type, double amount, LocalDateTime dateTime);
    void requestListAccountingMovements();
    void requestExportAccounting();
}