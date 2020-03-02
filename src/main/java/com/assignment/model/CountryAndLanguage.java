package com.assignment.model;

public enum CountryAndLanguage {
    CA_EN("en_CA"),
    CA_FR("fr_CA"),
    US_EN("en_US");

    String locale;

    CountryAndLanguage(String locale) {
        this.locale = locale;
    }

    public String getLocale() {
        return locale;
    }

    public static CountryAndLanguage getFromLocale(String locale) {
        for (CountryAndLanguage cal : values()) {
            if ( cal.getLocale().equals(locale)) {
                return cal;
            }
        }
        throw new IllegalArgumentException("Unknown locale: " + locale);
    }

}
