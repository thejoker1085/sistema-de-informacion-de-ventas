package co.edu.uptc.model.service;

import co.edu.uptc.config.ConfigLoader;
import co.edu.uptc.model.ValidationService;
import co.edu.uptc.model.persistence.CsvFormatter;
import co.edu.uptc.model.persistence.CsvWriter;
import co.edu.uptc.model.structures.DoublyLinkedList;
import co.edu.uptc.pojo.MeasurementUnit;
import co.edu.uptc.pojo.Product;

import java.util.List;

public class ProductService {

    private final DoublyLinkedList<Product> repository = new DoublyLinkedList<>();
    private final ValidationService validator;
    private final CsvWriter csvWriter;

    public ProductService(ValidationService validator, CsvWriter csvWriter) {
        this.validator = validator;
        this.csvWriter = csvWriter;
    }

    public void add(String description, MeasurementUnit unit, double price) {
        validateProduct(description, price);
        repository.add(new Product(repository.generateNextId(), formatDescription(description), unit, price));
    }

    private void validateProduct(String description, double price) {
        if (!validator.isNotBlank(description))
            throw new IllegalArgumentException("msg.error.blank");
        if (!validator.isValidPrice(price))
            throw new IllegalArgumentException("msg.error.price:" + validator.getPriceMax());
    }

    public String formatDescription(String raw) {
        String mode = ConfigLoader.get("product.description.mode");
        return "TITLE_CASE".equalsIgnoreCase(mode) ? toTitleCase(raw) : raw.toUpperCase();
    }

    private String toTitleCase(String text) {
        String[] words = text.toLowerCase().split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words)
            if (!word.isEmpty()) sb.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
        return sb.toString().trim();
    }

    public Product removeFirst() {
        if (repository.isEmpty()) throw new IllegalStateException("msg.error.empty");
        return repository.removeFirst();
    }

    public List<Product> getAll() {
        return repository.getAll();
    }

    public void exportToCsv() {
        csvWriter.write(ConfigLoader.get("products.file"), CsvFormatter.formatProducts(repository.getAll()));
    }
}