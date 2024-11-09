package io.github.eealba.payper.subscriptions.v1.model;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import io.github.eealba.jasoner.JasonerProperty;

public class SubscriptionBillingInfo {

    @JasonerProperty("outstanding_balance")
    private final Money outstandingBalance;
    @JasonerProperty("cycle_executions")
    private final List<CycleExecution> cycleExecutions;
    @JasonerProperty("last_payment")
    private final LastPaymentDetails lastPayment;
    @JasonerProperty("next_billing_time")
    private final Instant nextBillingTime;
    @JasonerProperty("final_payment_time")
    private final Instant finalPaymentTime;
    @JasonerProperty("failed_payments_count")
    private final Integer failedPaymentsCount;
    @JasonerProperty("last_failed_payment")
    private final FailedPaymentDetails lastFailedPayment;

    private SubscriptionBillingInfo(Builder builder) {
        cycleExecutions = builder.cycleExecutions;
        lastPayment = builder.lastPayment;
        nextBillingTime = builder.nextBillingTime;
        finalPaymentTime = builder.finalPaymentTime;
        lastFailedPayment = builder.lastFailedPayment;
        outstandingBalance = Objects.requireNonNull(builder.outstandingBalance);
        failedPaymentsCount = Objects.requireNonNull(builder.failedPaymentsCount);
    }

    @JasonerProperty("outstanding_balance")
    public Money outstandingBalance() {
        return outstandingBalance;
    }

    @JasonerProperty("cycle_executions")
    public List<CycleExecution> cycleExecutions() {
        return cycleExecutions;
    }

    @JasonerProperty("last_payment")
    public LastPaymentDetails lastPayment() {
        return lastPayment;
    }

    @JasonerProperty("next_billing_time")
    public Instant nextBillingTime() {
        return nextBillingTime;
    }

    @JasonerProperty("final_payment_time")
    public Instant finalPaymentTime() {
        return finalPaymentTime;
    }

    @JasonerProperty("failed_payments_count")
    public Integer failedPaymentsCount() {
        return failedPaymentsCount;
    }

    @JasonerProperty("last_failed_payment")
    public FailedPaymentDetails lastFailedPayment() {
        return lastFailedPayment;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Money outstandingBalance;
        private List<CycleExecution> cycleExecutions;
        private LastPaymentDetails lastPayment;
        private Instant nextBillingTime;
        private Instant finalPaymentTime;
        private Integer failedPaymentsCount;
        private FailedPaymentDetails lastFailedPayment;

        @JasonerProperty("outstanding_balance")
        public Builder outstandingBalance(Money value) {
            this.outstandingBalance = value;
            return this;
        }

        @JasonerProperty("cycle_executions")
        public Builder cycleExecutions(List<CycleExecution> value) {
            this.cycleExecutions = value;
            return this;
        }

        @JasonerProperty("last_payment")
        public Builder lastPayment(LastPaymentDetails value) {
            this.lastPayment = value;
            return this;
        }

        @JasonerProperty("next_billing_time")
        public Builder nextBillingTime(Instant value) {
            this.nextBillingTime = value;
            return this;
        }

        @JasonerProperty("final_payment_time")
        public Builder finalPaymentTime(Instant value) {
            this.finalPaymentTime = value;
            return this;
        }

        @JasonerProperty("failed_payments_count")
        public Builder failedPaymentsCount(Integer value) {
            this.failedPaymentsCount = value;
            return this;
        }

        @JasonerProperty("last_failed_payment")
        public Builder lastFailedPayment(FailedPaymentDetails value) {
            this.lastFailedPayment = value;
            return this;
        }

        public SubscriptionBillingInfo build() {
            return new SubscriptionBillingInfo(this);
        }

    }

}

