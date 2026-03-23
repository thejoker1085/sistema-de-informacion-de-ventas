package co.edu.uptc.interfaces;

import co.edu.uptc.pojo.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ModelInterface {

    void addPerson(String firstName, String lastName, Gender gender, LocalDate birthDate);
    Person removePerson();
    List<Person> getAllPersons();
    void exportPersonsToCsv();

    void addProduct(String description, MeasurementUnit unit, double price);
    Product removeProduct();
    List<Product> getAllProducts();
    void exportProductsToCsv();

    void addAccountingMovement(String description, MovementType type, double amount, LocalDateTime dateTime);
    AccountingMovement removeAccountingMovement();
    List<AccountingMovement> getAllAccountingMovements();
    double getAccountingBalance();
    void exportAccountingToCsv();
}