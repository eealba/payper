package io.github.eealba.payper.invoices.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.time.LocalDate;

/**
 * The refund details of the invoice. Includes the refund type, date, amount, and method.
 */
public class RefundDetail {


    
    private final PaymentType type;
    @JasonerProperty("refund_id")
    private final String refundId;
    @JasonerProperty("refund_date")
    private final LocalDate refundDate;
    
    private final Money amount;
    
    private final PaymentMethod method;

    private RefundDetail(Builder builder) {
        type = builder.type;
        refundId = builder.refundId;
        refundDate = builder.refundDate;
        amount = builder.amount;
        method = builder.method;
    }

    /**
     * type
     */
    
    public PaymentType type() {
        return type;
    }

    /**
     * The ID for a PayPal payment transaction. Required for the `PAYPAL` payment type.
     */
    @JasonerProperty("refund_id")
    public String refundId() {
        return refundId;
    }

    /**
     * refundDate
     */
    @JasonerProperty("refund_date")
    public LocalDate refundDate() {
        return refundDate;
    }

    /**
     * amount
     */
    
    public Money amount() {
        return amount;
    }

    /**
     * method
     */
    
    public PaymentMethod method() {
        return method;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private PaymentType type;
        private String refundId;
        private LocalDate refundDate;
        private Money amount;
        private PaymentMethod method;

        /**
         * type
         */
        
        public Builder type(PaymentType value) {
            this.type = value;
            return this;
        }

        /**
         * The ID for a PayPal payment transaction. Required for the `PAYPAL` payment type.
         */
        @JasonerProperty("refund_id")
        public Builder refundId(String value) {
            this.refundId = value;
            return this;
        }

        /**
         * refundDate
         */
        @JasonerProperty("refund_date")
        public Builder refundDate(LocalDate value) {
            this.refundDate = value;
            return this;
        }

        /**
         * amount
         */
        
        public Builder amount(Money value) {
            this.amount = value;
            return this;
        }

        /**
         * method
         */
        
        public Builder method(PaymentMethod value) {
            this.method = value;
            return this;
        }

        public RefundDetail build() {
            return new RefundDetail(this);
        }

    }

}

