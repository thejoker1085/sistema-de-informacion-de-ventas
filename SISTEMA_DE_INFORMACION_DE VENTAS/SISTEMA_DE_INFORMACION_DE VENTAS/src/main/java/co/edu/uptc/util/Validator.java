package co.edu.uptc.util;

import co.edu.uptc.config.ConfigLoader;

public class Validator {

    private static final int NAME_MIN = ConfigLoader.getInt("person.name.min");
    private static final int NAME_MAX = ConfigLoader.getInt("person.name.max");
    private static final int LASTNAME_MIN = ConfigLoader.getInt("person.lastname.min");
    private static final int LASTNAME_MAX = ConfigLoader.getInt("person.lastname.max");
    private static final double PRICE_MAX = ConfigLoader.getDouble("product.price.max");

    public static boolean isValidPersonName(String name) {
        return name != null && name.length() >= NAME_MIN && name.length() <= NAME_MAX;
    }

    public static boolean isValidPersonLastName(String lastName) {
        return lastName != null && lastName.length() >= LASTNAME_MIN && lastName.length() <= LASTNAME_MAX;
    }

    public static boolean isValidPrice(double price) {
        return price > 0 && price <= PRICE_MAX;
    }

    public static boolean isPositiveAmount(double amount) {
        return amount > 0;
    }

    public static boolean isNotBlank(String value) {
        return value != null && !value.isBlank();
    }

    private Validator() {}
}
