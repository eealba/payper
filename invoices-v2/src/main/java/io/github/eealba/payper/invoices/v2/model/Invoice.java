package io.github.eealba.payper.invoices.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * The invoice details which includes all information of the invoice like items, billing information.
 */
public class Invoice {


    
    private final String id;
    @JasonerProperty("parent_id")
    private final String parentId;
    
    private final InvoiceStatus status;
    
    private final InvoiceDetail detail;
    
    private final InvoicerInfo invoicer;
    @JasonerProperty("primary_recipients")
    private final List<RecipientInfo> primaryRecipients;
    @JasonerProperty("additional_recipients")
    private final List<Email> additionalRecipients;
    
    private final List<Item> items;
    
    private final Configuration configuration;
    
    private final AmountSummaryDetail amount;
    @JasonerProperty("due_amount")
    private final Money dueAmount;
    
    private final Money gratuity;
    
    private final Payments payments;
    
    private final Refunds refunds;
    
    private final List<LinkDescription> links;

    private Invoice(Builder builder) {
        id = builder.id;
        parentId = builder.parentId;
        status = builder.status;
        detail = builder.detail;
        invoicer = builder.invoicer;
        primaryRecipients = builder.primaryRecipients;
        additionalRecipients = builder.additionalRecipients;
        items = builder.items;
        configuration = builder.configuration;
        amount = builder.amount;
        dueAmount = builder.dueAmount;
        gratuity = builder.gratuity;
        payments = builder.payments;
        refunds = builder.refunds;
        links = builder.links;
    }

    /**
     * The ID of the invoice.
     */
    
    public String id() {
        return id;
    }

    /**
     * The parent ID to an invoice that defines the group invoice to which the invoice is related.
     */
    @JasonerProperty("parent_id")
    public String parentId() {
        return parentId;
    }

    /**
     * status
     */
    
    public InvoiceStatus status() {
        return status;
    }

    /**
     * detail
     */
    
    public InvoiceDetail detail() {
        return detail;
    }

    /**
     * invoicer
     */
    
    public InvoicerInfo invoicer() {
        return invoicer;
    }

    /**
     * The billing and shipping information. Includes name, email, address, phone and language.
     */
    @JasonerProperty("primary_recipients")
    public List<RecipientInfo> primaryRecipients() {
        return primaryRecipients;
    }

    /**
     * An array of one or more CC: emails to which notifications are sent. If you omit this parameter, a notification 
is sent to all CC: email addresses that are part of the invoice.<blockquote><strong>Note:</strong> Valid 
values are email addresses in the `additional_recipients` value associated with the invoice.</blockquote>
     */
    @JasonerProperty("additional_recipients")
    public List<Email> additionalRecipients() {
        return additionalRecipients;
    }

    /**
     * An array of invoice line item information.
     */
    
    public List<Item> items() {
        return items;
    }

    /**
     * configuration
     */
    
    public Configuration configuration() {
        return configuration;
    }

    /**
     * amount
     */
    
    public AmountSummaryDetail amount() {
        return amount;
    }

    /**
     * dueAmount
     */
    @JasonerProperty("due_amount")
    public Money dueAmount() {
        return dueAmount;
    }

    /**
     * gratuity
     */
    
    public Money gratuity() {
        return gratuity;
    }

    /**
     * payments
     */
    
    public Payments payments() {
        return payments;
    }

    /**
     * refunds
     */
    
    public Refunds refunds() {
        return refunds;
    }

    /**
     * An array of request-related [HATEOAS links](/docs/api/reference/api-responses/#hateoas-links).
     */
    
    public List<LinkDescription> links() {
        return links;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String id;
        private String parentId;
        private InvoiceStatus status;
        private InvoiceDetail detail;
        private InvoicerInfo invoicer;
        private List<RecipientInfo> primaryRecipients;
        private List<Email> additionalRecipients;
        private List<Item> items;
        private Configuration configuration;
        private AmountSummaryDetail amount;
        private Money dueAmount;
        private Money gratuity;
        private Payments payments;
        private Refunds refunds;
        private List<LinkDescription> links;

        /**
         * The ID of the invoice.
         */
        
        public Builder id(String value) {
            this.id = value;
            return this;
        }

        /**
         * The parent ID to an invoice that defines the group invoice to which the invoice is related.
         */
        @JasonerProperty("parent_id")
        public Builder parentId(String value) {
            this.parentId = value;
            return this;
        }

        /**
         * status
         */
        
        public Builder status(InvoiceStatus value) {
            this.status = value;
            return this;
        }

        /**
         * detail
         */
        
        public Builder detail(InvoiceDetail value) {
            this.detail = value;
            return this;
        }

        /**
         * invoicer
         */
        
        public Builder invoicer(InvoicerInfo value) {
            this.invoicer = value;
            return this;
        }

        /**
         * The billing and shipping information. Includes name, email, address, phone and language.
         */
        @JasonerProperty("primary_recipients")
        public Builder primaryRecipients(List<RecipientInfo> value) {
            this.primaryRecipients = value;
            return this;
        }

        /**
         * An array of one or more CC: emails to which notifications are sent. If you omit this parameter, a notification 
is sent to all CC: email addresses that are part of the invoice.<blockquote><strong>Note:</strong> Valid 
values are email addresses in the `additional_recipients` value associated with the invoice.</blockquote>
         */
        @JasonerProperty("additional_recipients")
        public Builder additionalRecipients(List<Email> value) {
            this.additionalRecipients = value;
            return this;
        }

        /**
         * An array of invoice line item information.
         */
        
        public Builder items(List<Item> value) {
            this.items = value;
            return this;
        }

        /**
         * configuration
         */
        
        public Builder configuration(Configuration value) {
            this.configuration = value;
            return this;
        }

        /**
         * amount
         */
        
        public Builder amount(AmountSummaryDetail value) {
            this.amount = value;
            return this;
        }

        /**
         * dueAmount
         */
        @JasonerProperty("due_amount")
        public Builder dueAmount(Money value) {
            this.dueAmount = value;
            return this;
        }

        /**
         * gratuity
         */
        
        public Builder gratuity(Money value) {
            this.gratuity = value;
            return this;
        }

        /**
         * payments
         */
        
        public Builder payments(Payments value) {
            this.payments = value;
            return this;
        }

        /**
         * refunds
         */
        
        public Builder refunds(Refunds value) {
            this.refunds = value;
            return this;
        }

        /**
         * An array of request-related [HATEOAS links](/docs/api/reference/api-responses/#hateoas-links).
         */
        
        public Builder links(List<LinkDescription> value) {
            this.links = value;
            return this;
        }

        public Invoice build() {
            return new Invoice(this);
        }

    }

}

