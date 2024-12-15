package io.github.eealba.payper.subscriptions.v1.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * An inline plan object to customise the subscription. You can override plan level default attributes by providing 
customised values for the subscription in this object.
 */
public class PlanOverride {


    @JasonerProperty("billing_cycles")
    private final List<BillingCycleOverride> billingCycles;
    @JasonerProperty("payment_preferences")
    private final PaymentPreferencesOverride paymentPreferences;
    
    private final TaxesOverride taxes;

    private PlanOverride(Builder builder) {
        billingCycles = builder.billingCycles;
        paymentPreferences = builder.paymentPreferences;
        taxes = builder.taxes;

    }

    /**
     * An array of billing cycles for trial billing and regular billing. The subscription billing cycle definition 
has to adhere to the plan billing cycle definition.
     */
    @JasonerProperty("billing_cycles")
    public List<BillingCycleOverride> billingCycles() {
        return billingCycles;
    }

    /**
     * paymentPreferences
     */
    @JasonerProperty("payment_preferences")
    public PaymentPreferencesOverride paymentPreferences() {
        return paymentPreferences;
    }

    /**
     * taxes
     */
    
    public TaxesOverride taxes() {
        return taxes;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private List<BillingCycleOverride> billingCycles;
        private PaymentPreferencesOverride paymentPreferences;
        private TaxesOverride taxes;

        /**
         * An array of billing cycles for trial billing and regular billing. The subscription billing cycle definition 
has to adhere to the plan billing cycle definition.
         */
        @JasonerProperty("billing_cycles")
        public Builder billingCycles(List<BillingCycleOverride> value) {
            this.billingCycles = value;
            return this;
        }

        /**
         * paymentPreferences
         */
        @JasonerProperty("payment_preferences")
        public Builder paymentPreferences(PaymentPreferencesOverride value) {
            this.paymentPreferences = value;
            return this;
        }

        /**
         * taxes
         */
        
        public Builder taxes(TaxesOverride value) {
            this.taxes = value;
            return this;
        }

        public PlanOverride build() {
            return new PlanOverride(this);
        }

    }

}

