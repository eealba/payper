package io.github.eealba.payper.subscriptions.v1.model;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;

/**
 * The pricing tier details.
 */
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

    /**
     * The starting quantity for the tier.
     */
    @JasonerProperty("starting_quantity")
    public String startingQuantity() {
        return startingQuantity;
    }

    /**
     * The ending quantity for the tier. Optional for the last tier.
     */
    @JasonerProperty("ending_quantity")
    public String endingQuantity() {
        return endingQuantity;
    }

    /**
     * amount
     */
    
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

        /**
         * The starting quantity for the tier.
         */
        @JasonerProperty("starting_quantity")
        public Builder startingQuantity(String value) {
            this.startingQuantity = value;
            return this;
        }

        /**
         * The ending quantity for the tier. Optional for the last tier.
         */
        @JasonerProperty("ending_quantity")
        public Builder endingQuantity(String value) {
            this.endingQuantity = value;
            return this;
        }

        /**
         * amount
         */
        
        public Builder amount(Money value) {
            this.amount = value;
            return this;
        }

        public PricingTier build() {
            return new PricingTier(this);
        }

    }

}

