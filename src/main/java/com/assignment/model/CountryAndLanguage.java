package com.assignment.model;

/**
 * Enum used to enter data defining locale when entering input for endpoint calls.
 * User friendly version with country specified first, followed by language is used.
 * It is then converted to standard locale identifier.
 */
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
