package io.github.eealba.payper.subscriptions.v1.model;

import java.time.Instant;

/**
 * The details for the last payment.
 */
public class LastPaymentDetails {


    
    private final Money amount;
    
    private final Instant time;

    private LastPaymentDetails(Builder builder) {
        amount = builder.amount;
        time = builder.time;

    }

    /**
     * amount
     */
    
    public Money amount() {
        return amount;
    }

    /**
     * time
     */
    
    public Instant time() {
        return time;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Money amount;
        private Instant time;

        /**
         * amount
         */
        
        public Builder amount(Money value) {
            this.amount = value;
            return this;
        }

        /**
         * time
         */
        
        public Builder time(Instant value) {
            this.time = value;
            return this;
        }

        public LastPaymentDetails build() {
            return new LastPaymentDetails(this);
        }

    }

}

