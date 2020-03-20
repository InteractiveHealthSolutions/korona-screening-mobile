package com.ihsinformatics.korona.model;

public class Language {
    private String language;
    private String locale;

    public Language(String language, String locale) {
        this.language = language;
        this.locale = locale;
    }

    public String getLanguage() {
        return language;
    }

    public String getLocale() {
        return locale;
    }

    @Override
    public String toString() {
        return language;
    }
}
