package co.edu.uptc.util;

import co.edu.uptc.config.ConfigLoader;
import co.edu.uptc.config.MessageProvider;
import co.edu.uptc.pojo.AccountingMovement;
import co.edu.uptc.pojo.Person;
import co.edu.uptc.pojo.Product;

import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class TablePrinter {

    private static final String LINE = "-".repeat(72);
    private static final int PAGE_SIZE = ConfigLoader.getInt("list.page.size");
    private static final Scanner scanner = new Scanner(System.in);

    private static final String PERSON_FORMAT = "%-4d  %-20s %-20s %-12s %-5d%n";
    private static final String PRODUCT_FORMAT = "%-4d  %-30s %-10s %-12.2f%n";
    private static final String ACCOUNTING_FORMAT = "%-4d  %-28s %-8s %-12.2f %-16s%n";

    private static final String[] PERSON_HEADERS = {
        "header.person.id", "header.person.name", "header.person.lastname",
        "header.person.gender", "header.person.age"
    };
    
    private static final String[] PRODUCT_HEADERS = {
        "header.product.id", "header.product.desc", "header.product.unit", "header.product.price"
    };
    
    private static final String[] ACCOUNTING_HEADERS = {
        "header.accounting.id", "header.accounting.desc", "header.accounting.type",
        "header.accounting.amount", "header.accounting.datetime"
    };

    public static void printPersons(List<Person> persons) {
        doPrint(persons, PERSON_HEADERS, TablePrinter::formatPersonRow);
    }

    public static void printProducts(List<Product> products) {
        doPrint(products, PRODUCT_HEADERS, TablePrinter::formatProductRow);
    }

    public static void printAccountingMovements(List<AccountingMovement> movements, double balance) {
        doPrint(movements, ACCOUNTING_HEADERS, TablePrinter::formatAccountingRow);
        printBalance(balance);
    }

    public static <T> void printGeneric(List<T> items, Runnable headerPrinter, Function<T, String> formatter) {
        if (items.isEmpty()) { 
            System.out.println(MessageProvider.get("msg.empty")); 
            return; 
        }
        headerPrinter.run();
        printPagedRows(items, formatter);
    }

    private static <T> void doPrint(List<T> items, String[] headerKeys, Function<T, String> formatter) {
        if (items.isEmpty()) { 
            System.out.println(MessageProvider.get("msg.empty")); 
            return; 
        }
        printHeader(headerKeys);
        printPagedRows(items, formatter);
    }

    private static void printHeader(String[] headerKeys) {
        System.out.println(LINE);
        
        String format = switch(headerKeys.length) {
            case 4 -> "%-4s  %-30s %-10s %-12s%n";       
            case 5 -> (headerKeys[4].contains("age") ? 
                       "%-4s  %-20s %-20s %-12s %-5s%n" :
                       "%-4s  %-28s %-8s %-12s %-16s%n");
            default -> "%-4s  %s%n";
        };
        
        Object[] values = new Object[headerKeys.length];
        for (int i = 0; i < headerKeys.length; i++) {
            values[i] = MessageProvider.get(headerKeys[i]);
        }
        
        System.out.printf(format, values);
        System.out.println(LINE);
    }

    private static <T> void printPagedRows(List<T> items, Function<T, String> formatter) {
        int total = (int) Math.ceil((double) items.size() / PAGE_SIZE);
        for (int page = 0; page < total; page++) {
            printPage(items, page, total, formatter);
            if (page < total - 1 && !promptNextPage()) break;
        }
        System.out.println(LINE);
    }

    private static <T> void printPage(List<T> items, int page, int totalPages, Function<T, String> formatter) {
        int from = page * PAGE_SIZE;
        int to = Math.min(from + PAGE_SIZE, items.size());
        items.subList(from, to).forEach(item -> System.out.println(formatter.apply(item)));
        System.out.printf("%s %d %s %d%n",
                MessageProvider.get("label.page"), page + 1,
                MessageProvider.get("label.of"), totalPages);
    }

    private static void printBalance(double balance) {
        System.out.printf("%s%n%-40s %s: %.2f%n%s%n", LINE,
                "", MessageProvider.get("label.balance"), balance, LINE);
    }

    private static String formatPersonRow(Person person) {
        return String.format(PERSON_FORMAT,
                person.getId(), 
                person.getFirstName(), 
                person.getLastName(), 
                person.getGender().getDisplayValue(),
                person.getAge());
    }

    private static String formatProductRow(Product product) {
        return String.format(PRODUCT_FORMAT,
                product.getId(), 
                product.getDescription(), 
                product.getUnit(), 
                product.getPrice());
    }

    private static String formatAccountingRow(AccountingMovement movement) {
        return String.format(ACCOUNTING_FORMAT,
                movement.getId(), 
                movement.getDescription(), 
                movement.getType(), 
                movement.getAmount(), 
                movement.getFormattedDateTime());
    }
    private static boolean promptNextPage() {
        System.out.println(MessageProvider.get("label.next"));
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("s") || input.equals("s\r");
    }

    private TablePrinter() {}
}