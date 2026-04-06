package co.edu.uptc.interfaces;

import co.edu.uptc.pojo.Gender;
import co.edu.uptc.pojo.MeasurementUnit;
import co.edu.uptc.pojo.MovementType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface PresenterInterface {
    
    void addPerson(String firstName, String lastName, Gender gender, LocalDate birthDate);
    void removePerson();
    void listPersons();
    void exportPersons();
    
    void addProduct(String description, MeasurementUnit unit, double price);
    void removeProduct();
    void listProducts();
    void exportProducts();
    
    void addAccountingMovement(String description, MovementType type, double amount, LocalDateTime dateTime);
    void listAccountingMovements();
    void exportAccountingMovements();
}