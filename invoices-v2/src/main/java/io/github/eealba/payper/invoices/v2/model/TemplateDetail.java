package io.github.eealba.payper.invoices.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * The template-related details. Includes notes, terms and conditions, memo, and attachments.
 */
public class TemplateDetail {


    
    private final String reference;
    @JasonerProperty("currency_code")
    private final CurrencyCode currencyCode;
    
    private final String note;
    @JasonerProperty("terms_and_conditions")
    private final String termsAndConditions;
    
    private final String memo;
    
    private final List<FileReference> attachments;
    @JasonerProperty("payment_term")
    private final PaymentTerm paymentTerm;
    
    private final TemplateMetadata metadata;

    private TemplateDetail(Builder builder) {
        reference = builder.reference;
        currencyCode = builder.currencyCode;
        note = builder.note;
        termsAndConditions = builder.termsAndConditions;
        memo = builder.memo;
        attachments = builder.attachments;
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
     * paymentTerm
     */
    @JasonerProperty("payment_term")
    public PaymentTerm paymentTerm() {
        return paymentTerm;
    }

    /**
     * metadata
     */
    
    public TemplateMetadata metadata() {
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
        private PaymentTerm paymentTerm;
        private TemplateMetadata metadata;

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
         * paymentTerm
         */
        @JasonerProperty("payment_term")
        public Builder paymentTerm(PaymentTerm value) {
            this.paymentTerm = value;
            return this;
        }

        /**
         * metadata
         */
        
        public Builder metadata(TemplateMetadata value) {
            this.metadata = value;
            return this;
        }

        public TemplateDetail build() {
            return new TemplateDetail(this);
        }

    }

}

