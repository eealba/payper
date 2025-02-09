package io.github.eealba.payper.invoices.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The reference to the payment detail.
 */
public class PaymentReference {


    @JasonerProperty("payment_id")
    private final String paymentId;

    private PaymentReference(Builder builder) {
        paymentId = builder.paymentId;
    }

    /**
     * The ID for the invoice payment.
     */
    @JasonerProperty("payment_id")
    public String paymentId() {
        return paymentId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String paymentId;

        /**
         * The ID for the invoice payment.
         */
        @JasonerProperty("payment_id")
        public Builder paymentId(String value) {
            this.paymentId = value;
            return this;
        }

        public PaymentReference build() {
            return new PaymentReference(this);
        }

    }

}

