package io.github.eealba.payper.subscriptions.v1.model;


import io.github.eealba.jasoner.JasonerProperty;

public class PaymentPreferencesOverride {

    @JasonerProperty("auto_bill_outstanding")
    private final Boolean autoBillOutstanding;
    @JasonerProperty("setup_fee")
    private final Money setupFee;
    @JasonerProperty("setup_fee_failure_action")
    private final SetupFeeFailureAction setupFeeFailureAction;
    @JasonerProperty("payment_failure_threshold")
    private final Integer paymentFailureThreshold;

    private PaymentPreferencesOverride(Builder builder) {
        autoBillOutstanding = builder.autoBillOutstanding;
        setupFee = builder.setupFee;
        setupFeeFailureAction = builder.setupFeeFailureAction;
        paymentFailureThreshold = builder.paymentFailureThreshold;

    }

    @JasonerProperty("auto_bill_outstanding")
    public Boolean autoBillOutstanding() {
        return autoBillOutstanding;
    }

    @JasonerProperty("setup_fee")
    public Money setupFee() {
        return setupFee;
    }

    @JasonerProperty("setup_fee_failure_action")
    public SetupFeeFailureAction setupFeeFailureAction() {
        return setupFeeFailureAction;
    }

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

        @JasonerProperty("auto_bill_outstanding")
        public Builder autoBillOutstanding(Boolean value) {
            this.autoBillOutstanding = value;
            return this;
        }

        @JasonerProperty("setup_fee")
        public Builder setupFee(Money value) {
            this.setupFee = value;
            return this;
        }

        @JasonerProperty("setup_fee_failure_action")
        public Builder setupFeeFailureAction(SetupFeeFailureAction value) {
            this.setupFeeFailureAction = value;
            return this;
        }

        @JasonerProperty("payment_failure_threshold")
        public Builder paymentFailureThreshold(Integer value) {
            this.paymentFailureThreshold = value;
            return this;
        }

        public PaymentPreferencesOverride build() {
            return new PaymentPreferencesOverride(this);
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

