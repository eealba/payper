package io.github.eealba.payper.invoices.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * An array of payments registered against the invoice.
 */
public class Payments {


    @JasonerProperty("paid_amount")
    private final Money paidAmount;
    
    private final List<PaymentDetail> transactions;

    private Payments(Builder builder) {
        paidAmount = builder.paidAmount;
        transactions = builder.transactions;

    }

    /**
     * paidAmount
     */
    @JasonerProperty("paid_amount")
    public Money paidAmount() {
        return paidAmount;
    }

    /**
     * An array of payment details for the invoice. The payment details of the invoice like payment type, method, 
date, discount and transaction type.
     */
    
    public List<PaymentDetail> transactions() {
        return transactions;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Money paidAmount;
        private List<PaymentDetail> transactions;

        /**
         * paidAmount
         */
        @JasonerProperty("paid_amount")
        public Builder paidAmount(Money value) {
            this.paidAmount = value;
            return this;
        }

        /**
         * An array of payment details for the invoice. The payment details of the invoice like payment type, method, 
date, discount and transaction type.
         */
        
        public Builder transactions(List<PaymentDetail> value) {
            this.transactions = value;
            return this;
        }

        public Payments build() {
            return new Payments(this);
        }

    }

}

