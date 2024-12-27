package io.github.eealba.payper.orders.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * The details about a saved PayPal Wallet payment source.
 */
public class PaypalWalletVaultResponse {


    
    private final String id;
    
    private final Status status;
    
    private final Customer customer;
    
    private final List<LinkDescription> links;
    @JasonerProperty("owner_id")
    private final VaultOwnerId ownerId;

    private PaypalWalletVaultResponse(Builder builder) {
        id = builder.id;
        status = builder.status;
        customer = builder.customer;
        links = builder.links;
        ownerId = builder.ownerId;

    }

    /**
     * The PayPal-generated ID for the saved payment source.
     */
    
    public String id() {
        return id;
    }

    /**
     * The vault status.
     */
    
    public Status status() {
        return status;
    }

    /**
     * customer
     */
    
    public Customer customer() {
        return customer;
    }

    /**
     * An array of request-related HATEOAS links.
     */
    
    public List<LinkDescription> links() {
        return links;
    }

    /**
     * ownerId
     */
    @JasonerProperty("owner_id")
    public VaultOwnerId ownerId() {
        return ownerId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String id;
        private Status status;
        private Customer customer;
        private List<LinkDescription> links;
        private VaultOwnerId ownerId;

        /**
         * The PayPal-generated ID for the saved payment source.
         */
        
        public Builder id(String value) {
            this.id = value;
            return this;
        }

        /**
         * The vault status.
         */
        
        public Builder status(Status value) {
            this.status = value;
            return this;
        }

        /**
         * customer
         */
        
        public Builder customer(Customer value) {
            this.customer = value;
            return this;
        }

        /**
         * An array of request-related HATEOAS links.
         */
        
        public Builder links(List<LinkDescription> value) {
            this.links = value;
            return this;
        }

        /**
         * ownerId
         */
        @JasonerProperty("owner_id")
        public Builder ownerId(VaultOwnerId value) {
            this.ownerId = value;
            return this;
        }

        public PaypalWalletVaultResponse build() {
            return new PaypalWalletVaultResponse(this);
        }

    }
    /**
     * The vault status.
     */
    public enum Status {
        VAULTED,
        CREATED,
        APPROVED
    }
}

