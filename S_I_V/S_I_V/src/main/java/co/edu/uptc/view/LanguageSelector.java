package co.edu.uptc.view;

import co.edu.uptc.config.ConfigLoader;
import co.edu.uptc.config.MessageProvider;

public class LanguageSelector {

    public static void selectAndLoad() {
        String lang = ConfigLoader.get("language");
        MessageProvider.load(lang);
    }

    private LanguageSelector() {}
}
