package ebank.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * Singleton pattern.
 * Manages the interface language of the whole application. Only one
 * instance exists so every component reads messages from the same
 * language source (En.properties / Vi.properties).
 */
public class LanguageManager {

    private static LanguageManager instance;

    private Properties messages = new Properties();

    private LanguageManager() {
        // English is the default language of the system
        setLocate("En");
    }

    public static synchronized LanguageManager getInstance() {
        if (instance == null) {
            instance = new LanguageManager();
        }
        return instance;
    }

    /**
     * Switches the interface language by loading the matching
     * properties file from the classpath.
     *
     * @param locate name of the properties file without extension ("En" or "Vi")
     */
    public void setLocate(String locate) {
        String fileName = locate + ".properties";
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (in == null) {
                System.err.println("Cannot find resource: " + fileName);
                return;
            }
            Properties loaded = new Properties();
            // read as UTF-8 so Vietnamese characters are kept intact
            loaded.load(new InputStreamReader(in, StandardCharsets.UTF_8));
            this.messages = loaded;
        } catch (IOException e) {
            System.err.println("Error loading resource " + fileName + ": " + e.getMessage());
        }
    }

    public String getMessage(String key) {
        return messages.getProperty(key, key);
    }

    public String getMessage(String key, Object... args) {
        return MessageFormat.format(getMessage(key), args);
    }
}
