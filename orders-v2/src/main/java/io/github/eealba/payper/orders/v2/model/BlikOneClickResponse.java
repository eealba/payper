package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;


/**
 * Information used to pay using BLIK one-click flow.
 */
public class BlikOneClickResponse {


    @JasonerProperty("consumer_reference")
    private final String consumerReference;

    private BlikOneClickResponse(Builder builder) {
        consumerReference = builder.consumerReference;

    }

    /**
     * The merchant generated, unique reference serving as a primary identifier for accounts connected between Blik 
and a merchant.
     */
    @JasonerProperty("consumer_reference")
    public String consumerReference() {
        return consumerReference;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String consumerReference;

        /**
         * The merchant generated, unique reference serving as a primary identifier for accounts connected between Blik 
and a merchant.
         */
        @JasonerProperty("consumer_reference")
        public Builder consumerReference(String value) {
            this.consumerReference = value;
            return this;
        }

        public BlikOneClickResponse build() {
            return new BlikOneClickResponse(this);
        }

    }


}

