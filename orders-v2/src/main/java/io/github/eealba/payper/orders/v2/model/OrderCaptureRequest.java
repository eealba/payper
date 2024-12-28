package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * Completes an capture payment for an order.
 */
public class OrderCaptureRequest {


    @JasonerProperty("payment_source")
    private final PaymentSource paymentSource;

    private OrderCaptureRequest(Builder builder) {
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

        public OrderCaptureRequest build() {
            return new OrderCaptureRequest(this);
        }

    }

}

