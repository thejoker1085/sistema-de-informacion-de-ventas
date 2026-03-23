package co.edu.uptc.view;

import co.edu.uptc.config.MessageProvider;
import co.edu.uptc.pojo.Gender;
import co.edu.uptc.pojo.MeasurementUnit;
import co.edu.uptc.pojo.MovementType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class InputReader {

    private final Scanner scanner = new Scanner(System.in);

    public String readString(String promptKey) {
        System.out.print(MessageProvider.get(promptKey) + ": ");
        return scanner.nextLine().trim();
    }

    public int readOption(int min, int max) {
        while (true) {
            System.out.print(MessageProvider.get("menu.select") + " (" + min + "-" + max + "): ");
            try {
                int val = Integer.parseInt(scanner.nextLine().trim());
                if (val >= min && val <= max) return val;
            } catch (NumberFormatException ignored) {}
            System.out.println("  " + MessageProvider.get("msg.error.option"));
        }
    }

    public double readPositiveDouble(String promptKey) {
        while (true) {
            System.out.print(MessageProvider.get(promptKey) + ": ");
            try {
                double val = Double.parseDouble(scanner.nextLine().trim());
                if (val > 0) return val;
            } catch (NumberFormatException ignored) {}
            System.out.println("  " + MessageProvider.get("msg.error.amount"));
        }
    }

    public LocalDate readDate() {
        while (true) {
            System.out.print(MessageProvider.get("prompt.birthdate") + ": ");
            String raw = scanner.nextLine().trim();
            try { return LocalDate.parse(raw); }
            catch (Exception e) { System.out.println("  " + MessageProvider.get("msg.error.date")); }
        }
    }

    public LocalDateTime readDateTime() {
        while (true) {
            System.out.print(MessageProvider.get("prompt.datetime") + ": ");
            String raw = scanner.nextLine().trim();
            try { return LocalDateTime.parse(raw); }
            catch (Exception e) { System.out.println("  " + MessageProvider.get("msg.error.date")); }
        }
    }

    public Gender readGender() {
        System.out.println(MessageProvider.get("prompt.gender.options"));
        return readOption(1, 2) == 1 ? Gender.MASCULINE : Gender.FEMININE;
    }

    public MeasurementUnit readMeasurementUnit() {
        System.out.println(MessageProvider.get("prompt.unit.options"));
        return switch (readOption(1, 4)) {
            case 1 -> MeasurementUnit.POUND;
            case 2 -> MeasurementUnit.KILO;
            case 3 -> MeasurementUnit.SACK;
            default -> MeasurementUnit.TON;
        };
    }

    public MovementType readMovementType() {
        System.out.println(MessageProvider.get("prompt.movtype.options"));
        return readOption(1, 2) == 1 ? MovementType.INCOME : MovementType.EXPENSE;
    }
}
