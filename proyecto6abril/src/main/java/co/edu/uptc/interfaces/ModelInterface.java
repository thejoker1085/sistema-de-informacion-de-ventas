package co.edu.uptc.interfaces;

import co.edu.uptc.pojo.AccountingMovement;
import co.edu.uptc.pojo.Gender;
import co.edu.uptc.pojo.MeasurementUnit;
import co.edu.uptc.pojo.MovementType;
import co.edu.uptc.pojo.Person;
import co.edu.uptc.pojo.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ModelInterface {
    void addPerson(String name, String lastName, Gender gender, LocalDate date);
    Person removePerson();
    List<Person> getAllPersons();
    void exportPersonsToCsv(String filePath);
    
    void addProduct(String desc, MeasurementUnit unit, double price);
    Product removeProduct();
    List<Product> getAllProducts();
    void exportProductsToCsv(String filePath);
    
    void addAccountingMovement(String desc, MovementType type, double amount, LocalDateTime date);
    List<AccountingMovement> getAllMovements();
    double getAccountingBalance();
    void exportAccountingToCsv(String filePath);
    
    void loadAccountingData();
}