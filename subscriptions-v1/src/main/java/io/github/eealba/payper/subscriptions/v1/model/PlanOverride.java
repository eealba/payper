package io.github.eealba.payper.subscriptions.v1.model;

import java.util.List;
import io.github.eealba.jasoner.JasonerProperty;

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

    @JasonerProperty("billing_cycles")
    public List<BillingCycleOverride> billingCycles() {
        return billingCycles;
    }

    @JasonerProperty("payment_preferences")
    public PaymentPreferencesOverride paymentPreferences() {
        return paymentPreferences;
    }

    
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

        @JasonerProperty("billing_cycles")
        public Builder billingCycles(List<BillingCycleOverride> value) {
            billingCycles = value;
            return this;
        }

        @JasonerProperty("payment_preferences")
        public Builder paymentPreferences(PaymentPreferencesOverride value) {
            paymentPreferences = value;
            return this;
        }

        
        public Builder taxes(TaxesOverride value) {
            taxes = value;
            return this;
        }

        public PlanOverride build() {
            return new PlanOverride(this);
        }

    }

}

