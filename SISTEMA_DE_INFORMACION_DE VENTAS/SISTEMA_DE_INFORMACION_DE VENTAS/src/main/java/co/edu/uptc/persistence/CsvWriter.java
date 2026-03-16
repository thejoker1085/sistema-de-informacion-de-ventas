package co.edu.uptc.persistence;

import co.edu.uptc.config.ConfigLoader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CsvWriter {

    static {
        try {
            Files.createDirectories(Paths.get(ConfigLoader.get("data.path")));
        } catch (IOException e) {
            throw new RuntimeException("Cannot create data directory", e);
        }
    }

    public static void write(String filePath, String csvContent) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(csvContent);
            System.out.println("Exported successfully to: " + filePath);
        } catch (IOException e) {
            System.err.println("Export failed: " + e.getMessage());
        }
    }

    private CsvWriter() {}
}
