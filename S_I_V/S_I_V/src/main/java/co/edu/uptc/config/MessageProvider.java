package co.edu.uptc.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class MessageProvider {

    private static Properties messages = new Properties();

    public static void load(String languageCode) {
        String path = "/i18n/messages_" + languageCode + ".properties";
        try (InputStream in = MessageProvider.class.getResourceAsStream(path)) {
            if (in == null) throw new RuntimeException("Archivo de lenguajes no encontrado: " + path);
            messages = new Properties();
            messages.load(new InputStreamReader(in, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException("Fallo al cargar lenguajes: " + languageCode, e);
        }
    }

    public static String get(String key) {
        return messages.getProperty(key, "[" + key + "]");
    }

    public static String get(String key, Object... args) {
        String msg = get(key);
        for (int i = 0; i < args.length; i++)
            msg = msg.replace("{" + i + "}", String.valueOf(args[i]));
        return msg;
    }

    private MessageProvider() {}
}