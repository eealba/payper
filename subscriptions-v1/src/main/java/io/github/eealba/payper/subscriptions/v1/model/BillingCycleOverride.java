package io.github.eealba.payper.subscriptions.v1.model;


import java.util.Objects;
import io.github.eealba.jasoner.JasonerProperty;

public class BillingCycleOverride {

    @JasonerProperty("pricing_scheme")
    private final PricingScheme pricingScheme;
    
    private final Integer sequence;
    @JasonerProperty("total_cycles")
    private final Integer totalCycles;

    private BillingCycleOverride(Builder builder) {
        pricingScheme = builder.pricingScheme;
        totalCycles = builder.totalCycles;
        sequence = Objects.requireNonNull(builder.sequence);
    }

    @JasonerProperty("pricing_scheme")
    public PricingScheme pricingScheme() {
        return pricingScheme;
    }

    
    public Integer sequence() {
        return sequence;
    }

    @JasonerProperty("total_cycles")
    public Integer totalCycles() {
        return totalCycles;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private PricingScheme pricingScheme;
        private Integer sequence;
        private Integer totalCycles;

        @JasonerProperty("pricing_scheme")
        public Builder pricingScheme(PricingScheme value) {
            this.pricingScheme = value;
            return this;
        }

        
        public Builder sequence(Integer value) {
            this.sequence = value;
            return this;
        }

        @JasonerProperty("total_cycles")
        public Builder totalCycles(Integer value) {
            this.totalCycles = value;
            return this;
        }

        public BillingCycleOverride build() {
            return new BillingCycleOverride(this);
        }

    }

}

