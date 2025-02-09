package io.github.eealba.payper.invoices.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * The invoice search parameters.
 */
public class SearchData {


    @JasonerProperty("recipient_email")
    private final String recipientEmail;
    @JasonerProperty("recipient_first_name")
    private final String recipientFirstName;
    @JasonerProperty("recipient_last_name")
    private final String recipientLastName;
    @JasonerProperty("recipient_business_name")
    private final String recipientBusinessName;
    @JasonerProperty("invoice_number")
    private final String invoiceNumber;
    
    private final List<InvoiceStatus> status;
    
    private final String reference;
    @JasonerProperty("currency_code")
    private final CurrencyCode currencyCode;
    
    private final String memo;
    @JasonerProperty("total_amount_range")
    private final AmountRange totalAmountRange;
    @JasonerProperty("invoice_date_range")
    private final DateRange invoiceDateRange;
    @JasonerProperty("due_date_range")
    private final DateRange dueDateRange;
    @JasonerProperty("payment_date_range")
    private final DateTimeRange paymentDateRange;
    @JasonerProperty("creation_date_range")
    private final DateTimeRange creationDateRange;
    
    private final Boolean archived;
    
    private final List<String> fields;

    private SearchData(Builder builder) {
        recipientEmail = builder.recipientEmail;
        recipientFirstName = builder.recipientFirstName;
        recipientLastName = builder.recipientLastName;
        recipientBusinessName = builder.recipientBusinessName;
        invoiceNumber = builder.invoiceNumber;
        status = builder.status;
        reference = builder.reference;
        currencyCode = builder.currencyCode;
        memo = builder.memo;
        totalAmountRange = builder.totalAmountRange;
        invoiceDateRange = builder.invoiceDateRange;
        dueDateRange = builder.dueDateRange;
        paymentDateRange = builder.paymentDateRange;
        creationDateRange = builder.creationDateRange;
        archived = builder.archived;
        fields = builder.fields;
    }

    /**
     * Filters the search by the email address.
     */
    @JasonerProperty("recipient_email")
    public String recipientEmail() {
        return recipientEmail;
    }

    /**
     * Filters the search by the recipient first name.
     */
    @JasonerProperty("recipient_first_name")
    public String recipientFirstName() {
        return recipientFirstName;
    }

    /**
     * Filters the search by the recipient last name.
     */
    @JasonerProperty("recipient_last_name")
    public String recipientLastName() {
        return recipientLastName;
    }

    /**
     * Filters the search by the recipient business name.
     */
    @JasonerProperty("recipient_business_name")
    public String recipientBusinessName() {
        return recipientBusinessName;
    }

    /**
     * Filters the search by the invoice number.
     */
    @JasonerProperty("invoice_number")
    public String invoiceNumber() {
        return invoiceNumber;
    }

    /**
     * An array of status values.
     */
    
    public List<InvoiceStatus> status() {
        return status;
    }

    /**
     * The reference data, such as a PO number.
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
     * A private bookkeeping memo for the user.
     */
    
    public String memo() {
        return memo;
    }

    /**
     * totalAmountRange
     */
    @JasonerProperty("total_amount_range")
    public AmountRange totalAmountRange() {
        return totalAmountRange;
    }

    /**
     * invoiceDateRange
     */
    @JasonerProperty("invoice_date_range")
    public DateRange invoiceDateRange() {
        return invoiceDateRange;
    }

    /**
     * dueDateRange
     */
    @JasonerProperty("due_date_range")
    public DateRange dueDateRange() {
        return dueDateRange;
    }

    /**
     * paymentDateRange
     */
    @JasonerProperty("payment_date_range")
    public DateTimeRange paymentDateRange() {
        return paymentDateRange;
    }

    /**
     * creationDateRange
     */
    @JasonerProperty("creation_date_range")
    public DateTimeRange creationDateRange() {
        return creationDateRange;
    }

    /**
     * Indicates whether to list merchant-archived invoices in the response. Value is:<ul><li><code>true</code>. 
Response lists only merchant-archived invoices.</li><li><code>false</code>. Response lists only unarchived 
invoices.</li><li><code>null</code>. Response lists all invoices.</li></ul>
     */
    
