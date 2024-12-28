package io.github.eealba.payper.orders.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * Bank Identification Number (BIN) details used to fund a payment.
 */
public class BinDetails {


    
    private final String bin;
    @JasonerProperty("issuing_bank")
    private final String issuingBank;
    @JasonerProperty("bin_country_code")
    private final CountryCode binCountryCode;
    
    private final List<String> products;

    private BinDetails(Builder builder) {
        bin = builder.bin;
        issuingBank = builder.issuingBank;
        binCountryCode = builder.binCountryCode;
        products = builder.products;

    }

    /**
     * The Bank Identification Number (BIN) signifies the number that is being used to identify the granular level 
details (except the PII information) of the card.
     */
    
    public String bin() {
        return bin;
    }

    /**
     * The issuer of the card instrument.
     */
    @JasonerProperty("issuing_bank")
    public String issuingBank() {
        return issuingBank;
    }

    /**
     * binCountryCode
     */
    @JasonerProperty("bin_country_code")
    public CountryCode binCountryCode() {
        return binCountryCode;
    }

    /**
     * The type of card product assigned to the BIN by the issuer. These values are defined by the issuer and may 
change over time. Some examples include: PREPAID_GIFT, CONSUMER, CORPORATE.
     */
    
    public List<String> products() {
        return products;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String bin;
        private String issuingBank;
        private CountryCode binCountryCode;
        private List<String> products;

        /**
         * The Bank Identification Number (BIN) signifies the number that is being used to identify the granular level 
details (except the PII information) of the card.
         */
        
        public Builder bin(String value) {
            this.bin = value;
            return this;
        }

        /**
         * The issuer of the card instrument.
         */
        @JasonerProperty("issuing_bank")
        public Builder issuingBank(String value) {
            this.issuingBank = value;
            return this;
        }

        /**
         * binCountryCode
         */
        @JasonerProperty("bin_country_code")
        public Builder binCountryCode(CountryCode value) {
            this.binCountryCode = value;
            return this;
        }

        /**
         * The type of card product assigned to the BIN by the issuer. These values are defined by the issuer and may 
change over time. Some examples include: PREPAID_GIFT, CONSUMER, CORPORATE.
         */
        
        public Builder products(List<String> value) {
            this.products = value;
            return this;
        }

        public BinDetails build() {
            return new BinDetails(this);
        }

    }

}

