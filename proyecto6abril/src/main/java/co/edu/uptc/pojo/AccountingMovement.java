package co.edu.uptc.pojo;

import co.edu.uptc.interfaces.Csvable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AccountingMovement implements Csvable {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final String description;
    private final MovementType type;
    private final double amount;
    private final LocalDateTime dateTime;

    public AccountingMovement(String description, MovementType type, double amount, LocalDateTime dateTime) {
        this.description = description;
        this.type = type;
        this.amount = amount;
        this.dateTime = dateTime;
    }
    public String getDescription() { 
        return description; 
    }
    public MovementType getType() { 
        return type; 
    }
    public double getAmount() { 
        return amount; 
    }
    public LocalDateTime getDateTime() { 
        return dateTime; 
    }
    public String getFormattedDateTime() { 
        return dateTime.format(FORMATTER); 
    }

    @Override
    public String toCsvRow() {
        return description + "," + type + "," + amount + "," + getFormattedDateTime();
    }
}