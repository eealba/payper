package io.github.eealba.payper.orders.v2.model;



/**
 * Additional attributes associated with the use of this Venmo Wallet.
 */
public class VenmoWalletAttributes {


    
    private final Customer customer;
    
    private final VaultVenmoWalletBase vault;

    private VenmoWalletAttributes(Builder builder) {
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
    
    public VaultVenmoWalletBase vault() {
        return vault;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Customer customer;
        private VaultVenmoWalletBase vault;

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
        
        public Builder vault(VaultVenmoWalletBase value) {
            this.vault = value;
            return this;
        }

        public VenmoWalletAttributes build() {
            return new VenmoWalletAttributes(this);
        }

    }

}

