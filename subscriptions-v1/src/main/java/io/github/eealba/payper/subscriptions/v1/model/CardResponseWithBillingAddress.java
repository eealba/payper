package io.github.eealba.payper.subscriptions.v1.model;


import io.github.eealba.jasoner.JasonerProperty;

public class CardResponseWithBillingAddress {

    @JasonerProperty("last_digits")
    private final String lastDigits;
    
    private final CardBrand brand;
    
    private final Type type;
    @JasonerProperty("authentication_result")
    private final AuthenticationResponse authenticationResult;
    
    private final String name;
    @JasonerProperty("billing_address")
    private final AddressPortable billingAddress;
    
    private final DateYearMonth expiry;
    @JasonerProperty("currency_code")
    private final CurrencyCode currencyCode;

    private CardResponseWithBillingAddress(Builder builder) {
        lastDigits = builder.lastDigits;
        brand = builder.brand;
        type = builder.type;
        authenticationResult = builder.authenticationResult;
        name = builder.name;
        billingAddress = builder.billingAddress;
        expiry = builder.expiry;
        currencyCode = builder.currencyCode;

    }

    @JasonerProperty("last_digits")
    public String lastDigits() {
        return lastDigits;
    }

    
    public CardBrand brand() {
        return brand;
    }

    
    public Type type() {
        return type;
    }

    @JasonerProperty("authentication_result")
    public AuthenticationResponse authenticationResult() {
        return authenticationResult;
    }

    
    public String name() {
        return name;
    }

    @JasonerProperty("billing_address")
    public AddressPortable billingAddress() {
        return billingAddress;
    }

    
    public DateYearMonth expiry() {
        return expiry;
    }

    @JasonerProperty("currency_code")
    public CurrencyCode currencyCode() {
        return currencyCode;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String lastDigits;
        private CardBrand brand;
        private Type type;
        private AuthenticationResponse authenticationResult;
        private String name;
        private AddressPortable billingAddress;
        private DateYearMonth expiry;
        private CurrencyCode currencyCode;

        @JasonerProperty("last_digits")
        public Builder lastDigits(String value) {
            this.lastDigits = value;
            return this;
        }

        
        public Builder brand(CardBrand value) {
            this.brand = value;
            return this;
        }

        
        public Builder type(Type value) {
            this.type = value;
            return this;
        }

        @JasonerProperty("authentication_result")
        public Builder authenticationResult(AuthenticationResponse value) {
            this.authenticationResult = value;
            return this;
        }

        
        public Builder name(String value) {
            this.name = value;
            return this;
        }

        @JasonerProperty("billing_address")
        public Builder billingAddress(AddressPortable value) {
            this.billingAddress = value;
            return this;
        }

        
        public Builder expiry(DateYearMonth value) {
            this.expiry = value;
            return this;
        }

        @JasonerProperty("currency_code")
        public Builder currencyCode(CurrencyCode value) {
            this.currencyCode = value;
            return this;
        }

        public CardResponseWithBillingAddress build() {
            return new CardResponseWithBillingAddress(this);
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

