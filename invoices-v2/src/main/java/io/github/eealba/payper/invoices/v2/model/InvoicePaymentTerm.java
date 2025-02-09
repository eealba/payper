package io.github.eealba.payper.invoices.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.time.LocalDate;

/**
 * The payment term of the invoice. Payment can be due upon receipt, a specified date, or in a set number of days.
 */
public class InvoicePaymentTerm {


    @JasonerProperty("term_type")
    private final PaymentTermType termType;
    @JasonerProperty("due_date")
    private final LocalDate dueDate;

    private InvoicePaymentTerm(Builder builder) {
        termType = builder.termType;
        dueDate = builder.dueDate;
    }

    /**
     * termType
     */
    @JasonerProperty("term_type")
    public PaymentTermType termType() {
        return termType;
    }

    /**
     * dueDate
     */
    @JasonerProperty("due_date")
    public LocalDate dueDate() {
        return dueDate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private PaymentTermType termType;
        private LocalDate dueDate;

        /**
         * termType
         */
        @JasonerProperty("term_type")
        public Builder termType(PaymentTermType value) {
            this.termType = value;
            return this;
        }

        /**
         * dueDate
         */
        @JasonerProperty("due_date")
        public Builder dueDate(LocalDate value) {
            this.dueDate = value;
            return this;
        }

        public InvoicePaymentTerm build() {
            return new InvoicePaymentTerm(this);
        }

    }

}

