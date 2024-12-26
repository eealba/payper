package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;


/**
 * Information used to pay using BLIK one-click flow.
 */
public class BlikOneClick {


    @JasonerProperty("auth_code")
    private final String authCode;
    @JasonerProperty("consumer_reference")
    private final String consumerReference;
    @JasonerProperty("alias_label")
    private final String aliasLabel;
    @JasonerProperty("alias_key")
    private final String aliasKey;

    private BlikOneClick(Builder builder) {
        authCode = builder.authCode;
        aliasLabel = builder.aliasLabel;
        aliasKey = builder.aliasKey;
        consumerReference = Objects.requireNonNull(builder.consumerReference);
    }

    /**
     * The 6-digit code used to authenticate a consumer within BLIK.
     */
    @JasonerProperty("auth_code")
    public String authCode() {
        return authCode;
    }

    /**
     * The merchant generated, unique reference serving as a primary identifier for accounts connected between Blik 
and a merchant.
     */
    @JasonerProperty("consumer_reference")
    public String consumerReference() {
        return consumerReference;
    }

    /**
     * A bank defined identifier used as a display name to allow the payer to differentiate between multiple 
registered bank accounts.
     */
    @JasonerProperty("alias_label")
    public String aliasLabel() {
        return aliasLabel;
    }

    /**
     * A Blik-defined identifier for a specific Blik-enabled bank account that is associated with a given merchant. 
Used only in conjunction with a Consumer Reference.
     */
    @JasonerProperty("alias_key")
    public String aliasKey() {
        return aliasKey;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String authCode;
        private String consumerReference;
        private String aliasLabel;
        private String aliasKey;

        /**
         * The 6-digit code used to authenticate a consumer within BLIK.
         */
        @JasonerProperty("auth_code")
        public Builder authCode(String value) {
            this.authCode = value;
            return this;
        }

        /**
         * The merchant generated, unique reference serving as a primary identifier for accounts connected between Blik 
and a merchant.
         */
        @JasonerProperty("consumer_reference")
        public Builder consumerReference(String value) {
            this.consumerReference = value;
            return this;
        }

        /**
         * A bank defined identifier used as a display name to allow the payer to differentiate between multiple 
registered bank accounts.
         */
        @JasonerProperty("alias_label")
        public Builder aliasLabel(String value) {
            this.aliasLabel = value;
            return this;
        }

        /**
         * A Blik-defined identifier for a specific Blik-enabled bank account that is associated with a given merchant. 
Used only in conjunction with a Consumer Reference.
         */
        @JasonerProperty("alias_key")
        public Builder aliasKey(String value) {
            this.aliasKey = value;
            return this;
        }

        public BlikOneClick build() {
            return new BlikOneClick(this);
        }

    }


}

