package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;


/**
 * Information used to pay using P24(Przelewy24).
 */
public class P24 {


    
    private final FullName name;
    
    private final EmailAddress email;
    @JasonerProperty("country_code")
    private final CountryCode countryCode;
    @JasonerProperty("payment_descriptor")
    private final String paymentDescriptor;
    @JasonerProperty("method_id")
    private final String methodId;
    @JasonerProperty("method_description")
    private final String methodDescription;

    private P24(Builder builder) {
        name = builder.name;
        email = builder.email;
        countryCode = builder.countryCode;
        paymentDescriptor = builder.paymentDescriptor;
        methodId = builder.methodId;
        methodDescription = builder.methodDescription;

    }

    /**
     * name
     */
    
    public FullName name() {
        return name;
    }

    /**
     * email
     */
    
    public EmailAddress email() {
        return email;
    }

    /**
     * countryCode
     */
    @JasonerProperty("country_code")
    public CountryCode countryCode() {
        return countryCode;
    }

    /**
     * P24 generated payment description.
     */
    @JasonerProperty("payment_descriptor")
    public String paymentDescriptor() {
        return paymentDescriptor;
    }

    /**
     * Numeric identifier of the payment scheme or bank used for the payment.
     */
    @JasonerProperty("method_id")
    public String methodId() {
        return methodId;
    }

    /**
     * Friendly name of the payment scheme or bank used for the payment.
     */
    @JasonerProperty("method_description")
    public String methodDescription() {
        return methodDescription;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private FullName name;
        private EmailAddress email;
        private CountryCode countryCode;
        private String paymentDescriptor;
        private String methodId;
        private String methodDescription;

        /**
         * name
         */
        
        public Builder name(FullName value) {
            this.name = value;
            return this;
        }

        /**
         * email
         */
        
        public Builder email(EmailAddress value) {
            this.email = value;
            return this;
        }

        /**
         * countryCode
         */
        @JasonerProperty("country_code")
        public Builder countryCode(CountryCode value) {
            this.countryCode = value;
            return this;
        }

        /**
         * P24 generated payment description.
         */
        @JasonerProperty("payment_descriptor")
        public Builder paymentDescriptor(String value) {
            this.paymentDescriptor = value;
            return this;
        }

        /**
         * Numeric identifier of the payment scheme or bank used for the payment.
         */
        @JasonerProperty("method_id")
        public Builder methodId(String value) {
            this.methodId = value;
            return this;
        }

        /**
         * Friendly name of the payment scheme or bank used for the payment.
         */
        @JasonerProperty("method_description")
        public Builder methodDescription(String value) {
            this.methodDescription = value;
            return this;
        }

        public P24 build() {
            return new P24(this);
        }

    }


}