    public Boolean archived() {
        return archived;
    }

    /**
     * A CSV file of fields to return for the user, if available. Because the invoice object can be very large, field 
filtering is required. Valid collection fields are <code>items</code>, <code>payments</code>, 
<code>refunds</code>, <code>additional_recipients_info</code>, and <code>attachments</code>.
     */
    
    public List<String> fields() {
        return fields;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String recipientEmail;
        private String recipientFirstName;
        private String recipientLastName;
        private String recipientBusinessName;
        private String invoiceNumber;
        private List<InvoiceStatus> status;
        private String reference;
        private CurrencyCode currencyCode;
        private String memo;
        private AmountRange totalAmountRange;
        private DateRange invoiceDateRange;
        private DateRange dueDateRange;
        private DateTimeRange paymentDateRange;
        private DateTimeRange creationDateRange;
        private Boolean archived;
        private List<String> fields;

        /**
         * Filters the search by the email address.
         */
        @JasonerProperty("recipient_email")
        public Builder recipientEmail(String value) {
            this.recipientEmail = value;
            return this;
        }

        /**
         * Filters the search by the recipient first name.
         */
        @JasonerProperty("recipient_first_name")
        public Builder recipientFirstName(String value) {
            this.recipientFirstName = value;
            return this;
        }

        /**
         * Filters the search by the recipient last name.
         */
        @JasonerProperty("recipient_last_name")
        public Builder recipientLastName(String value) {
            this.recipientLastName = value;
            return this;
        }

        /**
         * Filters the search by the recipient business name.
         */
        @JasonerProperty("recipient_business_name")
        public Builder recipientBusinessName(String value) {
            this.recipientBusinessName = value;
            return this;
        }

        /**
         * Filters the search by the invoice number.
         */
        @JasonerProperty("invoice_number")
        public Builder invoiceNumber(String value) {
            this.invoiceNumber = value;
            return this;
        }

        /**
         * An array of status values.
         */
        
        public Builder status(List<InvoiceStatus> value) {
            this.status = value;
            return this;
        }

        /**
         * The reference data, such as a PO number.
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
         * A private bookkeeping memo for the user.
         */
        
        public Builder memo(String value) {
            this.memo = value;
            return this;
        }

        /**
         * totalAmountRange
         */
        @JasonerProperty("total_amount_range")
        public Builder totalAmountRange(AmountRange value) {
            this.totalAmountRange = value;
            return this;
        }

        /**
         * invoiceDateRange
         */
        @JasonerProperty("invoice_date_range")
        public Builder invoiceDateRange(DateRange value) {
            this.invoiceDateRange = value;
            return this;
        }

        /**
         * dueDateRange
         */
        @JasonerProperty("due_date_range")
        public Builder dueDateRange(DateRange value) {
            this.dueDateRange = value;
            return this;
        }

        /**
         * paymentDateRange
         */
        @JasonerProperty("payment_date_range")
        public Builder paymentDateRange(DateTimeRange value) {
            this.paymentDateRange = value;
            return this;
        }

        /**
         * creationDateRange
         */
        @JasonerProperty("creation_date_range")
        public Builder creationDateRange(DateTimeRange value) {
            this.creationDateRange = value;
            return this;
        }

        /**
         * Indicates whether to list merchant-archived invoices in the response. Value is:<ul><li><code>true</code>. 
Response lists only merchant-archived invoices.</li><li><code>false</code>. Response lists only unarchived 
invoices.</li><li><code>null</code>. Response lists all invoices.</li></ul>
         */
        
        public Builder archived(Boolean value) {
            this.archived = value;
            return this;
        }

        /**
         * A CSV file of fields to return for the user, if available. Because the invoice object can be very large, field 
filtering is required. Valid collection fields are <code>items</code>, <code>payments</code>, 
<code>refunds</code>, <code>additional_recipients_info</code>, and <code>attachments</code>.
         */
        
        public Builder fields(List<String> value) {
            this.fields = value;
            return this;
        }

        public SearchData build() {
            return new SearchData(this);
        }

    }

}

