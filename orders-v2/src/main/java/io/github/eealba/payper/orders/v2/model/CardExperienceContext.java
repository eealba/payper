package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;


/**
 * Customizes the payer experience during the 3DS Approval for payment.
 */
public class CardExperienceContext {


    @JasonerProperty("return_url")
    private final String returnUrl;
    @JasonerProperty("cancel_url")
    private final String cancelUrl;

    private CardExperienceContext(Builder builder) {
        returnUrl = builder.returnUrl;
        cancelUrl = builder.cancelUrl;

    }

    /**
     * returnUrl
     */
    @JasonerProperty("return_url")
    public String returnUrl() {
        return returnUrl;
    }

    /**
     * cancelUrl
     */
    @JasonerProperty("cancel_url")
    public String cancelUrl() {
        return cancelUrl;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String returnUrl;
        private String cancelUrl;

        /**
         * returnUrl
         */
        @JasonerProperty("return_url")
        public Builder returnUrl(String value) {
            this.returnUrl = value;
            return this;
        }

        /**
         * cancelUrl
         */
        @JasonerProperty("cancel_url")
        public Builder cancelUrl(String value) {
            this.cancelUrl = value;
            return this;
        }

        public CardExperienceContext build() {
            return new CardExperienceContext(this);
        }

    }


}

