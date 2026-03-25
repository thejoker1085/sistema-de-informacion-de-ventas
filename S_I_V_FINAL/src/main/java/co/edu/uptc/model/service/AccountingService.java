package co.edu.uptc.model.service;

import co.edu.uptc.config.ConfigLoader;
import co.edu.uptc.model.ValidationService;
import co.edu.uptc.model.persistence.CsvFormatter;
import co.edu.uptc.model.persistence.CsvWriter;
import co.edu.uptc.model.structures.DoublyLinkedList;
import co.edu.uptc.pojo.AccountingMovement;
import co.edu.uptc.pojo.MovementType;

import java.time.LocalDateTime;
import java.util.List;

public class AccountingService {

    private final DoublyLinkedList<AccountingMovement> repository = new DoublyLinkedList<>();
    private final ValidationService validator;
    private final CsvWriter csvWriter;

    public AccountingService(ValidationService validator, CsvWriter csvWriter) {
        this.validator = validator;
        this.csvWriter = csvWriter;
    }

    public void add(String description, MovementType type, double amount, LocalDateTime dateTime) {
        validateMovement(description, amount);
        repository.addFront(new AccountingMovement(repository.generateNextId(), description, type, amount, dateTime));
    }

    private void validateMovement(String description, double amount) {
        if (!validator.isNotBlank(description))
            throw new IllegalArgumentException("msg.error.blank");
        if (!validator.isPositiveAmount(amount))
            throw new IllegalArgumentException("msg.error.amount");
    }

    public List<AccountingMovement> getAll() {
        return repository.getAll();
    }

    public double calculateBalance() {
        return repository.getAll().stream()
                .mapToDouble(m -> m.getType() == MovementType.INCOME ? m.getAmount() : -m.getAmount())
                .sum();
    }

    public void exportToCsv() {
        csvWriter.write(ConfigLoader.get("accounting.file"), CsvFormatter.formatAccountingMovements(repository.getAll()));
    }
}