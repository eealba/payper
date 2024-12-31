package io.github.eealba.payper.invoices.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The invoice number.
 */
public class InvoiceNumber {


    @JasonerProperty("invoice_number")
    private final String invoiceNumber;

    private InvoiceNumber(Builder builder) {
        invoiceNumber = builder.invoiceNumber;

    }

    /**
     * The invoice number. If you omit this value, the default is the auto-incremented number from the last number.
     */
    @JasonerProperty("invoice_number")
    public String invoiceNumber() {
        return invoiceNumber;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String invoiceNumber;

        /**
         * The invoice number. If you omit this value, the default is the auto-incremented number from the last number.
         */
        @JasonerProperty("invoice_number")
        public Builder invoiceNumber(String value) {
            this.invoiceNumber = value;
            return this;
        }

        public InvoiceNumber build() {
            return new InvoiceNumber(this);
        }

    }

}

