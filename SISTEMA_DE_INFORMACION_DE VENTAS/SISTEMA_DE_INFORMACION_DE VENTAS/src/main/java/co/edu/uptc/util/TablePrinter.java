package co.edu.uptc.util;

import java.util.List;

public class TablePrinter {

    private static final String LINE = "-".repeat(70);

    public static void printHeader(String title) {
        System.out.println("\n" + LINE);
        System.out.println("  " + title);
        System.out.println(LINE);
    }

    public static void printRows(List<?> items) {
        if (items.isEmpty()) {
            System.out.println("  No records found.");
        } else {
            items.forEach(item -> System.out.println("  " + item));
        }
        System.out.println(LINE);
    }

    public static void printTotal(String label, double total) {
        System.out.printf("%s  TOTAL: %.2f%n", LINE, total);
        System.out.println(LINE);
    }

    private TablePrinter() {}
}
