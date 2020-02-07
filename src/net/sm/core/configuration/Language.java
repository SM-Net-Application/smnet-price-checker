package net.sm.core.configuration;

import java.io.File;
import java.io.IOException;

public class Language extends PropertiesReader {

    private String languageName;

    public Language(File fileProperties, String keyLanguageName, String propertyNullText) throws IOException {
        super(fileProperties, propertyNullText);

        this.languageName = this.getString(keyLanguageName, false);
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }
}