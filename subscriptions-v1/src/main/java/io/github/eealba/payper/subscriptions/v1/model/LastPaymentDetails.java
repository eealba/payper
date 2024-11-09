package io.github.eealba.payper.subscriptions.v1.model;

import java.time.Instant;

public class LastPaymentDetails {

    
    private final Money amount;
    
    private final Instant time;

    private LastPaymentDetails(Builder builder) {
        amount = builder.amount;
        time = builder.time;

    }

    
    public Money amount() {
        return amount;
    }

    
    public Instant time() {
        return time;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Money amount;
        private Instant time;

        
        public Builder amount(Money value) {
            this.amount = value;
            return this;
        }

        
        public Builder time(Instant value) {
            this.time = value;
            return this;
        }

        public LastPaymentDetails build() {
            return new LastPaymentDetails(this);
        }

    }

}

