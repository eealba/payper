package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * Information about the decrypted apple pay payment data for the token like cryptogram, eci indicator.
 */
public class ApplePayPaymentData {


    
    private final String cryptogram;
    @JasonerProperty("eci_indicator")
    private final String eciIndicator;
    @JasonerProperty("emv_data")
    private final String emvData;
    
    private final String pin;

    private ApplePayPaymentData(Builder builder) {
        cryptogram = builder.cryptogram;
        eciIndicator = builder.eciIndicator;
        emvData = builder.emvData;
        pin = builder.pin;

    }

    /**
     * Online payment cryptogram, as defined by 3D Secure. The pattern is defined by an external party and supports 
Unicode.
     */
    
    public String cryptogram() {
        return cryptogram;
    }

    /**
     * ECI indicator, as defined by 3- Secure. The pattern is defined by an external party and supports Unicode.
     */
    @JasonerProperty("eci_indicator")
    public String eciIndicator() {
        return eciIndicator;
    }

    /**
     * Encoded Apple Pay EMV Payment Structure used for payments in China. The pattern is defined by an external 
party and supports Unicode.
     */
    @JasonerProperty("emv_data")
    public String emvData() {
        return emvData;
    }

    /**
     * Bank Key encrypted Apple Pay PIN. The pattern is defined by an external party and supports Unicode.
     */
    
    public String pin() {
        return pin;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String cryptogram;
        private String eciIndicator;
        private String emvData;
        private String pin;

        /**
         * Online payment cryptogram, as defined by 3D Secure. The pattern is defined by an external party and supports 
Unicode.
         */
        
        public Builder cryptogram(String value) {
            this.cryptogram = value;
            return this;
        }

        /**
         * ECI indicator, as defined by 3- Secure. The pattern is defined by an external party and supports Unicode.
         */
        @JasonerProperty("eci_indicator")
        public Builder eciIndicator(String value) {
            this.eciIndicator = value;
            return this;
        }

        /**
         * Encoded Apple Pay EMV Payment Structure used for payments in China. The pattern is defined by an external 
party and supports Unicode.
         */
        @JasonerProperty("emv_data")
        public Builder emvData(String value) {
            this.emvData = value;
            return this;
        }

        /**
         * Bank Key encrypted Apple Pay PIN. The pattern is defined by an external party and supports Unicode.
         */
        
        public Builder pin(String value) {
            this.pin = value;
            return this;
        }

        public ApplePayPaymentData build() {
            return new ApplePayPaymentData(this);
        }

    }

}

