package io.github.eealba.payper.subscriptions.v1.model;

import java.time.Instant;
import io.github.eealba.jasoner.JasonerProperty;

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

    
    public Status status() {
        return status;
    }

    @JasonerProperty("status_details")
    public CaptureStatusDetails statusDetails() {
        return statusDetails;
    }

    
    public String id() {
        return id;
    }

    @JasonerProperty("amount_with_breakdown")
    public AmountWithBreakdown amountWithBreakdown() {
        return amountWithBreakdown;
    }

    @JasonerProperty("payer_name")
    public Name payerName() {
        return payerName;
    }

    @JasonerProperty("payer_email")
    public EmailAddress payerEmail() {
        return payerEmail;
    }

    
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

        
        public Builder status(Status value) {
            status = value;
            return this;
        }

        @JasonerProperty("status_details")
        public Builder statusDetails(CaptureStatusDetails value) {
            statusDetails = value;
            return this;
        }

        
        public Builder id(String value) {
            id = value;
            return this;
        }

        @JasonerProperty("amount_with_breakdown")
        public Builder amountWithBreakdown(AmountWithBreakdown value) {
            amountWithBreakdown = value;
            return this;
        }

        @JasonerProperty("payer_name")
        public Builder payerName(Name value) {
            payerName = value;
            return this;
        }

        @JasonerProperty("payer_email")
        public Builder payerEmail(EmailAddress value) {
            payerEmail = value;
            return this;
        }

        
        public Builder time(Instant value) {
            time = value;
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

