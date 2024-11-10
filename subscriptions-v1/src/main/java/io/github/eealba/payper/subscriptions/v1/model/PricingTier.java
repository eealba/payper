package io.github.eealba.payper.subscriptions.v1.model;


import java.util.Objects;
import io.github.eealba.jasoner.JasonerProperty;

public class PricingTier {

    @JasonerProperty("starting_quantity")
    private final String startingQuantity;
    @JasonerProperty("ending_quantity")
    private final String endingQuantity;
    
    private final Money amount;

    private PricingTier(Builder builder) {
        endingQuantity = builder.endingQuantity;
        startingQuantity = Objects.requireNonNull(builder.startingQuantity);
        amount = Objects.requireNonNull(builder.amount);
    }

    @JasonerProperty("starting_quantity")
    public String startingQuantity() {
        return startingQuantity;
    }

    @JasonerProperty("ending_quantity")
    public String endingQuantity() {
        return endingQuantity;
    }

    
    public Money amount() {
        return amount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String startingQuantity;
        private String endingQuantity;
        private Money amount;

        @JasonerProperty("starting_quantity")
        public Builder startingQuantity(String value) {
            this.startingQuantity = value;
            return this;
        }

        @JasonerProperty("ending_quantity")
        public Builder endingQuantity(String value) {
            this.endingQuantity = value;
            return this;
        }

        
        public Builder amount(Money value) {
            this.amount = value;
            return this;
        }

        public PricingTier build() {
            return new PricingTier(this);
        }

    }

}

