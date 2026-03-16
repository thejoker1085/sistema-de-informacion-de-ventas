package co.edu.uptc.pojo;

public class Product {

    private int id;
    private String description;
    private MeasurementUnit unit;
    private double price;

    public Product(int id, String description, MeasurementUnit unit, double price) {
        this.id = id;
        this.description = description.toUpperCase();
        this.unit = unit;
        this.price = price;
    }

    public int getId() { return id; }
    public String getDescription() { return description; }
    public MeasurementUnit getUnit() { return unit; }
    public double getPrice() { return price; }

    public String toCsvRow() {
        return id + "," + description + "," + unit + "," + price;
    }

    @Override
    public String toString() {
        return String.format("%-4d %-30s %-10s %.2f", id, description, unit, price);
    }
}
