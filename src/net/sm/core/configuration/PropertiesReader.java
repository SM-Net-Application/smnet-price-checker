package net.sm.core.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    private final String propertyNullText;
    private File fileProperties;
    private Properties properties;

    public PropertiesReader(File fileProperties, String propertyNullText) throws IOException {
        super();

        this.propertyNullText = propertyNullText;
        this.fileProperties = fileProperties;
        this.load();
    }

    private void load() throws IOException {

        if (this.fileProperties != null)
            if (this.fileProperties.exists()) {
                this.properties = new Properties();
                this.properties.load(new FileInputStream(this.fileProperties));
            }
    }

    public String getString(String key, boolean newLine) {

        if (this.properties != null) {
            String property = this.properties.getProperty(key);
            if (property != null)
                return newLine ? property.replaceAll("\\\\n", "\n") : property;
        }

        return this.propertyNullText;
    }

    public String getString(String key) {
        return this.getString(key, false);
    }
}
