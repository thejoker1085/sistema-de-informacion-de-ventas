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
            String language = props.getProperty("app.language", "es");
            MessageProvider.load(language);
        } catch (IOException e) {
            throw new RuntimeException("Fallo al cargar config.properties", e);
        }
    }

    public static String getConfigProperty(String key) { 
        return props.getProperty(key); 
    }
    public static int getIntProperty(String key) { 
        return Integer.parseInt(props.getProperty(key)); 
    }
    public static double getDoubleProperty(String key) { 
        return Double.parseDouble(props.getProperty(key)); 
    }
    public static void setConfigProperty(String key, String value) { 
        props.setProperty(key, value); 
    }

    private ConfigLoader() {}
}