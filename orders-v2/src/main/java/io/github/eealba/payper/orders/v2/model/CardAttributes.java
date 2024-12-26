package io.github.eealba.payper.orders.v2.model;



/**
 * Additional attributes associated with the use of this card.
 */
public class CardAttributes {


    
    private final Customer customer;
    
    private final VaultInstructionBase vault;

    private CardAttributes(Builder builder) {
        customer = builder.customer;
        vault = builder.vault;

    }

    /**
     * customer
     */
    
    public Customer customer() {
        return customer;
    }

    /**
     * vault
     */
    
    public VaultInstructionBase vault() {
        return vault;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Customer customer;
        private VaultInstructionBase vault;

        /**
         * customer
         */
        
        public Builder customer(Customer value) {
            this.customer = value;
            return this;
        }

        /**
         * vault
         */
        
        public Builder vault(VaultInstructionBase value) {
            this.vault = value;
            return this;
        }

        public CardAttributes build() {
            return new CardAttributes(this);
        }

    }


}

