package io.github.eealba.payper.subscriptions.v1.model;


import java.util.Objects;
import io.github.eealba.jasoner.JasonerProperty;

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

    @JasonerProperty("country_code")
    public String countryCode() {
        return countryCode;
    }

    @JasonerProperty("national_number")
    public String nationalNumber() {
        return nationalNumber;
    }

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

        @JasonerProperty("country_code")
        public Builder countryCode(String value) {
            countryCode = value;
            return this;
        }

        @JasonerProperty("national_number")
        public Builder nationalNumber(String value) {
            nationalNumber = value;
            return this;
        }

        @JasonerProperty("extension_number")
        public Builder extensionNumber(String value) {
            extensionNumber = value;
            return this;
        }

        public Phone build() {
            return new Phone(this);
        }

    }

}

