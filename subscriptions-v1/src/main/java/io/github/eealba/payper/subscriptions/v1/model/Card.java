package io.github.eealba.payper.subscriptions.v1.model;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;

/**
 * The payment card to use to fund a payment. Can be a credit or debit card.
 */
public class Card {


    
    private final String id;
    
    private final String name;
    
    private final String number;
    
    private final DateYearMonth expiry;
    @JasonerProperty("security_code")
    private final String securityCode;
    @JasonerProperty("last_digits")
    private final String lastDigits;
    @JasonerProperty("card_type")
    private final CardBrand cardType;
    @JasonerProperty("billing_address")
    private final AddressPortable billingAddress;

    private Card(Builder builder) {
        id = builder.id;
        name = builder.name;
        securityCode = builder.securityCode;
        lastDigits = builder.lastDigits;
        cardType = builder.cardType;
        billingAddress = builder.billingAddress;
        number = Objects.requireNonNull(builder.number);
        expiry = Objects.requireNonNull(builder.expiry);
    }

    /**
     * The PayPal-generated ID for the card.
     */
    
    public String id() {
        return id;
    }

    /**
     * The card holder's name as it appears on the card.
     */
    
    public String name() {
        return name;
    }

    /**
     * The primary account number (PAN) for the payment card.
     */
    
    public String number() {
        return number;
    }

    /**
     * expiry
     */
    
    public DateYearMonth expiry() {
        return expiry;
    }

    /**
     * The three- or four-digit security code of the card. Also known as the CVV, CVC, CVN, CVE, or CID. This 
parameter cannot be present in the request when `payment_initiator=MERCHANT`.
     */
    @JasonerProperty("security_code")
    public String securityCode() {
        return securityCode;
    }

    /**
     * The last digits of the payment card.
     */
    @JasonerProperty("last_digits")
    public String lastDigits() {
        return lastDigits;
    }

    /**
     * cardType
     */
    @JasonerProperty("card_type")
    public CardBrand cardType() {
        return cardType;
    }

    /**
     * billingAddress
     */
    @JasonerProperty("billing_address")
    public AddressPortable billingAddress() {
        return billingAddress;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String id;
        private String name;
        private String number;
        private DateYearMonth expiry;
        private String securityCode;
        private String lastDigits;
        private CardBrand cardType;
        private AddressPortable billingAddress;

        /**
         * The PayPal-generated ID for the card.
         */
        
        public Builder id(String value) {
            this.id = value;
            return this;
        }

        /**
         * The card holder's name as it appears on the card.
         */
        
        public Builder name(String value) {
            this.name = value;
            return this;
        }

        /**
         * The primary account number (PAN) for the payment card.
         */
        
        public Builder number(String value) {
            this.number = value;
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
         * The three- or four-digit security code of the card. Also known as the CVV, CVC, CVN, CVE, or CID. This 
parameter cannot be present in the request when `payment_initiator=MERCHANT`.
         */
        @JasonerProperty("security_code")
        public Builder securityCode(String value) {
            this.securityCode = value;
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
         * cardType
         */
        @JasonerProperty("card_type")
        public Builder cardType(CardBrand value) {
            this.cardType = value;
            return this;
        }

        /**
         * billingAddress
         */
        @JasonerProperty("billing_address")
        public Builder billingAddress(AddressPortable value) {
            this.billingAddress = value;
            return this;
        }

        public Card build() {
            return new Card(this);
        }

    }

}

