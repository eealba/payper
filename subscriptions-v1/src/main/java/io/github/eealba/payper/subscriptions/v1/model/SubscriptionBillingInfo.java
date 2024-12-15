package io.github.eealba.payper.subscriptions.v1.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

/**
 * The billing details for the subscription. If the subscription was or is active, these fields are populated.
 */
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

    /**
     * outstandingBalance
     */
    @JasonerProperty("outstanding_balance")
    public Money outstandingBalance() {
        return outstandingBalance;
    }

    /**
     * The trial and regular billing executions.
     */
    @JasonerProperty("cycle_executions")
    public List<CycleExecution> cycleExecutions() {
        return cycleExecutions;
    }

    /**
     * lastPayment
     */
    @JasonerProperty("last_payment")
    public LastPaymentDetails lastPayment() {
        return lastPayment;
    }

    /**
     * nextBillingTime
     */
    @JasonerProperty("next_billing_time")
    public Instant nextBillingTime() {
        return nextBillingTime;
    }

    /**
     * finalPaymentTime
     */
    @JasonerProperty("final_payment_time")
    public Instant finalPaymentTime() {
        return finalPaymentTime;
    }

    /**
     * The number of consecutive payment failures. Resets to `0` after a successful payment. If this reaches the 
`payment_failure_threshold` value, the subscription updates to the `SUSPENDED` state.
     */
    @JasonerProperty("failed_payments_count")
    public Integer failedPaymentsCount() {
        return failedPaymentsCount;
    }

    /**
     * lastFailedPayment
     */
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

        /**
         * outstandingBalance
         */
        @JasonerProperty("outstanding_balance")
        public Builder outstandingBalance(Money value) {
            this.outstandingBalance = value;
            return this;
        }

        /**
         * The trial and regular billing executions.
         */
        @JasonerProperty("cycle_executions")
        public Builder cycleExecutions(List<CycleExecution> value) {
            this.cycleExecutions = value;
            return this;
        }

        /**
         * lastPayment
         */
        @JasonerProperty("last_payment")
        public Builder lastPayment(LastPaymentDetails value) {
            this.lastPayment = value;
            return this;
        }

        /**
         * nextBillingTime
         */
        @JasonerProperty("next_billing_time")
        public Builder nextBillingTime(Instant value) {
            this.nextBillingTime = value;
            return this;
        }

        /**
         * finalPaymentTime
         */
        @JasonerProperty("final_payment_time")
        public Builder finalPaymentTime(Instant value) {
            this.finalPaymentTime = value;
            return this;
        }

        /**
         * The number of consecutive payment failures. Resets to `0` after a successful payment. If this reaches the 
`payment_failure_threshold` value, the subscription updates to the `SUSPENDED` state.
         */
        @JasonerProperty("failed_payments_count")
        public Builder failedPaymentsCount(Integer value) {
            this.failedPaymentsCount = value;
            return this;
        }

        /**
         * lastFailedPayment
         */
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

