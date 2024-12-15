package io.github.eealba.payper.subscriptions.v1.model;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;

/**
 * The billing cycle details.
 */
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

    /**
     * pricingScheme
     */
    @JasonerProperty("pricing_scheme")
    public PricingScheme pricingScheme() {
        return pricingScheme;
    }

    /**
     * frequency
     */
    
    public Frequency frequency() {
        return frequency;
    }

    /**
     * The tenure type of the billing cycle. In case of a plan having trial cycle, only 2 trial cycles are allowed 
per plan.
     */
    @JasonerProperty("tenure_type")
    public TenureType tenureType() {
        return tenureType;
    }

    /**
     * The order in which this cycle is to run among other billing cycles. For example, a trial billing cycle has a 
`sequence` of `1` while a regular billing cycle has a `sequence` of `2`, so that trial cycle runs before the 
regular cycle.
     */
    
    public Integer sequence() {
        return sequence;
    }

    /**
     * The number of times this billing cycle gets executed. Trial billing cycles can only be executed a finite 
number of times (value between <code>1</code> and <code>999</code> for <code>total_cycles</code>). Regular 
billing cycles can be executed infinite times (value of <code>0</code> for <code>total_cycles</code>) or a 
finite number of times (value between <code>1</code> and <code>999</code> for <code>total_cycles</code>).
     */
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

        /**
         * pricingScheme
         */
        @JasonerProperty("pricing_scheme")
        public Builder pricingScheme(PricingScheme value) {
            this.pricingScheme = value;
            return this;
        }

        /**
         * frequency
         */
        
        public Builder frequency(Frequency value) {
            this.frequency = value;
            return this;
        }

        /**
         * The tenure type of the billing cycle. In case of a plan having trial cycle, only 2 trial cycles are allowed 
per plan.
         */
        @JasonerProperty("tenure_type")
        public Builder tenureType(TenureType value) {
            this.tenureType = value;
            return this;
        }

        /**
         * The order in which this cycle is to run among other billing cycles. For example, a trial billing cycle has a 
`sequence` of `1` while a regular billing cycle has a `sequence` of `2`, so that trial cycle runs before the 
regular cycle.
         */
        
        public Builder sequence(Integer value) {
            this.sequence = value;
            return this;
        }

        /**
         * The number of times this billing cycle gets executed. Trial billing cycles can only be executed a finite 
number of times (value between <code>1</code> and <code>999</code> for <code>total_cycles</code>). Regular 
billing cycles can be executed infinite times (value of <code>0</code> for <code>total_cycles</code>) or a 
finite number of times (value between <code>1</code> and <code>999</code> for <code>total_cycles</code>).
         */
        @JasonerProperty("total_cycles")
        public Builder totalCycles(Integer value) {
            this.totalCycles = value;
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

