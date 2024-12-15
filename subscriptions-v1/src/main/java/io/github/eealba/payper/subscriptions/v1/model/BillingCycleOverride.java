package io.github.eealba.payper.subscriptions.v1.model;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;

/**
 * The billing cycle details to override at subscription level. The subscription billing cycle definition has to adhere to 
the plan billing cycle definition.
 */
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

    /**
     * pricingScheme
     */
    @JasonerProperty("pricing_scheme")
    public PricingScheme pricingScheme() {
        return pricingScheme;
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

        public BillingCycleOverride build() {
            return new BillingCycleOverride(this);
        }

    }

}

