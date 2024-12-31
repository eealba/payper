package io.github.eealba.payper.invoices.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * The invoicing refund details. Includes the refund type, date, amount, and method.
 */
public class Refunds {


    @JasonerProperty("refund_amount")
    private final Money refundAmount;
    
    private final List<RefundDetail> transactions;

    private Refunds(Builder builder) {
        refundAmount = builder.refundAmount;
        transactions = builder.transactions;

    }

    /**
     * refundAmount
     */
    @JasonerProperty("refund_amount")
    public Money refundAmount() {
        return refundAmount;
    }

    /**
     * An array of refund details for the invoice. Includes the refund type, date, amount, and method.
     */
    
    public List<RefundDetail> transactions() {
        return transactions;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Money refundAmount;
        private List<RefundDetail> transactions;

        /**
         * refundAmount
         */
        @JasonerProperty("refund_amount")
        public Builder refundAmount(Money value) {
            this.refundAmount = value;
            return this;
        }

        /**
         * An array of refund details for the invoice. Includes the refund type, date, amount, and method.
         */
        
        public Builder transactions(List<RefundDetail> value) {
            this.transactions = value;
            return this;
        }

        public Refunds build() {
            return new Refunds(this);
        }

    }

}

