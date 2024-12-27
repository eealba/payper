package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The authorization of an order request.
 */
public class OrderAuthorizeRequest {


    @JasonerProperty("payment_source")
    private final PaymentSource paymentSource;

    private OrderAuthorizeRequest(Builder builder) {
        paymentSource = builder.paymentSource;

    }

    /**
     * paymentSource
     */
    @JasonerProperty("payment_source")
    public PaymentSource paymentSource() {
        return paymentSource;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private PaymentSource paymentSource;

        /**
         * paymentSource
         */
        @JasonerProperty("payment_source")
        public Builder paymentSource(PaymentSource value) {
            this.paymentSource = value;
            return this;
        }

        public OrderAuthorizeRequest build() {
            return new OrderAuthorizeRequest(this);
        }

    }

}

