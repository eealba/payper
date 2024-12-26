package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;


/**
 * The details about a customer in PayPal's system of record.
 */
public class Customer {


    
    private final MerchantPartnerCustomerId id;
    @JasonerProperty("email_address")
    private final Email emailAddress;
    
    private final PhoneWithType phone;

    private Customer(Builder builder) {
        id = builder.id;
        emailAddress = builder.emailAddress;
        phone = builder.phone;

    }

    /**
     * id
     */
    
    public MerchantPartnerCustomerId id() {
        return id;
    }

    /**
     * emailAddress
     */
    @JasonerProperty("email_address")
    public Email emailAddress() {
        return emailAddress;
    }

    /**
     * phone
     */
    
    public PhoneWithType phone() {
        return phone;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private MerchantPartnerCustomerId id;
        private Email emailAddress;
        private PhoneWithType phone;

        /**
         * id
         */
        
        public Builder id(MerchantPartnerCustomerId value) {
            this.id = value;
            return this;
        }

        /**
         * emailAddress
         */
        @JasonerProperty("email_address")
        public Builder emailAddress(Email value) {
            this.emailAddress = value;
            return this;
        }

        /**
         * phone
         */
        
        public Builder phone(PhoneWithType value) {
            this.phone = value;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }

    }


}

