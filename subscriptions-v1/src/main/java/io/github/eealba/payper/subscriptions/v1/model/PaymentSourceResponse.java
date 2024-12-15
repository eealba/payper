package io.github.eealba.payper.subscriptions.v1.model;



/**
 * The payment source used to fund the payment.
 */
public class PaymentSourceResponse {


    
    private final CardResponseWithBillingAddress card;

    private PaymentSourceResponse(Builder builder) {
        card = builder.card;

    }

    /**
     * card
     */
    
    public CardResponseWithBillingAddress card() {
        return card;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private CardResponseWithBillingAddress card;

        /**
         * card
         */
        
        public Builder card(CardResponseWithBillingAddress value) {
            this.card = value;
            return this;
        }

        public PaymentSourceResponse build() {
            return new PaymentSourceResponse(this);
        }

    }

}

