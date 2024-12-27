package io.github.eealba.payper.orders.v2.model;


import java.util.Objects;

/**
 * The platform or partner fee, commission, or brokerage fee that is associated with the transaction. Not a separate or 
isolated transaction leg from the external perspective. The platform fee is limited in scope and is always associated 
with the original payment for the purchase unit.
 */
public class PlatformFee {


    
    private final Money amount;
    
    private final Payee payee;

    private PlatformFee(Builder builder) {
        payee = builder.payee;
        amount = Objects.requireNonNull(builder.amount);
    }

    /**
     * amount
     */
    
    public Money amount() {
        return amount;
    }

    /**
     * payee
     */
    
    public Payee payee() {
        return payee;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Money amount;
        private Payee payee;

        /**
         * amount
         */
        
        public Builder amount(Money value) {
            this.amount = value;
            return this;
        }

        /**
         * payee
         */
        
        public Builder payee(Payee value) {
            this.payee = value;
            return this;
        }

        public PlatformFee build() {
            return new PlatformFee(this);
        }

    }

}

