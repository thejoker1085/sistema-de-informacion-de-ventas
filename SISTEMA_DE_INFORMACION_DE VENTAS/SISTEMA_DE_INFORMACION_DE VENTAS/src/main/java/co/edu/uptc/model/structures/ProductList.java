package co.edu.uptc.model.structures;

import co.edu.uptc.pojo.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductList {

    private final List<Product> list = new ArrayList<>();
    private int nextId = 1;

    public void add(Product product) {
        list.add(product);
    }

    public boolean remove(int id) {
        return list.removeIf(p -> p.getId() == id);
    }

    public List<Product> getAll() {
        return new ArrayList<>(list);
    }

    public int generateNextId() {
        return nextId++;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }
}
