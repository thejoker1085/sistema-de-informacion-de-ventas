package co.edu.uptc.model.service;

import co.edu.uptc.model.StrategyBasedCollectionManager;
import co.edu.uptc.model.persistence.CsvFormatter;
import co.edu.uptc.model.persistence.CsvWriter;
import co.edu.uptc.model.validation.Validator;
import co.edu.uptc.pojo.MeasurementUnit;
import co.edu.uptc.pojo.Product;

import java.util.List;

public class ProductService {

    private final StrategyBasedCollectionManager<Product> manager;
    private final Validator<Product> validator;
    private final CsvWriter csvWriter;
    private int currentId = 1;

    public ProductService(StrategyBasedCollectionManager<Product> manager, Validator<Product> validator, CsvWriter csvWriter) {
        this.manager = manager;
        this.validator = validator;
        this.csvWriter = csvWriter;
    }

    public void pushProductToStack(String description, MeasurementUnit unit, double price) {
        String formattedDesc = description.toUpperCase();
        Product product = new Product(currentId++, formattedDesc, unit, price);
        validator.validate(product);
        manager.insertUsingStrategy(product);
    }

    public Product popProduct() {
        Product removed = manager.removeUsingStrategy();
        if (removed != null) {
            recalculateIds();
        }
        return removed;
    }

    public List<Product> retrieveAllProducts() {
        return manager.getAll();
    }

    public void exportToCsv(String filePath) {
        String csvContent = CsvFormatter.formatProducts(manager.getAll());
        csvWriter.write(filePath, csvContent);
    }
    
    private void recalculateIds() {
        List<Product> products = manager.getAll();
        manager.clear();
        int newId = 1;
        for (Product p : products) {
            Product newProduct = new Product(newId++, p.getDescription(), p.getUnit(), p.getPrice());
            manager.insertUsingStrategy(newProduct);
        }
        currentId = newId;
    }
}