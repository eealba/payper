package io.github.eealba.payper.subscriptions.v1.model;



public class PaymentSourceResponse {

    
    private final CardResponseWithBillingAddress card;

    private PaymentSourceResponse(Builder builder) {
        card = builder.card;

    }

    
    public CardResponseWithBillingAddress card() {
        return card;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private CardResponseWithBillingAddress card;

        
        public Builder card(CardResponseWithBillingAddress value) {
            this.card = value;
            return this;
        }

        public PaymentSourceResponse build() {
            return new PaymentSourceResponse(this);
        }

    }

}

