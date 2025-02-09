package io.github.eealba.payper.invoices.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.time.Instant;

/**
 * The audit metadata. Captures all invoicing actions on create, send, update, and cancel.
 */
public class Metadata {


    @JasonerProperty("create_time")
    private final Instant createTime;
    @JasonerProperty("created_by")
    private final String createdBy;
    @JasonerProperty("last_update_time")
    private final Instant lastUpdateTime;
    @JasonerProperty("last_updated_by")
    private final String lastUpdatedBy;
    @JasonerProperty("cancel_time")
    private final Instant cancelTime;
    @JasonerProperty("cancelled_by")
    private final String cancelledBy;
    @JasonerProperty("first_sent_time")
    private final Instant firstSentTime;
    @JasonerProperty("last_sent_time")
    private final Instant lastSentTime;
    @JasonerProperty("last_sent_by")
    private final String lastSentBy;
    @JasonerProperty("created_by_flow")
    private final InvoiceCreationFlow createdByFlow;
    @JasonerProperty("recipient_view_url")
    private final String recipientViewUrl;
    @JasonerProperty("invoicer_view_url")
    private final String invoicerViewUrl;

    private Metadata(Builder builder) {
        createTime = builder.createTime;
        createdBy = builder.createdBy;
        lastUpdateTime = builder.lastUpdateTime;
        lastUpdatedBy = builder.lastUpdatedBy;
        cancelTime = builder.cancelTime;
        cancelledBy = builder.cancelledBy;
        firstSentTime = builder.firstSentTime;
        lastSentTime = builder.lastSentTime;
        lastSentBy = builder.lastSentBy;
        createdByFlow = builder.createdByFlow;
        recipientViewUrl = builder.recipientViewUrl;
        invoicerViewUrl = builder.invoicerViewUrl;
    }

    /**
     * createTime
     */
    @JasonerProperty("create_time")
    public Instant createTime() {
        return createTime;
    }

    /**
     * The email address of the account that created the resource.
     */
    @JasonerProperty("created_by")
    public String createdBy() {
        return createdBy;
    }

    /**
     * lastUpdateTime
     */
    @JasonerProperty("last_update_time")
    public Instant lastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * The email address of the account that last edited the resource.
     */
    @JasonerProperty("last_updated_by")
    public String lastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * cancelTime
     */
    @JasonerProperty("cancel_time")
    public Instant cancelTime() {
        return cancelTime;
    }

    /**
     * The actor who canceled the resource.
     */
    @JasonerProperty("cancelled_by")
    public String cancelledBy() {
        return cancelledBy;
    }

    /**
     * firstSentTime
     */
    @JasonerProperty("first_sent_time")
    public Instant firstSentTime() {
        return firstSentTime;
    }

    /**
     * lastSentTime
     */
    @JasonerProperty("last_sent_time")
    public Instant lastSentTime() {
        return lastSentTime;
    }

    /**
     * The email address of the account that last sent the resource.
     */
    @JasonerProperty("last_sent_by")
    public String lastSentBy() {
        return lastSentBy;
    }

    /**
     * createdByFlow
     */
    @JasonerProperty("created_by_flow")
    public InvoiceCreationFlow createdByFlow() {
        return createdByFlow;
    }

    /**
     * The URL for the invoice payer view hosted on paypal.com.
     */
    @JasonerProperty("recipient_view_url")
    public String recipientViewUrl() {
        return recipientViewUrl;
    }

    /**
     * The URL for the invoice merchant view hosted on paypal.com.
     */
    @JasonerProperty("invoicer_view_url")
    public String invoicerViewUrl() {
        return invoicerViewUrl;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Instant createTime;
        private String createdBy;
        private Instant lastUpdateTime;
        private String lastUpdatedBy;
        private Instant cancelTime;
        private String cancelledBy;
        private Instant firstSentTime;
        private Instant lastSentTime;
        private String lastSentBy;
        private InvoiceCreationFlow createdByFlow;
        private String recipientViewUrl;
        private String invoicerViewUrl;

        /**
         * createTime
         */
        @JasonerProperty("create_time")
        public Builder createTime(Instant value) {
            this.createTime = value;
            return this;
        }

        /**
         * The email address of the account that created the resource.
         */
        @JasonerProperty("created_by")
        public Builder createdBy(String value) {
            this.createdBy = value;
            return this;
        }

        /**
         * lastUpdateTime
         */
        @JasonerProperty("last_update_time")
        public Builder lastUpdateTime(Instant value) {
            this.lastUpdateTime = value;
            return this;
        }

        /**
         * The email address of the account that last edited the resource.
         */
        @JasonerProperty("last_updated_by")
        public Builder lastUpdatedBy(String value) {
            this.lastUpdatedBy = value;
            return this;
        }

        /**
         * cancelTime
         */
        @JasonerProperty("cancel_time")
        public Builder cancelTime(Instant value) {
            this.cancelTime = value;
            return this;
        }

        /**
         * The actor who canceled the resource.
         */
        @JasonerProperty("cancelled_by")
        public Builder cancelledBy(String value) {
            this.cancelledBy = value;
            return this;
        }

        /**
         * firstSentTime
         */
        @JasonerProperty("first_sent_time")
        public Builder firstSentTime(Instant value) {
            this.firstSentTime = value;
            return this;
        }

        /**
         * lastSentTime
         */
        @JasonerProperty("last_sent_time")
        public Builder lastSentTime(Instant value) {
            this.lastSentTime = value;
            return this;
        }

        /**
         * The email address of the account that last sent the resource.
         */
        @JasonerProperty("last_sent_by")
        public Builder lastSentBy(String value) {
            this.lastSentBy = value;
            return this;
        }

        /**
         * createdByFlow
         */
        @JasonerProperty("created_by_flow")
        public Builder createdByFlow(InvoiceCreationFlow value) {
            this.createdByFlow = value;
            return this;
        }

        /**
         * The URL for the invoice payer view hosted on paypal.com.
         */
        @JasonerProperty("recipient_view_url")
        public Builder recipientViewUrl(String value) {
            this.recipientViewUrl = value;
            return this;
        }

        /**
         * The URL for the invoice merchant view hosted on paypal.com.
         */
        @JasonerProperty("invoicer_view_url")
        public Builder invoicerViewUrl(String value) {
            this.invoicerViewUrl = value;
            return this;
        }

        public Metadata build() {
            return new Metadata(this);
        }

    }

}

