package io.github.eealba.payper.subscriptions.v1.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The payment preferences for a subscription.
 */
public class PaymentPreferences {


    @JasonerProperty("auto_bill_outstanding")
    private final Boolean autoBillOutstanding;
    @JasonerProperty("setup_fee")
    private final Money setupFee;
    @JasonerProperty("setup_fee_failure_action")
    private final SetupFeeFailureAction setupFeeFailureAction;
    @JasonerProperty("payment_failure_threshold")
    private final Integer paymentFailureThreshold;

    private PaymentPreferences(Builder builder) {
        autoBillOutstanding = builder.autoBillOutstanding;
        setupFee = builder.setupFee;
        setupFeeFailureAction = builder.setupFeeFailureAction;
        paymentFailureThreshold = builder.paymentFailureThreshold;

    }

    /**
     * Indicates whether to automatically bill the outstanding amount in the next billing cycle.
     */
    @JasonerProperty("auto_bill_outstanding")
    public Boolean autoBillOutstanding() {
        return autoBillOutstanding;
    }

    /**
     * setupFee
     */
    @JasonerProperty("setup_fee")
    public Money setupFee() {
        return setupFee;
    }

    /**
     * The action to take on the subscription if the initial payment for the setup fails.
     */
    @JasonerProperty("setup_fee_failure_action")
    public SetupFeeFailureAction setupFeeFailureAction() {
        return setupFeeFailureAction;
    }

    /**
     * The maximum number of payment failures before a subscription is suspended. For example, if 
`payment_failure_threshold` is `2`, the subscription automatically updates to the `SUSPEND` state if two 
consecutive payments fail.
     */
    @JasonerProperty("payment_failure_threshold")
    public Integer paymentFailureThreshold() {
        return paymentFailureThreshold;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Boolean autoBillOutstanding;
        private Money setupFee;
        private SetupFeeFailureAction setupFeeFailureAction;
        private Integer paymentFailureThreshold;

        /**
         * Indicates whether to automatically bill the outstanding amount in the next billing cycle.
         */
        @JasonerProperty("auto_bill_outstanding")
        public Builder autoBillOutstanding(Boolean value) {
            this.autoBillOutstanding = value;
            return this;
        }

        /**
         * setupFee
         */
        @JasonerProperty("setup_fee")
        public Builder setupFee(Money value) {
            this.setupFee = value;
            return this;
        }

        /**
         * The action to take on the subscription if the initial payment for the setup fails.
         */
        @JasonerProperty("setup_fee_failure_action")
        public Builder setupFeeFailureAction(SetupFeeFailureAction value) {
            this.setupFeeFailureAction = value;
            return this;
        }

        /**
         * The maximum number of payment failures before a subscription is suspended. For example, if 
`payment_failure_threshold` is `2`, the subscription automatically updates to the `SUSPEND` state if two 
consecutive payments fail.
         */
        @JasonerProperty("payment_failure_threshold")
        public Builder paymentFailureThreshold(Integer value) {
            this.paymentFailureThreshold = value;
            return this;
        }

        public PaymentPreferences build() {
            return new PaymentPreferences(this);
        }

    }
    /**
     * The action to take on the subscription if the initial payment for the setup fails.
     */
    public enum SetupFeeFailureAction {
        CONTINUE,
        CANCEL
    }
}

