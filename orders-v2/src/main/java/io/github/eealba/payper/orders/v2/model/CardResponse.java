package io.github.eealba.payper.orders.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * The payment card to use to fund a payment. Card can be a credit or debit card.
 */
public class CardResponse {


    
    private final String name;
    @JasonerProperty("last_digits")
    private final String lastDigits;
    
    private final CardBrand brand;
    @JasonerProperty("available_networks")
    private final List<CardBrand> availableNetworks;
    
    private final Type type;
    @JasonerProperty("authentication_result")
    private final AuthenticationResponse authenticationResult;
    
    private final CardAttributesResponse attributes;
    @JasonerProperty("from_request")
    private final CardFromRequest fromRequest;
    
    private final DateYearMonth expiry;
    @JasonerProperty("bin_details")
    private final BinDetails binDetails;

    private CardResponse(Builder builder) {
        name = builder.name;
        lastDigits = builder.lastDigits;
        brand = builder.brand;
        availableNetworks = builder.availableNetworks;
        type = builder.type;
        authenticationResult = builder.authenticationResult;
        attributes = builder.attributes;
        fromRequest = builder.fromRequest;
        expiry = builder.expiry;
        binDetails = builder.binDetails;

    }

    /**
     * The card holder's name as it appears on the card.
     */
    
    public String name() {
        return name;
    }

    /**
     * The last digits of the payment card.
     */
    @JasonerProperty("last_digits")
    public String lastDigits() {
        return lastDigits;
    }

    /**
     * brand
     */
    
    public CardBrand brand() {
        return brand;
    }

    /**
     * Array of brands or networks associated with the card.
     */
    @JasonerProperty("available_networks")
    public List<CardBrand> availableNetworks() {
        return availableNetworks;
    }

    /**
     * The payment card type.
     */
    
    public Type type() {
        return type;
    }

    /**
     * authenticationResult
     */
    @JasonerProperty("authentication_result")
    public AuthenticationResponse authenticationResult() {
        return authenticationResult;
    }

    /**
     * attributes
     */
    
    public CardAttributesResponse attributes() {
        return attributes;
    }

    /**
     * fromRequest
     */
    @JasonerProperty("from_request")
    public CardFromRequest fromRequest() {
        return fromRequest;
    }

    /**
     * expiry
     */
    
    public DateYearMonth expiry() {
        return expiry;
    }

    /**
     * binDetails
     */
    @JasonerProperty("bin_details")
    public BinDetails binDetails() {
        return binDetails;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String name;
        private String lastDigits;
        private CardBrand brand;
        private List<CardBrand> availableNetworks;
        private Type type;
        private AuthenticationResponse authenticationResult;
        private CardAttributesResponse attributes;
        private CardFromRequest fromRequest;
        private DateYearMonth expiry;
        private BinDetails binDetails;

        /**
         * The card holder's name as it appears on the card.
         */
        
        public Builder name(String value) {
            this.name = value;
            return this;
        }

        /**
         * The last digits of the payment card.
         */
        @JasonerProperty("last_digits")
        public Builder lastDigits(String value) {
            this.lastDigits = value;
            return this;
        }

        /**
         * brand
         */
        
        public Builder brand(CardBrand value) {
            this.brand = value;
            return this;
        }

        /**
         * Array of brands or networks associated with the card.
         */
        @JasonerProperty("available_networks")
        public Builder availableNetworks(List<CardBrand> value) {
            this.availableNetworks = value;
            return this;
        }

        /**
         * The payment card type.
         */
        
        public Builder type(Type value) {
            this.type = value;
            return this;
        }

        /**
         * authenticationResult
         */
        @JasonerProperty("authentication_result")
        public Builder authenticationResult(AuthenticationResponse value) {
            this.authenticationResult = value;
            return this;
        }

        /**
         * attributes
         */
        
        public Builder attributes(CardAttributesResponse value) {
            this.attributes = value;
            return this;
        }

        /**
         * fromRequest
         */
        @JasonerProperty("from_request")
        public Builder fromRequest(CardFromRequest value) {
            this.fromRequest = value;
            return this;
        }

        /**
         * expiry
         */
        
        public Builder expiry(DateYearMonth value) {
            this.expiry = value;
            return this;
        }

        /**
         * binDetails
         */
        @JasonerProperty("bin_details")
        public Builder binDetails(BinDetails value) {
            this.binDetails = value;
            return this;
        }

        public CardResponse build() {
            return new CardResponse(this);
        }

    }
    /**
     * The payment card type.
     */
    public enum Type {
        CREDIT,
        DEBIT,
        PREPAID,
        UNKNOWN
    }
}

