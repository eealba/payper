package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The payment card to use to fund a payment. Can be a credit or debit card.
 */
public class Card {


    
    private final InstrumentId id;
    
    private final String name;
    
    private final String number;
    
    private final DateYearMonth expiry;
    @JasonerProperty("security_code")
    private final String securityCode;
    @JasonerProperty("last_digits")
    private final String lastDigits;
    @JasonerProperty("card_type")
    private final CardBrand cardType;
    
    private final CardType type;
    
    private final CardBrand brand;
    @JasonerProperty("billing_address")
    private final AddressPortable billingAddress;
    
    private final CardAttributes attributes;

    private Card(Builder builder) {
        id = builder.id;
        name = builder.name;
        number = builder.number;
        expiry = builder.expiry;
        securityCode = builder.securityCode;
        lastDigits = builder.lastDigits;
        cardType = builder.cardType;
        type = builder.type;
        brand = builder.brand;
        billingAddress = builder.billingAddress;
        attributes = builder.attributes;

    }

    /**
     * id
     */
    
    public InstrumentId id() {
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
     * type
     */
    
    public CardType type() {
        return type;
    }

    /**
     * brand
     */
    
    public CardBrand brand() {
        return brand;
    }

    /**
     * billingAddress
     */
    @JasonerProperty("billing_address")
    public AddressPortable billingAddress() {
        return billingAddress;
    }

    /**
     * attributes
     */
    
    public CardAttributes attributes() {
        return attributes;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private InstrumentId id;
        private String name;
        private String number;
        private DateYearMonth expiry;
        private String securityCode;
        private String lastDigits;
        private CardBrand cardType;
        private CardType type;
        private CardBrand brand;
        private AddressPortable billingAddress;
        private CardAttributes attributes;

        /**
         * id
         */
        
        public Builder id(InstrumentId value) {
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
         * type
         */
        
        public Builder type(CardType value) {
            this.type = value;
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
         * billingAddress
         */
        @JasonerProperty("billing_address")
        public Builder billingAddress(AddressPortable value) {
            this.billingAddress = value;
            return this;
        }

        /**
         * attributes
         */
        
        public Builder attributes(CardAttributes value) {
            this.attributes = value;
            return this;
        }

        public Card build() {
            return new Card(this);
        }

    }

}

