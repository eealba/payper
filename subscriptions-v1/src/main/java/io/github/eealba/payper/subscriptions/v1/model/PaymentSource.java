package io.github.eealba.payper.subscriptions.v1.model;



/**
 * The payment source definition. To be eligible to create subscription using debit or credit card, you will need to sign 
up here (https://www.paypal.com/bizsignup/entry/product/ppcp). Please note, its available only for non-3DS cards and for 
merchants in US and AU regions.
 */
public class PaymentSource {


    
    private final Card card;

    private PaymentSource(Builder builder) {
        card = builder.card;

    }

    /**
     * card
     */
    
    public Card card() {
        return card;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Card card;

        /**
         * card
         */
        
        public Builder card(Card value) {
            this.card = value;
            return this;
        }

        public PaymentSource build() {
            return new PaymentSource(this);
        }

    }

}

