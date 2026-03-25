package co.edu.uptc.model.persistence;

import co.edu.uptc.interfaces.Csvable;

import java.util.List;
import java.util.stream.Collectors;

public class CsvFormatter {

    private static final String PERSONS_HEADER = "id,firstName,lastName,gender,birthDate";
    private static final String PRODUCTS_HEADER = "id,description,unit,price";
    private static final String ACCOUNTING_HEADER = "id,description,type,amount,dateTime";

    public static String format(String header, List<? extends Csvable> items) {
        if (items == null || items.isEmpty()) return header;
        return header + "\n" + items.stream()
                .map(Csvable::toCsvRow)
                .collect(Collectors.joining("\n"));
    }

    public static String formatPersons(List<? extends Csvable> persons) {
        return format(PERSONS_HEADER, persons);
    }

    public static String formatProducts(List<? extends Csvable> products) {
        return format(PRODUCTS_HEADER, products);
    }

    public static String formatAccountingMovements(List<? extends Csvable> movements) {
        return format(ACCOUNTING_HEADER, movements);
    }

    private CsvFormatter() {}
}