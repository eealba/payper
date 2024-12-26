package io.github.eealba.payper.orders.v2.model;

import java.util.List;

/**
 * The details about a saved payment source.
 */
public class VaultResponse {


    
    private final String id;
    
    private final Status status;
    
    private final Customer customer;
    
    private final List<LinkDescription> links;

    private VaultResponse(Builder builder) {
        id = builder.id;
        status = builder.status;
        customer = builder.customer;
        links = builder.links;

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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String id;
        private Status status;
        private Customer customer;
        private List<LinkDescription> links;

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

        public VaultResponse build() {
            return new VaultResponse(this);
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

