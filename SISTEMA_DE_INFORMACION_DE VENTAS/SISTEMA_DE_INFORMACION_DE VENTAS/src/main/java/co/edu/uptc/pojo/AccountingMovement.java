package co.edu.uptc.pojo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AccountingMovement {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private int id;
    private String description;
    private MovementType type;
    private double amount;
    private LocalDateTime dateTime;

    public AccountingMovement(int id, String description, MovementType type, double amount, LocalDateTime dateTime) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    public int getId() { return id; }
    public String getDescription() { return description; }
    public MovementType getType() { return type; }
    public double getAmount() { return amount; }
    public LocalDateTime getDateTime() { return dateTime; }

    public String toCsvRow() {
        return id + "," + description + "," + type + "," + amount + "," + dateTime.format(FORMATTER);
    }

    @Override
    public String toString() {
        return String.format("%-4d %-30s %-8s %10.2f  %s", id, description, type, amount, dateTime.format(FORMATTER));
    }
}
