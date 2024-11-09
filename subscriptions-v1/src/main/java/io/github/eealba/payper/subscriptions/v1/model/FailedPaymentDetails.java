package io.github.eealba.payper.subscriptions.v1.model;

import java.time.Instant;
import java.util.Objects;
import io.github.eealba.jasoner.JasonerProperty;

public class FailedPaymentDetails {

    
    private final Money amount;
    
    private final Instant time;
    @JasonerProperty("reason_code")
    private final ReasonCode reasonCode;
    @JasonerProperty("next_payment_retry_time")
    private final Instant nextPaymentRetryTime;

    private FailedPaymentDetails(Builder builder) {
        reasonCode = builder.reasonCode;
        nextPaymentRetryTime = builder.nextPaymentRetryTime;
        amount = Objects.requireNonNull(builder.amount);
        time = Objects.requireNonNull(builder.time);
    }

    
    public Money amount() {
        return amount;
    }

    
    public Instant time() {
        return time;
    }

    @JasonerProperty("reason_code")
    public ReasonCode reasonCode() {
        return reasonCode;
    }

    @JasonerProperty("next_payment_retry_time")
    public Instant nextPaymentRetryTime() {
        return nextPaymentRetryTime;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Money amount;
        private Instant time;
        private ReasonCode reasonCode;
        private Instant nextPaymentRetryTime;

        
        public Builder amount(Money value) {
            this.amount = value;
            return this;
        }

        
        public Builder time(Instant value) {
            this.time = value;
            return this;
        }

        @JasonerProperty("reason_code")
        public Builder reasonCode(ReasonCode value) {
            this.reasonCode = value;
            return this;
        }

        @JasonerProperty("next_payment_retry_time")
        public Builder nextPaymentRetryTime(Instant value) {
            this.nextPaymentRetryTime = value;
            return this;
        }

        public FailedPaymentDetails build() {
            return new FailedPaymentDetails(this);
        }

    }
    /**
     * The reason code for the payment failure.
     */
    public enum ReasonCode {
        PAYMENT_DENIED,
        INTERNAL_SERVER_ERROR,
        PAYEE_ACCOUNT_RESTRICTED,
        PAYER_ACCOUNT_RESTRICTED,
        PAYER_CANNOT_PAY,
        SENDING_LIMIT_EXCEEDED,
        TRANSACTION_RECEIVING_LIMIT_EXCEEDED,
        CURRENCY_MISMATCH
    }
}

