package io.github.eealba.payper.subscriptions.v1.model;


import java.util.Objects;
import io.github.eealba.jasoner.JasonerProperty;

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

    @JasonerProperty("tenure_type")
    public TenureType tenureType() {
        return tenureType;
    }

    
    public Integer sequence() {
        return sequence;
    }

    @JasonerProperty("cycles_completed")
    public Integer cyclesCompleted() {
        return cyclesCompleted;
    }

    @JasonerProperty("cycles_remaining")
    public Integer cyclesRemaining() {
        return cyclesRemaining;
    }

    @JasonerProperty("current_pricing_scheme_version")
    public Integer currentPricingSchemeVersion() {
        return currentPricingSchemeVersion;
    }

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

        @JasonerProperty("tenure_type")
        public Builder tenureType(TenureType value) {
            this.tenureType = value;
            return this;
        }

        
        public Builder sequence(Integer value) {
            this.sequence = value;
            return this;
        }

        @JasonerProperty("cycles_completed")
        public Builder cyclesCompleted(Integer value) {
            this.cyclesCompleted = value;
            return this;
        }

        @JasonerProperty("cycles_remaining")
        public Builder cyclesRemaining(Integer value) {
            this.cyclesRemaining = value;
            return this;
        }

        @JasonerProperty("current_pricing_scheme_version")
        public Builder currentPricingSchemeVersion(Integer value) {
            this.currentPricingSchemeVersion = value;
            return this;
        }

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

