package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * Merchants and partners can add Level 2 and 3 data to payments to reduce risk and payment processing costs. For more 
information about processing payments, see <a 
href="https://developer.paypal.com/docs/checkout/advanced/processing/">checkout</a> or <a 
href="https://developer.paypal.com/docs/multiparty/checkout/advanced/processing/">multiparty checkout</a>.
 */
public class CardSupplementaryData {


    @JasonerProperty("level_2")
    private final Level2CardProcessingData level2;
    @JasonerProperty("level_3")
    private final Level3CardProcessingData level3;

    private CardSupplementaryData(Builder builder) {
        level2 = builder.level2;
        level3 = builder.level3;

    }

    /**
     * level2
     */
    @JasonerProperty("level_2")
    public Level2CardProcessingData level2() {
        return level2;
    }

    /**
     * level3
     */
    @JasonerProperty("level_3")
    public Level3CardProcessingData level3() {
        return level3;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Level2CardProcessingData level2;
        private Level3CardProcessingData level3;

        /**
         * level2
         */
        @JasonerProperty("level_2")
        public Builder level2(Level2CardProcessingData value) {
            this.level2 = value;
            return this;
        }

        /**
         * level3
         */
        @JasonerProperty("level_3")
        public Builder level3(Level3CardProcessingData value) {
            this.level3 = value;
            return this;
        }

        public CardSupplementaryData build() {
            return new CardSupplementaryData(this);
        }

    }

}

