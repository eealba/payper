package io.github.eealba.payper.subscriptions.v1.model;


import java.util.Objects;

public class Taxes {

    
    private final Percentage percentage;
    
    private final Boolean inclusive;

    private Taxes(Builder builder) {
        inclusive = builder.inclusive;
        percentage = Objects.requireNonNull(builder.percentage);
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
            this.percentage = value;
            return this;
        }

        
        public Builder inclusive(Boolean value) {
            this.inclusive = value;
            return this;
        }

        public Taxes build() {
            return new Taxes(this);
        }

    }

}

