package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;


/**
 * Representation of card details as received in the request.
 */
public class CardFromRequest {


    
    private final DateYearMonth expiry;
    @JasonerProperty("last_digits")
    private final String lastDigits;

    private CardFromRequest(Builder builder) {
        expiry = builder.expiry;
        lastDigits = builder.lastDigits;

    }

    /**
     * expiry
     */
    
    public DateYearMonth expiry() {
        return expiry;
    }

    /**
     * The last digits of the payment card.
     */
    @JasonerProperty("last_digits")
    public String lastDigits() {
        return lastDigits;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private DateYearMonth expiry;
        private String lastDigits;

        /**
         * expiry
         */
        
        public Builder expiry(DateYearMonth value) {
            this.expiry = value;
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

        public CardFromRequest build() {
            return new CardFromRequest(this);
        }

    }


}

