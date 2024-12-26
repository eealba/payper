package io.github.eealba.payper.orders.v2.model;



/**
 * Supplementary data about a payment. This object passes information that can be used to improve risk assessments and 
processing costs, for example, by providing Level 2 and Level 3 payment data.
 */
public class SupplementaryData {


    
    private final CardSupplementaryData card;

    private SupplementaryData(Builder builder) {
        card = builder.card;

    }

    /**
     * card
     */
    
    public CardSupplementaryData card() {
        return card;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private CardSupplementaryData card;

        /**
         * card
         */
        
        public Builder card(CardSupplementaryData value) {
            this.card = value;
            return this;
        }

        public SupplementaryData build() {
            return new SupplementaryData(this);
        }

    }


}

