package co.edu.uptc.model;

import co.edu.uptc.config.ConfigLoader;

public class ValidationService {

    private static final int NAME_MIN = ConfigLoader.getInt("person.name.min");
    private static final int NAME_MAX = ConfigLoader.getInt("person.name.max");
    private static final int LASTNAME_MIN = ConfigLoader.getInt("person.lastname.min");
    private static final int LASTNAME_MAX = ConfigLoader.getInt("person.lastname.max");
    private static final double PRICE_MAX = ConfigLoader.getDouble("product.price.max");

    public boolean isValidFirstName(String value) {
        return value != null && value.length() >= NAME_MIN && value.length() <= NAME_MAX;
    }

    public boolean isValidLastName(String value) {
        return value != null && value.length() >= LASTNAME_MIN && value.length() <= LASTNAME_MAX;
    }

    public boolean isValidPrice(double value) {
        return value > 0 && value <= PRICE_MAX;
    }

    public boolean isPositiveAmount(double value) {
        return value > 0;
    }

    public boolean isNotBlank(String value) {
        return value != null && !value.isBlank();
    }

    public int getNameMin() { return NAME_MIN; }
    public int getNameMax() { return NAME_MAX; }
    public int getLastNameMin() { return LASTNAME_MIN; }
    public int getLastNameMax() { return LASTNAME_MAX; }
    public double getPriceMax() { return PRICE_MAX; }
}
