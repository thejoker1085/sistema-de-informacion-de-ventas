package co.edu.uptc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import co.edu.uptc.config.BusinessServiceFactory;
import co.edu.uptc.interfaces.ModelInterface;
import co.edu.uptc.model.service.AccountingService;
import co.edu.uptc.model.service.PersonService;
import co.edu.uptc.model.service.ProductService;
import co.edu.uptc.pojo.AccountingMovement;
import co.edu.uptc.pojo.Gender;
import co.edu.uptc.pojo.MeasurementUnit;
import co.edu.uptc.pojo.MovementType;
import co.edu.uptc.pojo.Person;
import co.edu.uptc.pojo.Product;

public class BusinessOperationOrchestrator implements ModelInterface {

    private final PersonService personService;
    private final ProductService productService;
    private final AccountingService accountingService;

    public BusinessOperationOrchestrator(BusinessServiceFactory factory) {
        this.personService = factory.createPersonService();
        this.productService = factory.createProductService();
        this.accountingService = factory.createAccountingService();
    }

    @Override
    public void addPerson(String firstName, String lastName, Gender gender, LocalDate birthDate) {
        personService.registerPersonInQueue(firstName, lastName, gender, birthDate);
    }

    @Override
    public Person removePerson() {
        return personService.dequeueFirstPerson();
    }

    @Override
    public List<Person> getAllPersons() {
        return personService.retrieveAllPersons();
    }

    @Override
    public void exportPersonsToCsv(String filePath) {
        personService.exportToCsv(filePath);
    }

    @Override
    public void addProduct(String description, MeasurementUnit unit, double price) {
        productService.pushProductToStack(description, unit, price);
    }

    @Override
    public Product removeProduct() {
        return productService.popProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        return productService.retrieveAllProducts();
    }

    @Override
    public void exportProductsToCsv(String filePath) {
        productService.exportToCsv(filePath);
    }

    @Override
    public void addAccountingMovement(String description, MovementType type, double amount, LocalDateTime dateTime) {
        accountingService.recordMovement(description, type, amount, dateTime);
    }

    @Override
    public List<AccountingMovement> getAllMovements() {
        return accountingService.retrieveAllMovements();
    }

    @Override
    public double getAccountingBalance() {
        return accountingService.calculateBalance();
    }

    @Override
    public void exportAccountingToCsv(String filePath) {
        accountingService.exportToCsv(filePath);
    }
    
    @Override
    public void loadAccountingData() {
        accountingService.loadFromCsv("accounting.csv"); 
    }
}