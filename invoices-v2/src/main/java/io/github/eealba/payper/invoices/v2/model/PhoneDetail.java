package io.github.eealba.payper.invoices.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The phone details. Includes the phone number and type.
 */
public class PhoneDetail {


    @JasonerProperty("country_code")
    private final String countryCode;
    @JasonerProperty("national_number")
    private final String nationalNumber;
    @JasonerProperty("extension_number")
    private final String extensionNumber;
    @JasonerProperty("phone_type")
    private final PhoneType phoneType;

    private PhoneDetail(Builder builder) {
        countryCode = builder.countryCode;
        nationalNumber = builder.nationalNumber;
        extensionNumber = builder.extensionNumber;
        phoneType = builder.phoneType;
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

    /**
     * phoneType
     */
    @JasonerProperty("phone_type")
    public PhoneType phoneType() {
        return phoneType;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String countryCode;
        private String nationalNumber;
        private String extensionNumber;
        private PhoneType phoneType;

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

        /**
         * phoneType
         */
        @JasonerProperty("phone_type")
        public Builder phoneType(PhoneType value) {
            this.phoneType = value;
            return this;
        }

        public PhoneDetail build() {
            return new PhoneDetail(this);
        }

    }

}

