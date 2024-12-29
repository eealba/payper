package io.github.eealba.payper.payments.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;

/**
 * Reference values used by the card network to identify a transaction.
 */
public class NetworkTransactionReference {


    
    private final String id;
    
    private final String date;
    
    private final CardBrand network;
    @JasonerProperty("acquirer_reference_number")
    private final String acquirerReferenceNumber;

    private NetworkTransactionReference(Builder builder) {
        date = builder.date;
        network = builder.network;
        acquirerReferenceNumber = builder.acquirerReferenceNumber;
        id = Objects.requireNonNull(builder.id);
    }

    /**
     * Transaction reference id returned by the scheme. For Visa and Amex, this is the "Tran id" field in response. 
For MasterCard, this is the "BankNet reference id" field in response. For Discover, this is the "NRID" field 
in response. The pattern we expect for this field from Visa/Amex/CB/Discover is numeric, Mastercard/BNPP is 
alphanumeric and Paysecure is alphanumeric with special character -.
     */
    
    public String id() {
        return id;
    }

    /**
     * The date that the transaction was authorized by the scheme. This field may not be returned for all networks. 
MasterCard refers to this field as "BankNet reference date.
     */
    
    public String date() {
        return date;
    }

    /**
     * network
     */
    
    public CardBrand network() {
        return network;
    }

    /**
     * Reference ID issued for the card transaction. This ID can be used to track the transaction across processors, 
card brands and issuing banks.
     */
    @JasonerProperty("acquirer_reference_number")
    public String acquirerReferenceNumber() {
        return acquirerReferenceNumber;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String id;
        private String date;
        private CardBrand network;
        private String acquirerReferenceNumber;

        /**
         * Transaction reference id returned by the scheme. For Visa and Amex, this is the "Tran id" field in response. 
For MasterCard, this is the "BankNet reference id" field in response. For Discover, this is the "NRID" field 
in response. The pattern we expect for this field from Visa/Amex/CB/Discover is numeric, Mastercard/BNPP is 
alphanumeric and Paysecure is alphanumeric with special character -.
         */
        
        public Builder id(String value) {
            this.id = value;
            return this;
        }

        /**
         * The date that the transaction was authorized by the scheme. This field may not be returned for all networks. 
MasterCard refers to this field as "BankNet reference date.
         */
        
        public Builder date(String value) {
            this.date = value;
            return this;
        }

        /**
         * network
         */
        
        public Builder network(CardBrand value) {
            this.network = value;
            return this;
        }

        /**
         * Reference ID issued for the card transaction. This ID can be used to track the transaction across processors, 
card brands and issuing banks.
         */
        @JasonerProperty("acquirer_reference_number")
        public Builder acquirerReferenceNumber(String value) {
            this.acquirerReferenceNumber = value;
            return this;
        }

        public NetworkTransactionReference build() {
            return new NetworkTransactionReference(this);
        }

    }

}

