package io.github.eealba.payper.invoices.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The payment term of the invoice. Payment can be due upon receipt, a specified date, or in a set number of days.
 */
public class PaymentTerm {


    @JasonerProperty("term_type")
    private final PaymentTermType termType;

    private PaymentTerm(Builder builder) {
        termType = builder.termType;
    }

    /**
     * termType
     */
    @JasonerProperty("term_type")
    public PaymentTermType termType() {
        return termType;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private PaymentTermType termType;

        /**
         * termType
         */
        @JasonerProperty("term_type")
        public Builder termType(PaymentTermType value) {
            this.termType = value;
            return this;
        }

        public PaymentTerm build() {
            return new PaymentTerm(this);
        }

    }

}

