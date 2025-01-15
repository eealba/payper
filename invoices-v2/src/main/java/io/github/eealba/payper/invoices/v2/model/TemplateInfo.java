package io.github.eealba.payper.invoices.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * The template details. Includes invoicer business information, invoice recipients, items, and configuration.
 */
public class TemplateInfo {


    
    private final TemplateDetail detail;
    
    private final InvoicerInfo invoicer;
    @JasonerProperty("primary_recipients")
    private final List<RecipientInfo> primaryRecipients;
    @JasonerProperty("additional_recipients")
    private final List<Email> additionalRecipients;
    
    private final List<Item> items;
    
    private final TemplateConfiguration configuration;
    
    private final AmountSummaryDetail amount;
    @JasonerProperty("due_amount")
    private final Money dueAmount;

    private TemplateInfo(Builder builder) {
        detail = builder.detail;
        invoicer = builder.invoicer;
        primaryRecipients = builder.primaryRecipients;
        additionalRecipients = builder.additionalRecipients;
        items = builder.items;
        configuration = builder.configuration;
        amount = builder.amount;
        dueAmount = builder.dueAmount;

    }

    /**
     * detail
     */
    
    public TemplateDetail detail() {
        return detail;
    }

    /**
     * invoicer
     */
    
    public InvoicerInfo invoicer() {
        return invoicer;
    }

    /**
     * The billing and shipping information. Includes name, email, address, phone, and language.
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
     * An array of invoice line-item information.
     */
    
    public List<Item> items() {
        return items;
    }

    /**
     * configuration
     */
    
    public TemplateConfiguration configuration() {
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private TemplateDetail detail;
        private InvoicerInfo invoicer;
        private List<RecipientInfo> primaryRecipients;
        private List<Email> additionalRecipients;
        private List<Item> items;
        private TemplateConfiguration configuration;
        private AmountSummaryDetail amount;
        private Money dueAmount;

        /**
         * detail
         */
        
        public Builder detail(TemplateDetail value) {
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
         * The billing and shipping information. Includes name, email, address, phone, and language.
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
         * An array of invoice line-item information.
         */
        
        public Builder items(List<Item> value) {
            this.items = value;
            return this;
        }

        /**
         * configuration
         */
        
        public Builder configuration(TemplateConfiguration value) {
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

        public TemplateInfo build() {
            return new TemplateInfo(this);
        }

    }

}

