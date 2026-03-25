package co.edu.uptc.model.persistence;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CsvWriter {

    private final String dataPath;

    public CsvWriter(String dataPath) {
        this.dataPath = dataPath;
        initializeDirectory();
    }

    private void initializeDirectory() {
        try {
            Files.createDirectories(Paths.get(dataPath));
        } catch (IOException e) {
            throw new RuntimeException("Cannot create data directory", e);
        }
    }

    public void write(String filePath, String csvContent) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(csvContent);
        } catch (IOException e) {
            throw new RuntimeException("Export failed: " + e.getMessage(), e);
        }
    }
}