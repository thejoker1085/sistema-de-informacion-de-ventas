package co.edu.uptc.model.service;

import co.edu.uptc.model.StrategyBasedCollectionManager;
import co.edu.uptc.model.persistence.CsvFormatter;
import co.edu.uptc.model.persistence.CsvReader;
import co.edu.uptc.model.persistence.CsvWriter;
import co.edu.uptc.model.validation.Validator;
import co.edu.uptc.pojo.AccountingMovement;
import co.edu.uptc.pojo.MovementType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AccountingService {

    private final StrategyBasedCollectionManager<AccountingMovement> manager;
    private final Validator<AccountingMovement> validator;
    private final CsvWriter csvWriter;
    private final CsvReader csvReader;

    public AccountingService(StrategyBasedCollectionManager<AccountingMovement> manager, 
                             Validator<AccountingMovement> validator, 
                             CsvWriter csvWriter, 
                             CsvReader csvReader) {
        this.manager = manager;
        this.validator = validator;
        this.csvWriter = csvWriter;
        this.csvReader = csvReader;
    }

    public void recordMovement(String description, MovementType type, double amount, LocalDateTime dateTime) {
        AccountingMovement movement = new AccountingMovement(description, type, amount, dateTime);
        validator.validate(movement);
        manager.insertUsingStrategy(movement);
    }

    public List<AccountingMovement> retrieveAllMovements() {
        return manager.getAll();
    }

    public double calculateBalance() {
        return manager.getAll().stream()
                .mapToDouble(m -> m.getType() == MovementType.INCOME ? m.getAmount() : -m.getAmount())
                .sum();
    }

    public void exportToCsv(String filePath) {
        String csvContent = CsvFormatter.formatAccountingMovements(manager.getAll());
        csvWriter.write(filePath, csvContent);
    }

    public void loadFromCsv(String fileName) {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            List<AccountingMovement> movements = csvReader.read(fileName, cols ->
                    new AccountingMovement(cols[0], MovementType.valueOf(cols[1]),
                            Double.parseDouble(cols[2]), LocalDateTime.parse(cols[3], fmt)));
            manager.clear();
            movements.forEach(m -> manager.insertUsingStrategy(m));
        } catch (Exception e) {
            System.err.println("Error al cargar: " + e.getMessage());
        }
    }
}