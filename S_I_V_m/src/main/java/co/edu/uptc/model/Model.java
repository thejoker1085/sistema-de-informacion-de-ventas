package co.edu.uptc.model;

import co.edu.uptc.config.ConfigLoader;
import co.edu.uptc.interfaces.ModelInterface;
import co.edu.uptc.model.persistence.CsvWriter;
import co.edu.uptc.model.service.AccountingService;
import co.edu.uptc.model.service.PersonService;
import co.edu.uptc.model.service.ProductService;
import co.edu.uptc.pojo.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Model implements ModelInterface {

    private final ValidationService validator = new ValidationService();
    private final CsvWriter csvWriter = new CsvWriter(ConfigLoader.get("data.path"));
    private final PersonService personService = new PersonService(validator, csvWriter);
    private final ProductService productService = new ProductService(validator, csvWriter);
    private final AccountingService accountingService = new AccountingService(validator, csvWriter);

    @Override
    public void addPerson(String firstName, String lastName, Gender gender, LocalDate birthDate) {
        personService.add(firstName, lastName, gender, birthDate);
    }

    @Override
    public Person removePerson() {
        return personService.removeFirst();
    }

    @Override
    public List<Person> getAllPersons() {
        return personService.getAll();
    }

    @Override
    public void exportPersonsToCsv() {
        personService.exportToCsv();
    }

    @Override
    public void addProduct(String description, MeasurementUnit unit, double price) {
        productService.add(description, unit, price);
    }

    @Override
    public Product removeProduct() {
        return productService.removeFirst();
    }

    @Override
    public List<Product> getAllProducts() {
        return productService.getAll();
    }

    @Override
    public void exportProductsToCsv() {
        productService.exportToCsv();
    }

    @Override
    public void addAccountingMovement(String description, MovementType type, double amount, LocalDateTime dateTime) {
        accountingService.add(description, type, amount, dateTime);
    }

    @Override
    public List<AccountingMovement> getAllAccountingMovements() {
        return accountingService.getAll();
    }

    @Override
    public double getAccountingBalance() {
        return accountingService.calculateBalance();
    }

    @Override
    public void exportAccountingToCsv() {
        accountingService.exportToCsv();
    }
}