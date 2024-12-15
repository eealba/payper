package io.github.eealba.payper.subscriptions.v1.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.time.Instant;

/**
 * The transaction details.
 */
public class Transaction {


    
    private final Status status;
    @JasonerProperty("status_details")
    private final CaptureStatusDetails statusDetails;
    
    private final String id;
    @JasonerProperty("amount_with_breakdown")
    private final AmountWithBreakdown amountWithBreakdown;
    @JasonerProperty("payer_name")
    private final Name payerName;
    @JasonerProperty("payer_email")
    private final EmailAddress payerEmail;
    
    private final Instant time;

    private Transaction(Builder builder) {
        status = builder.status;
        statusDetails = builder.statusDetails;
        id = builder.id;
        amountWithBreakdown = builder.amountWithBreakdown;
        payerName = builder.payerName;
        payerEmail = builder.payerEmail;
        time = builder.time;

    }

    /**
     * The status of the captured payment.
     */
    
    public Status status() {
        return status;
    }

    /**
     * statusDetails
     */
    @JasonerProperty("status_details")
    public CaptureStatusDetails statusDetails() {
        return statusDetails;
    }

    /**
     * The PayPal-generated transaction ID.
     */
    
    public String id() {
        return id;
    }

    /**
     * amountWithBreakdown
     */
    @JasonerProperty("amount_with_breakdown")
    public AmountWithBreakdown amountWithBreakdown() {
        return amountWithBreakdown;
    }

    /**
     * payerName
     */
    @JasonerProperty("payer_name")
    public Name payerName() {
        return payerName;
    }

    /**
     * payerEmail
     */
    @JasonerProperty("payer_email")
    public EmailAddress payerEmail() {
        return payerEmail;
    }

    /**
     * time
     */
    
    public Instant time() {
        return time;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Status status;
        private CaptureStatusDetails statusDetails;
        private String id;
        private AmountWithBreakdown amountWithBreakdown;
        private Name payerName;
        private EmailAddress payerEmail;
        private Instant time;

        /**
         * The status of the captured payment.
         */
        
        public Builder status(Status value) {
            this.status = value;
            return this;
        }

        /**
         * statusDetails
         */
        @JasonerProperty("status_details")
        public Builder statusDetails(CaptureStatusDetails value) {
            this.statusDetails = value;
            return this;
        }

        /**
         * The PayPal-generated transaction ID.
         */
        
        public Builder id(String value) {
            this.id = value;
            return this;
        }

        /**
         * amountWithBreakdown
         */
        @JasonerProperty("amount_with_breakdown")
        public Builder amountWithBreakdown(AmountWithBreakdown value) {
            this.amountWithBreakdown = value;
            return this;
        }

        /**
         * payerName
         */
        @JasonerProperty("payer_name")
        public Builder payerName(Name value) {
            this.payerName = value;
            return this;
        }

        /**
         * payerEmail
         */
        @JasonerProperty("payer_email")
        public Builder payerEmail(EmailAddress value) {
            this.payerEmail = value;
            return this;
        }

        /**
         * time
         */
        
        public Builder time(Instant value) {
            this.time = value;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }

    }
    /**
     * The status of the captured payment.
     */
    public enum Status {
        COMPLETED,
        DECLINED,
        PARTIALLY_REFUNDED,
        PENDING,
        REFUNDED
    }
}

