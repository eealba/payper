package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * Customizes the payer confirmation experience.
 */
public class OrderConfirmApplicationContext {


    @JasonerProperty("brand_name")
    private final String brandName;
    
    private final Language locale;
    @JasonerProperty("return_url")
    private final String returnUrl;
    @JasonerProperty("cancel_url")
    private final String cancelUrl;
    @JasonerProperty("stored_payment_source")
    private final StoredPaymentSource storedPaymentSource;

    private OrderConfirmApplicationContext(Builder builder) {
        brandName = builder.brandName;
        locale = builder.locale;
        returnUrl = builder.returnUrl;
        cancelUrl = builder.cancelUrl;
        storedPaymentSource = builder.storedPaymentSource;

    }

    /**
     * Label to present to your payer as part of the PayPal hosted web experience.
     */
    @JasonerProperty("brand_name")
    public String brandName() {
        return brandName;
    }

    /**
     * locale
     */
    
    public Language locale() {
        return locale;
    }

    /**
     * The URL where the customer is redirected after the customer approves the payment.
     */
    @JasonerProperty("return_url")
    public String returnUrl() {
        return returnUrl;
    }

    /**
     * The URL where the customer is redirected after the customer cancels the payment.
     */
    @JasonerProperty("cancel_url")
    public String cancelUrl() {
        return cancelUrl;
    }

    /**
     * storedPaymentSource
     */
    @JasonerProperty("stored_payment_source")
    public StoredPaymentSource storedPaymentSource() {
        return storedPaymentSource;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String brandName;
        private Language locale;
        private String returnUrl;
        private String cancelUrl;
        private StoredPaymentSource storedPaymentSource;

        /**
         * Label to present to your payer as part of the PayPal hosted web experience.
         */
        @JasonerProperty("brand_name")
        public Builder brandName(String value) {
            this.brandName = value;
            return this;
        }

        /**
         * locale
         */
        
        public Builder locale(Language value) {
            this.locale = value;
            return this;
        }

        /**
         * The URL where the customer is redirected after the customer approves the payment.
         */
        @JasonerProperty("return_url")
        public Builder returnUrl(String value) {
            this.returnUrl = value;
            return this;
        }

        /**
         * The URL where the customer is redirected after the customer cancels the payment.
         */
        @JasonerProperty("cancel_url")
        public Builder cancelUrl(String value) {
            this.cancelUrl = value;
            return this;
        }

        /**
         * storedPaymentSource
         */
        @JasonerProperty("stored_payment_source")
        public Builder storedPaymentSource(StoredPaymentSource value) {
            this.storedPaymentSource = value;
            return this;
        }

        public OrderConfirmApplicationContext build() {
            return new OrderConfirmApplicationContext(this);
        }

    }

}

