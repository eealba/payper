package io.github.eealba.payper.payments.v2.model;



/**
 * Reauthorizes an authorized PayPal account payment, by ID. To ensure that funds are still available, reauthorize a 
payment after its initial three-day honor period expires. You can reauthorize a payment only once from days four to 
29.<br/><br/>If 30 days have transpired since the date of the original authorization, you must create an authorized 
payment instead of reauthorizing the original authorized payment.<br/><br/>A reauthorized payment itself has a new honor 
period of three days.<br/><br/>You can reauthorize an authorized payment once. The allowed amount depends on context and 
geography, for example in US it is up to 115% of the original authorized amount, not to exceed an increase of $75 
USD.<br/><br/>Supports only the `amount` request parameter.<blockquote><strong>Note:</strong> This request is currently 
not supported for Partner use cases.</blockquote>
 */
public class ReauthorizeRequest {


    
    private final Money amount;

    private ReauthorizeRequest(Builder builder) {
        amount = builder.amount;

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

        private Money amount;

        /**
         * amount
         */
        
        public Builder amount(Money value) {
            this.amount = value;
            return this;
        }

        public ReauthorizeRequest build() {
            return new ReauthorizeRequest(this);
        }

    }

}

