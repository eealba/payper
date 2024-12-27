package io.github.eealba.payper.orders.v2.model;



/**
 * Additional attributes associated with the use of this PayPal Wallet.
 */
public class PaypalWalletAttributes {


    
    private final Customer customer;
    
    private final VaultPaypalWalletBase vault;

    private PaypalWalletAttributes(Builder builder) {
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
    
    public VaultPaypalWalletBase vault() {
        return vault;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Customer customer;
        private VaultPaypalWalletBase vault;

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
        
        public Builder vault(VaultPaypalWalletBase value) {
            this.vault = value;
            return this;
        }

        public PaypalWalletAttributes build() {
            return new PaypalWalletAttributes(this);
        }

    }

}

