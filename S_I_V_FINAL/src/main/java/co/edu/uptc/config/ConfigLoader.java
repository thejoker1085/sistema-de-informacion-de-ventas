package co.edu.uptc.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static final Properties props = new Properties();

    static {
        try (InputStream in = ConfigLoader.class.getResourceAsStream("/config.properties")) {
            if (in == null) throw new RuntimeException("config.properties no fue encontrado");
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Fallo al cargar config.properties", e);
        }
    }

    public static String get(String key) { 
        return props.getProperty(key); 
    }
    public static int getInt(String key) { 
        return Integer.parseInt(props.getProperty(key)); 
    }
    public static double getDouble(String key) { 
        return Double.parseDouble(props.getProperty(key)); 
    }
    public static void set(String key, String value) { 
        props.setProperty(key, value); 
    }

    private ConfigLoader() {}
}