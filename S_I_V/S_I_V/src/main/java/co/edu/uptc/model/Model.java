package co.edu.uptc.model;

import co.edu.uptc.config.ConfigLoader;
import co.edu.uptc.interfaces.ModelInterface;
import co.edu.uptc.model.structures.GenericRepository;
import co.edu.uptc.model.persistence.CsvWriter;
import co.edu.uptc.model.persistence.CsvFormatter;
import co.edu.uptc.pojo.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Model implements ModelInterface {

    private final GenericRepository<Person> personRepository = new GenericRepository<>();
    private final GenericRepository<Product> productRepository = new GenericRepository<>();
    private final GenericRepository<AccountingMovement> movementRepository = new GenericRepository<>();
    private final ValidationService validator = new ValidationService();
    private final CsvWriter csvWriter = new CsvWriter(ConfigLoader.get("data.path"));

    @Override
    public void addPerson(String firstName, String lastName, Gender gender, LocalDate birthDate) {
        validatePerson(firstName, lastName);
        personRepository.add(new Person(personRepository.generateNextId(), firstName, lastName, gender, birthDate));
    }

    @Override
    public Person removePerson() {
        return personRepository.removeFirst();
    }

    private void validatePerson(String firstName, String lastName) {
        if (!validator.isValidFirstName(firstName)) {
            throw new IllegalArgumentException("Nombre inválido");
        }
        if (!validator.isValidLastName(lastName)) {
            throw new IllegalArgumentException("Apellido inválido");
        }
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.getAll();
    }

    @Override
    public void exportPersonsToCsv() {
        String path = ConfigLoader.get("persons.file");
        csvWriter.write(path, CsvFormatter.formatPersons(personRepository.getAll()));
    }

    @Override
    public void addProduct(String description, MeasurementUnit unit, double price) {
        validateProduct(description, price);
        String formattedDescription = formatDescription(description);
        productRepository.add(new Product(productRepository.generateNextId(), formattedDescription, unit, price));
    }

    @Override
    public Product removeProduct() {
        return productRepository.removeFirst();
    }

    private void validateProduct(String description, double price) {
        if (!validator.isNotBlank(description)) {
            throw new IllegalArgumentException("Descripción vacía");
        }
        if (!validator.isValidPrice(price)) {
            throw new IllegalArgumentException("Precio inválido");
        }
    }

    private String formatDescription(String raw) {
        String mode = ConfigLoader.get("product.description.mode");
        if ("TITLE_CASE".equalsIgnoreCase(mode)) {
            return toTitleCase(raw);
        }
        return raw.toUpperCase();
    }

    private String toTitleCase(String text) {
        String[] words = text.toLowerCase().split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                sb.append(Character.toUpperCase(word.charAt(0)))
                  .append(word.substring(1)).append(" ");
            }
        }
        return sb.toString().trim();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAll();
    }

    @Override
    public void exportProductsToCsv() {
        String path = ConfigLoader.get("products.file");
        csvWriter.write(path, CsvFormatter.formatProducts(productRepository.getAll()));
    }

    @Override
    public void addAccountingMovement(String description, MovementType type, double amount, LocalDateTime dateTime) {
        validateAccountingMovement(description, amount);
        movementRepository.add(new AccountingMovement(movementRepository.generateNextId(), description, type, amount, dateTime));
    }

    @Override
    public AccountingMovement removeAccountingMovement() {
        return movementRepository.removeFirst();
    }

    private void validateAccountingMovement(String description, double amount) {
        if (!validator.isNotBlank(description)) {
            throw new IllegalArgumentException("Descripción vacía");
        }
        if (!validator.isPositiveAmount(amount)) {
            throw new IllegalArgumentException("Monto inválido");
        }
    }

    @Override
    public List<AccountingMovement> getAllAccountingMovements() {
        return movementRepository.getAllReverse();
    }

    @Override
    public double getAccountingBalance() {
        return movementRepository.getAllReverse().stream()
                .mapToDouble(m -> m.getType() == MovementType.INCOME ? m.getAmount() : -m.getAmount())
                .sum();
    }

    @Override
    public void exportAccountingToCsv() {
        String path = ConfigLoader.get("accounting.file");
        csvWriter.write(path, CsvFormatter.formatAccountingMovements(movementRepository.getAllReverse()));
    }
}