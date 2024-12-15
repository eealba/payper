package io.github.eealba.payper.subscriptions.v1.model;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;

/**
 * The phone number, in its canonical international [E.164 numbering plan format](https://www.itu.int/rec/T-REC-E.164/en).
 */
public class Phone {


    @JasonerProperty("country_code")
    private final String countryCode;
    @JasonerProperty("national_number")
    private final String nationalNumber;
    @JasonerProperty("extension_number")
    private final String extensionNumber;

    private Phone(Builder builder) {
        extensionNumber = builder.extensionNumber;
        countryCode = Objects.requireNonNull(builder.countryCode);
        nationalNumber = Objects.requireNonNull(builder.nationalNumber);
    }

    /**
     * The country calling code (CC), in its canonical international [E.164 numbering plan 
format](https://www.itu.int/rec/T-REC-E.164/en). The combined length of the CC and the national number must 
not be greater than 15 digits. The national number consists of a national destination code (NDC) and 
subscriber number (SN).
     */
    @JasonerProperty("country_code")
    public String countryCode() {
        return countryCode;
    }

    /**
     * The national number, in its canonical international [E.164 numbering plan 
format](https://www.itu.int/rec/T-REC-E.164/en). The combined length of the country calling code (CC) and the 
national number must not be greater than 15 digits. The national number consists of a national destination 
code (NDC) and subscriber number (SN).
     */
    @JasonerProperty("national_number")
    public String nationalNumber() {
        return nationalNumber;
    }

    /**
     * The extension number.
     */
    @JasonerProperty("extension_number")
    public String extensionNumber() {
        return extensionNumber;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String countryCode;
        private String nationalNumber;
        private String extensionNumber;

        /**
         * The country calling code (CC), in its canonical international [E.164 numbering plan 
format](https://www.itu.int/rec/T-REC-E.164/en). The combined length of the CC and the national number must 
not be greater than 15 digits. The national number consists of a national destination code (NDC) and 
subscriber number (SN).
         */
        @JasonerProperty("country_code")
        public Builder countryCode(String value) {
            this.countryCode = value;
            return this;
        }

        /**
         * The national number, in its canonical international [E.164 numbering plan 
format](https://www.itu.int/rec/T-REC-E.164/en). The combined length of the country calling code (CC) and the 
national number must not be greater than 15 digits. The national number consists of a national destination 
code (NDC) and subscriber number (SN).
         */
        @JasonerProperty("national_number")
        public Builder nationalNumber(String value) {
            this.nationalNumber = value;
            return this;
        }

        /**
         * The extension number.
         */
        @JasonerProperty("extension_number")
        public Builder extensionNumber(String value) {
            this.extensionNumber = value;
            return this;
        }

        public Phone build() {
            return new Phone(this);
        }

    }

}

