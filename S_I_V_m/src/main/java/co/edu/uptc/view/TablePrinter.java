package co.edu.uptc.view;

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

    private static final String PERSON_FORMAT = "%-20s %-20s %-5s  %-5d%n";
    private static final String PRODUCT_FORMAT = "%-4d  %-30s %-10s %-12.2f%n";
    private static final String ACCOUNTING_FORMAT = "%-4d  %-28s %-8s %-12.2f %-16s%n";

    public static void printPersons(List<Person> persons) {
        if (persons.isEmpty()) { System.out.println(MessageProvider.get("msg.empty")); return; }
        printPersonHeader();
        printPagedRows(persons, TablePrinter::formatPersonRow);
    }

    public static void printProducts(List<Product> products) {
        if (products.isEmpty()) { System.out.println(MessageProvider.get("msg.empty")); return; }
        printProductHeader();
        printPagedRows(products, TablePrinter::formatProductRow);
    }

    public static void printAccountingMovements(List<AccountingMovement> movements, double balance) {
        if (movements.isEmpty()) { System.out.println(MessageProvider.get("msg.empty")); return; }
        printAccountingHeader();
        printPagedRows(movements, TablePrinter::formatAccountingRow);
        printBalance(balance);
    }

    private static void printPersonHeader() {
        System.out.println(LINE);
        System.out.printf("%-20s %-20s %-5s  %-5s%n",
                MessageProvider.get("header.person.name"),
                MessageProvider.get("header.person.lastname"), 
                MessageProvider.get("header.person.gender"),
                MessageProvider.get("header.person.age"));
        System.out.println(LINE);
    }

    private static void printProductHeader() {
        System.out.println(LINE);
        System.out.printf("%-4s  %-30s %-10s %-12s%n",
                MessageProvider.get("header.product.id"),   MessageProvider.get("header.product.desc"),
                MessageProvider.get("header.product.unit"), MessageProvider.get("header.product.price"));
        System.out.println(LINE);
    }

    private static void printAccountingHeader() {
        System.out.println(LINE);
        System.out.printf("%-4s  %-28s %-8s %-12s %-16s%n",
                MessageProvider.get("header.accounting.id"),     MessageProvider.get("header.accounting.desc"),
                MessageProvider.get("header.accounting.type"),   MessageProvider.get("header.accounting.amount"),
                MessageProvider.get("header.accounting.datetime"));
        System.out.println(LINE);
    }

    private static <T> void printPagedRows(List<T> items, Function<T, String> formatter) {
        int totalPages = (int) Math.ceil((double) items.size() / PAGE_SIZE);
        for (int page = 0; page < totalPages; page++) {
            printPage(items, page, totalPages, formatter);
            if (page < totalPages - 1 && !promptNextPage()) break;
        }
        System.out.println(LINE);
    }

    private static <T> void printPage(List<T> items, int page, int totalPages, Function<T, String> formatter) {
        int from = page * PAGE_SIZE;
        int to = Math.min(from + PAGE_SIZE, items.size());
        items.subList(from, to).forEach(item -> System.out.print(formatter.apply(item)));
        System.out.printf("%s %d %s %d%n", MessageProvider.get("label.page"),
                page + 1, MessageProvider.get("label.of"), totalPages);
    }

    private static void printBalance(double balance) {
        System.out.printf("%s%n  %s: %.2f%n%s%n", LINE,
                MessageProvider.get("label.balance"), balance, LINE);
    }

    private static String formatPersonRow(Person p) {
        return String.format(PERSON_FORMAT, p.getFirstName(),
                p.getLastName(), p.getGender().getDisplayValue(), p.getAge());
    }

    private static String formatProductRow(Product p) {
        return String.format(PRODUCT_FORMAT, p.getId(), p.getDescription(), p.getUnit(), p.getPrice());
    }

    private static String formatAccountingRow(AccountingMovement m) {
        return String.format(ACCOUNTING_FORMAT, m.getId(), m.getDescription(),
                m.getType(), m.getAmount(), m.getFormattedDateTime());
    }

    private static boolean promptNextPage() {
        System.out.println(MessageProvider.get("label.next"));
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("s");
    }

    private TablePrinter() {}
}