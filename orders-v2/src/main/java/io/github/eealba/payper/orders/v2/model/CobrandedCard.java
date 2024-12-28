package io.github.eealba.payper.orders.v2.model;

import java.util.List;

/**
 * Details about the merchant cobranded card used for order purchase.
 */
public class CobrandedCard {


    
    private final List<String> labels;
    
    private final Payee payee;
    
    private final Money amount;

    private CobrandedCard(Builder builder) {
        labels = builder.labels;
        payee = builder.payee;
        amount = builder.amount;

    }

    /**
     * Array of labels for the cobranded card.
     */
    
    public List<String> labels() {
        return labels;
    }

    /**
     * payee
     */
    
    public Payee payee() {
        return payee;
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

        private List<String> labels;
        private Payee payee;
        private Money amount;

        /**
         * Array of labels for the cobranded card.
         */
        
        public Builder labels(List<String> value) {
            this.labels = value;
            return this;
        }

        /**
         * payee
         */
        
        public Builder payee(Payee value) {
            this.payee = value;
            return this;
        }

        /**
         * amount
         */
        
        public Builder amount(Money value) {
            this.amount = value;
            return this;
        }

        public CobrandedCard build() {
            return new CobrandedCard(this);
        }

    }

}

