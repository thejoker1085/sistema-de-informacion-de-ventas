package co.edu.uptc.util;

import co.edu.uptc.pojo.AccountingMovement;
import co.edu.uptc.pojo.Person;
import co.edu.uptc.pojo.Product;

import java.util.List;
import java.util.stream.Collectors;

public class CsvFormatter {

    private static final String PERSONS_HEADER = "id,firstName,lastName,gender,birthDate";
    private static final String PRODUCTS_HEADER = "id,description,unit,price";
    private static final String ACCOUNTING_HEADER = "id,description,type,amount,dateTime";

    public static String formatPersons(List<Person> persons) {
        return PERSONS_HEADER + "\n" + persons.stream()
                .map(Person::toCsvRow)
                .collect(Collectors.joining("\n"));
    }

    public static String formatProducts(List<Product> products) {
        return PRODUCTS_HEADER + "\n" + products.stream()
                .map(Product::toCsvRow)
                .collect(Collectors.joining("\n"));
    }

    public static String formatAccountingMovements(List<AccountingMovement> movements) {
        return ACCOUNTING_HEADER + "\n" + movements.stream()
                .map(AccountingMovement::toCsvRow)
                .collect(Collectors.joining("\n"));
    }

    private CsvFormatter() {}
}
