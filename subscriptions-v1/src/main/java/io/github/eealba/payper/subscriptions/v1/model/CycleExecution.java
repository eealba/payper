package io.github.eealba.payper.subscriptions.v1.model;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;

/**
 * The regular and trial execution details for a billing cycle.
 */
public class CycleExecution {


    @JasonerProperty("tenure_type")
    private final TenureType tenureType;
    
    private final Integer sequence;
    @JasonerProperty("cycles_completed")
    private final Integer cyclesCompleted;
    @JasonerProperty("cycles_remaining")
    private final Integer cyclesRemaining;
    @JasonerProperty("current_pricing_scheme_version")
    private final Integer currentPricingSchemeVersion;
    @JasonerProperty("total_cycles")
    private final Integer totalCycles;

    private CycleExecution(Builder builder) {
        cyclesRemaining = builder.cyclesRemaining;
        currentPricingSchemeVersion = builder.currentPricingSchemeVersion;
        totalCycles = builder.totalCycles;
        tenureType = Objects.requireNonNull(builder.tenureType);
        sequence = Objects.requireNonNull(builder.sequence);
        cyclesCompleted = Objects.requireNonNull(builder.cyclesCompleted);
    }

    /**
     * The type of the billing cycle.
     */
    @JasonerProperty("tenure_type")
    public TenureType tenureType() {
        return tenureType;
    }

    /**
     * The order in which to run this cycle among other billing cycles.
     */
    
    public Integer sequence() {
        return sequence;
    }

    /**
     * The number of billing cycles that have completed.
     */
    @JasonerProperty("cycles_completed")
    public Integer cyclesCompleted() {
        return cyclesCompleted;
    }

    /**
     * For a finite billing cycle, cycles_remaining is the number of remaining cycles. For an infinite billing cycle, 
cycles_remaining is set as 0.
     */
    @JasonerProperty("cycles_remaining")
    public Integer cyclesRemaining() {
        return cyclesRemaining;
    }

    /**
     * The active pricing scheme version for the billing cycle.
     */
    @JasonerProperty("current_pricing_scheme_version")
    public Integer currentPricingSchemeVersion() {
        return currentPricingSchemeVersion;
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

        private TenureType tenureType;
        private Integer sequence;
        private Integer cyclesCompleted;
        private Integer cyclesRemaining;
        private Integer currentPricingSchemeVersion;
        private Integer totalCycles;

        /**
         * The type of the billing cycle.
         */
        @JasonerProperty("tenure_type")
        public Builder tenureType(TenureType value) {
            this.tenureType = value;
            return this;
        }

        /**
         * The order in which to run this cycle among other billing cycles.
         */
        
        public Builder sequence(Integer value) {
            this.sequence = value;
            return this;
        }

        /**
         * The number of billing cycles that have completed.
         */
        @JasonerProperty("cycles_completed")
        public Builder cyclesCompleted(Integer value) {
            this.cyclesCompleted = value;
            return this;
        }

        /**
         * For a finite billing cycle, cycles_remaining is the number of remaining cycles. For an infinite billing cycle, 
cycles_remaining is set as 0.
         */
        @JasonerProperty("cycles_remaining")
        public Builder cyclesRemaining(Integer value) {
            this.cyclesRemaining = value;
            return this;
        }

        /**
         * The active pricing scheme version for the billing cycle.
         */
        @JasonerProperty("current_pricing_scheme_version")
        public Builder currentPricingSchemeVersion(Integer value) {
            this.currentPricingSchemeVersion = value;
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

        public CycleExecution build() {
            return new CycleExecution(this);
        }

    }
    /**
     * The type of the billing cycle.
     */
    public enum TenureType {
        REGULAR,
        TRIAL
    }
}

