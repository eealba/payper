package io.github.eealba.payper.subscriptions.v1.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The update pricing scheme request details.
 */
public record UpdatePricingSchemeRequest(@JasonerProperty("billing_cycle_sequence") Integer billingCycleSequence, @JasonerProperty("pricing_scheme") 
PricingScheme pricingScheme) {

    public UpdatePricingSchemeRequest(Integer billingCycleSequence, PricingScheme pricingScheme) {
        if (billingCycleSequence == null) {
            throw new IllegalArgumentException("Field billingCycleSequence can`t be null");
        }
        if (pricingScheme == null) {
            throw new IllegalArgumentException("Field pricingScheme can`t be null");
        }
        this.billingCycleSequence = billingCycleSequence;
        this.pricingScheme = pricingScheme;
    }

}
