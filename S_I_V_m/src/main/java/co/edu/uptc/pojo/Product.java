package co.edu.uptc.pojo;

import co.edu.uptc.interfaces.Csvable;

public class Product implements Csvable {

    private final int id;
    private final String description;
    private final MeasurementUnit unit;
    private final double price;

    public Product(int id, String description, MeasurementUnit unit, double price) {
        this.id = id;
        this.description = description;
        this.unit = unit;
        this.price = price;
    }

    public int getId() { 
        return id; 
    }
    public String getDescription() { 
        return description; 
    }
    public MeasurementUnit getUnit() { 
        return unit; 
    }
    public double getPrice() { 
        return price; 
    }

    @Override
    public String toCsvRow() {
        return id + "," + description + "," + unit + "," + price;
    }
}