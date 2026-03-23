package co.edu.uptc.model.persistence;

import java.util.List;
import java.util.stream.Collectors;

import co.edu.uptc.interfaces.Csvable;

public class CsvFormatter {

    private static final String PERSONS_HEADER = "id,firstName,lastName,gender,birthDate";
    private static final String PRODUCTS_HEADER = "id,description,unit,price";
    private static final String ACCOUNTING_HEADER = "id,description,type,amount,dateTime";

    public static String format(String header, List<? extends Csvable> items) {
        if (items == null || items.isEmpty()) {
            return header;
        }
        return header + "\n" + items.stream()
                .map(Csvable::toCsvRow)
                .collect(Collectors.joining("\n"));
    }

    public static String formatPersons(List<?> persons) {
        return format(PERSONS_HEADER, (List<? extends Csvable>) persons);
    }

    public static String formatProducts(List<?> products) {
        return format(PRODUCTS_HEADER, (List<? extends Csvable>) products);
    }

    public static String formatAccountingMovements(List<?> movements) {
        return format(ACCOUNTING_HEADER, (List<? extends Csvable>) movements);
    }

    private CsvFormatter() {}
}