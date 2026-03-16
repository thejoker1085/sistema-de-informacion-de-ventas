package co.edu.uptc.pojo;

import java.util.Date;

public class Accounting {
    private String description;
    private String movement;
    private double valueMovement;
    private Date date;

    public Accounting(String description, String movement, double valueMovement, Date date) {
        this.description = description;
        this.movement = movement;
        this.valueMovement = valueMovement;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

    public double getValueMovement() {
        return valueMovement;
    }

    public void setValueMovement(double valueMovement) {
        this.valueMovement = valueMovement;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
