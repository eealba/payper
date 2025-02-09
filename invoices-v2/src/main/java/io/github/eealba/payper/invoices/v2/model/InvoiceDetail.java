package io.github.eealba.payper.invoices.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.time.LocalDate;
import java.util.List;

/**
 * The details of the invoice. Includes invoice number, date, payment terms, and audit metadata.
 */
public class InvoiceDetail {


    
    private final String reference;
    @JasonerProperty("currency_code")
    private final CurrencyCode currencyCode;
    
    private final String note;
    @JasonerProperty("terms_and_conditions")
    private final String termsAndConditions;
    
    private final String memo;
    
    private final List<FileReference> attachments;
    @JasonerProperty("invoice_number")
    private final String invoiceNumber;
    @JasonerProperty("invoice_date")
    private final LocalDate invoiceDate;
    @JasonerProperty("payment_term")
    private final InvoicePaymentTerm paymentTerm;
    
    private final Metadata metadata;

    private InvoiceDetail(Builder builder) {
        reference = builder.reference;
        currencyCode = builder.currencyCode;
        note = builder.note;
        termsAndConditions = builder.termsAndConditions;
        memo = builder.memo;
        attachments = builder.attachments;
        invoiceNumber = builder.invoiceNumber;
        invoiceDate = builder.invoiceDate;
        paymentTerm = builder.paymentTerm;
        metadata = builder.metadata;
    }

    /**
     * The reference data. Includes a post office (PO) number.
     */
    
    public String reference() {
        return reference;
    }

    /**
     * currencyCode
     */
    @JasonerProperty("currency_code")
    public CurrencyCode currencyCode() {
        return currencyCode;
    }

    /**
     * A note to the invoice recipient. Also appears on the invoice notification email.
     */
    
    public String note() {
        return note;
    }

    /**
     * The general terms of the invoice. Can include return or cancellation policy and other terms and conditions.
     */
    @JasonerProperty("terms_and_conditions")
    public String termsAndConditions() {
        return termsAndConditions;
    }

    /**
     * A private bookkeeping memo for the user.
     */
    
    public String memo() {
        return memo;
    }

    /**
     * An array of PayPal IDs for the files that are attached to an invoice.
     */
    
    public List<FileReference> attachments() {
        return attachments;
    }

    /**
     * The invoice number. Default is the number that is auto-incremented number from the last number.
     */
    @JasonerProperty("invoice_number")
    public String invoiceNumber() {
        return invoiceNumber;
    }

    /**
     * invoiceDate
     */
    @JasonerProperty("invoice_date")
    public LocalDate invoiceDate() {
        return invoiceDate;
    }

    /**
     * paymentTerm
     */
    @JasonerProperty("payment_term")
    public InvoicePaymentTerm paymentTerm() {
        return paymentTerm;
    }

    /**
     * metadata
     */
    
    public Metadata metadata() {
        return metadata;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String reference;
        private CurrencyCode currencyCode;
        private String note;
        private String termsAndConditions;
        private String memo;
        private List<FileReference> attachments;
        private String invoiceNumber;
        private LocalDate invoiceDate;
        private InvoicePaymentTerm paymentTerm;
        private Metadata metadata;

        /**
         * The reference data. Includes a post office (PO) number.
         */
        
        public Builder reference(String value) {
            this.reference = value;
            return this;
        }

        /**
         * currencyCode
         */
        @JasonerProperty("currency_code")
        public Builder currencyCode(CurrencyCode value) {
            this.currencyCode = value;
            return this;
        }

        /**
         * A note to the invoice recipient. Also appears on the invoice notification email.
         */
        
        public Builder note(String value) {
            this.note = value;
            return this;
        }

        /**
         * The general terms of the invoice. Can include return or cancellation policy and other terms and conditions.
         */
        @JasonerProperty("terms_and_conditions")
        public Builder termsAndConditions(String value) {
            this.termsAndConditions = value;
            return this;
        }

        /**
         * A private bookkeeping memo for the user.
         */
        
        public Builder memo(String value) {
            this.memo = value;
            return this;
        }

        /**
         * An array of PayPal IDs for the files that are attached to an invoice.
         */
        
        public Builder attachments(List<FileReference> value) {
            this.attachments = value;
            return this;
        }

        /**
         * The invoice number. Default is the number that is auto-incremented number from the last number.
         */
        @JasonerProperty("invoice_number")
        public Builder invoiceNumber(String value) {
            this.invoiceNumber = value;
            return this;
        }

        /**
         * invoiceDate
         */
        @JasonerProperty("invoice_date")
        public Builder invoiceDate(LocalDate value) {
            this.invoiceDate = value;
            return this;
        }

        /**
         * paymentTerm
         */
        @JasonerProperty("payment_term")
        public Builder paymentTerm(InvoicePaymentTerm value) {
            this.paymentTerm = value;
            return this;
        }

        /**
         * metadata
         */
        
        public Builder metadata(Metadata value) {
            this.metadata = value;
            return this;
        }

        public InvoiceDetail build() {
            return new InvoiceDetail(this);
        }

    }

}

