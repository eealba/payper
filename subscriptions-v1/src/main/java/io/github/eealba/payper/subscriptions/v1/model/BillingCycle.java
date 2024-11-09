package io.github.eealba.payper.subscriptions.v1.model;


import java.util.Objects;
import io.github.eealba.jasoner.JasonerProperty;

public class BillingCycle {

    @JasonerProperty("pricing_scheme")
    private final PricingScheme pricingScheme;
    
    private final Frequency frequency;
    @JasonerProperty("tenure_type")
    private final TenureType tenureType;
    
    private final Integer sequence;
    @JasonerProperty("total_cycles")
    private final Integer totalCycles;

    private BillingCycle(Builder builder) {
        pricingScheme = builder.pricingScheme;
        totalCycles = builder.totalCycles;
        frequency = Objects.requireNonNull(builder.frequency);
        tenureType = Objects.requireNonNull(builder.tenureType);
        sequence = Objects.requireNonNull(builder.sequence);
    }

    @JasonerProperty("pricing_scheme")
    public PricingScheme pricingScheme() {
        return pricingScheme;
    }

    
    public Frequency frequency() {
        return frequency;
    }

    @JasonerProperty("tenure_type")
    public TenureType tenureType() {
        return tenureType;
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
        private Frequency frequency;
        private TenureType tenureType;
        private Integer sequence;
        private Integer totalCycles;

        @JasonerProperty("pricing_scheme")
        public Builder pricingScheme(PricingScheme value) {
            pricingScheme = value;
            return this;
        }

        
        public Builder frequency(Frequency value) {
            frequency = value;
            return this;
        }

        @JasonerProperty("tenure_type")
        public Builder tenureType(TenureType value) {
            tenureType = value;
            return this;
        }

        
        public Builder sequence(Integer value) {
            sequence = value;
            return this;
        }

        @JasonerProperty("total_cycles")
        public Builder totalCycles(Integer value) {
            totalCycles = value;
            return this;
        }

        public BillingCycle build() {
            return new BillingCycle(this);
        }

    }
    /**
     * The tenure type of the billing cycle. In case of a plan having trial cycle, only 2 trial cycles are allowed per plan.
     */
    public enum TenureType {
        REGULAR,
        TRIAL
    }
}

