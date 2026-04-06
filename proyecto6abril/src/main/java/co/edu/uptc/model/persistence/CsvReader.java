package co.edu.uptc.model.persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CsvReader {

    private final String dataPath;

    public CsvReader(String dataPath) {
        this.dataPath = dataPath;
    }

    public <T> List<T> read(String fileName, Function<String[], T> mapper) {
        List<T> result = new ArrayList<>();
        String fullPath = dataPath + "/" + fileName;
        if (!Files.exists(Paths.get(fullPath))) return result;
        try (BufferedReader reader = new BufferedReader(new FileReader(fullPath))) {
            readLines(reader, mapper, result);
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo " + fileName + ": " + e.getMessage(), e);
        }
        return result;
    }

    private <T> void readLines(BufferedReader reader, Function<String[], T> mapper, List<T> result) throws IOException {
        String line = reader.readLine();
        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                result.add(mapper.apply(line.split(",")));
            }
        }
    }
}