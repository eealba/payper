package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;

/**
 * Payer confirms the intent to pay for the Order using the provided payment source.
 */
public class ConfirmOrderRequest {


    @JasonerProperty("payment_source")
    private final PaymentSource paymentSource;
    @JasonerProperty("processing_instruction")
    private final ProcessingInstruction processingInstruction;
    @JasonerProperty("application_context")
    private final OrderConfirmApplicationContext applicationContext;

    private ConfirmOrderRequest(Builder builder) {
        processingInstruction = builder.processingInstruction;
        applicationContext = builder.applicationContext;
        paymentSource = Objects.requireNonNull(builder.paymentSource);
    }

    /**
     * paymentSource
     */
    @JasonerProperty("payment_source")
    public PaymentSource paymentSource() {
        return paymentSource;
    }

    /**
     * processingInstruction
     */
    @JasonerProperty("processing_instruction")
    public ProcessingInstruction processingInstruction() {
        return processingInstruction;
    }

    /**
     * applicationContext
     */
    @JasonerProperty("application_context")
    public OrderConfirmApplicationContext applicationContext() {
        return applicationContext;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private PaymentSource paymentSource;
        private ProcessingInstruction processingInstruction;
        private OrderConfirmApplicationContext applicationContext;

        /**
         * paymentSource
         */
        @JasonerProperty("payment_source")
        public Builder paymentSource(PaymentSource value) {
            this.paymentSource = value;
            return this;
        }

        /**
         * processingInstruction
         */
        @JasonerProperty("processing_instruction")
        public Builder processingInstruction(ProcessingInstruction value) {
            this.processingInstruction = value;
            return this;
        }

        /**
         * applicationContext
         */
        @JasonerProperty("application_context")
        public Builder applicationContext(OrderConfirmApplicationContext value) {
            this.applicationContext = value;
            return this;
        }

        public ConfirmOrderRequest build() {
            return new ConfirmOrderRequest(this);
        }

    }

}

