package io.github.eealba.payper.subscriptions.v1.model;



public class TaxesOverride {

    
    private final Percentage percentage;
    
    private final Boolean inclusive;

    private TaxesOverride(Builder builder) {
        percentage = builder.percentage;
        inclusive = builder.inclusive;

    }

    
    public Percentage percentage() {
        return percentage;
    }

    
    public Boolean inclusive() {
        return inclusive;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Percentage percentage;
        private Boolean inclusive;

        
        public Builder percentage(Percentage value) {
            percentage = value;
            return this;
        }

        
        public Builder inclusive(Boolean value) {
            inclusive = value;
            return this;
        }

        public TaxesOverride build() {
            return new TaxesOverride(this);
        }

    }

}

