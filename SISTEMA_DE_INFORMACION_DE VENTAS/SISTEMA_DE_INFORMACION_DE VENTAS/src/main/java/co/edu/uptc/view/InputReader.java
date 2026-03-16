package co.edu.uptc.view;

import co.edu.uptc.pojo.Gender;
import co.edu.uptc.pojo.MeasurementUnit;
import co.edu.uptc.pojo.MovementType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputReader {

    private final Scanner scanner = new Scanner(System.in);

    public String readString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }

    public int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt + " (" + min + "-" + max + "): ");
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) return value;
            } catch (NumberFormatException ignored) {}
            System.out.println("  Enter a number between " + min + " and " + max + ".");
        }
    }

    public double readPositiveDouble(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value > 0) return value;
            } catch (NumberFormatException ignored) {}
            System.out.println("  Enter a positive number.");
        }
    }

    public LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt + " (yyyy-MM-dd): ");
            try {
                return LocalDate.parse(scanner.nextLine().trim());
            } catch (DateTimeParseException ignored) {
                System.out.println("  Invalid date. Use format yyyy-MM-dd.");
            }
        }
    }

    public LocalDateTime readDateTime(String prompt) {
        while (true) {
            System.out.print(prompt + " (yyyy-MM-ddTHH:mm): ");
            try {
                return LocalDateTime.parse(scanner.nextLine().trim());
            } catch (DateTimeParseException ignored) {
                System.out.println("  Invalid date/time. Use format yyyy-MM-ddTHH:mm.");
            }
        }
    }

    public Gender readGender() {
        System.out.println("  1. Masculine  2. Feminine");
        int opt = readInt("  Select", 1, 2);
        return opt == 1 ? Gender.MASCULINE : Gender.FEMININE;
    }

    public MeasurementUnit readMeasurementUnit() {
        System.out.println("  1. Pound  2. Kilo  3. Sack  4. Ton");
        int opt = readInt("  Select", 1, 4);
        return switch (opt) {
            case 1 -> MeasurementUnit.POUND;
            case 2 -> MeasurementUnit.KILO;
            case 3 -> MeasurementUnit.SACK;
            default -> MeasurementUnit.TON;
        };
    }

    public MovementType readMovementType() {
        System.out.println("  1. Income  2. Expense");
        int opt = readInt("  Select", 1, 2);
        return opt == 1 ? MovementType.INCOME : MovementType.EXPENSE;
    }
}
