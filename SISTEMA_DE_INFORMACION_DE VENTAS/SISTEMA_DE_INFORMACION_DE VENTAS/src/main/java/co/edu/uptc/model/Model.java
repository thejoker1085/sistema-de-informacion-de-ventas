package co.edu.uptc.model;

import co.edu.uptc.config.ConfigLoader;
import co.edu.uptc.interfaces.ModelInterface;
import co.edu.uptc.model.structures.AccountingStack;
import co.edu.uptc.model.structures.PersonQueue;
import co.edu.uptc.model.structures.ProductList;
import co.edu.uptc.persistence.CsvWriter;
import co.edu.uptc.pojo.*;
import co.edu.uptc.util.CsvFormatter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Model implements ModelInterface {

    private final PersonQueue personQueue = new PersonQueue();
    private final ProductList productList = new ProductList();
    private final AccountingStack accountingStack = new AccountingStack();

    @Override
    public void addPerson(String firstName, String lastName, Gender gender, LocalDate birthDate) {
        personQueue.enqueue(new Person(personQueue.generateNextId(), firstName, lastName, gender, birthDate));
    }

    @Override
    public List<Person> getAllPersons() {
        return personQueue.getAll();
    }

    @Override
    public void exportPersonsToCsv() {
        CsvWriter.write(ConfigLoader.get("persons.file"), CsvFormatter.formatPersons(personQueue.getAll()));
    }

    @Override
    public void addProduct(String description, MeasurementUnit unit, double price) {
        productList.add(new Product(productList.generateNextId(), description, unit, price));
    }

    @Override
    public List<Product> getAllProducts() {
        return productList.getAll();
    }

    @Override
    public void exportProductsToCsv() {
        CsvWriter.write(ConfigLoader.get("products.file"), CsvFormatter.formatProducts(productList.getAll()));
    }

    @Override
    public void addAccountingMovement(String description, MovementType type, double amount, LocalDateTime dateTime) {
        accountingStack.push(new AccountingMovement(accountingStack.generateNextId(), description, type, amount, dateTime));
    }

    @Override
    public List<AccountingMovement> getAllAccountingMovements() {
        return accountingStack.getAll();
    }

    @Override
    public double getTotalBalance() {
        return accountingStack.calculateTotal();
    }

    @Override
    public void exportAccountingToCsv() {
        CsvWriter.write(ConfigLoader.get("accounting.file"), CsvFormatter.formatAccountingMovements(accountingStack.getAll()));
    }
}
