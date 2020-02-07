package net.sm.pricechecker.stores;

public enum EnumStores {

    AMAZON_DE(1,
            "AmazonDE",
            "(^https:\\/\\/www\\.amazon\\.de\\/.*dp\\/.+\\/*$|^https:\\/\\/www\\.amazon\\.de\\/.*product\\/.+\\/*$)",
            "https://www.amazon.de/dp/%s"),
    AMAZON_IT(2, "AmazonIT",
            "(^https:\\/\\/www\\.amazon\\.it\\/.*dp\\/.+\\/*$|^https:\\/\\/www\\.amazon\\.it\\/.*product\\/.+\\/*$)",
            "https://www.amazon.it/dp/%s");

    private int ordinal;
    private String name;
    private String patternURL;
    private String patternClearURL;

    EnumStores(int ordinal, String name, String patternURL, String patternClearURL) {

        this.ordinal = ordinal;
        this.name = name;
        this.patternURL = patternURL;
        this.patternClearURL = patternClearURL;
    }

    public static EnumStores getStoreByID(int id) {

        for (EnumStores s : EnumStores.values())
            if (s.getOrdinal() == id)
                return s;

        return null;
    }

    public static EnumStores getStoreByURL(String url) {

        for (EnumStores store : EnumStores.values())
            if (url.matches(store.getPatternURL()))
                return store;

        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatternURL() {
        return patternURL;
    }

    public void setPatternURL(String patternURL) {
        this.patternURL = patternURL;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    public String getPatternClearURL() {
        return patternClearURL;
    }

    public void setPatternClearURL(String patternClearURL) {
        this.patternClearURL = patternClearURL;
    }
}