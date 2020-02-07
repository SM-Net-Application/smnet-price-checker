package net.sm.core.configuration;

import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;

public class Ini {

    private Wini ini;

    public Ini(File fileIni) throws IOException {
        super();

        if (fileIni != null)
            if (fileIni.exists())
                this.ini = new Wini(fileIni);
    }

    public void add(String section, String option, Class<Object> clazz) {

        if (this.ini != null)
            this.ini.add(section, option, clazz);
    }

    public void store() throws IOException {

        if (this.ini != null)
            this.ini.store();
    }

    public Object get(String section, String option, Class<Object> clazz) {

        return this.ini != null ? this.ini.get(section, option, clazz) : null;
    }

    public Wini getIni() {
        return ini;
    }

    public void setIni(Wini ini) {
        this.ini = ini;
    }
}
