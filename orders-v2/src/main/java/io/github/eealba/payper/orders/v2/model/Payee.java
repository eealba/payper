package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The merchant who receives the funds and fulfills the order. The merchant is also known as the payee.
 */
public class Payee {


    @JasonerProperty("email_address")
    private final Email emailAddress;
    @JasonerProperty("merchant_id")
    private final AccountId merchantId;

    private Payee(Builder builder) {
        emailAddress = builder.emailAddress;
        merchantId = builder.merchantId;

    }

    /**
     * emailAddress
     */
    @JasonerProperty("email_address")
    public Email emailAddress() {
        return emailAddress;
    }

    /**
     * merchantId
     */
    @JasonerProperty("merchant_id")
    public AccountId merchantId() {
        return merchantId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Email emailAddress;
        private AccountId merchantId;

        /**
         * emailAddress
         */
        @JasonerProperty("email_address")
        public Builder emailAddress(Email value) {
            this.emailAddress = value;
            return this;
        }

        /**
         * merchantId
         */
        @JasonerProperty("merchant_id")
        public Builder merchantId(AccountId value) {
            this.merchantId = value;
            return this;
        }

        public Payee build() {
            return new Payee(this);
        }

    }

}

