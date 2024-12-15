package io.github.eealba.payper.subscriptions.v1.model;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;

/**
 * The application context, which customizes the payer experience during the subscription approval process with PayPal.
 */
public class ApplicationContext {


    @JasonerProperty("brand_name")
    private final String brandName;
    
    private final Language locale;
    @JasonerProperty("shipping_preference")
    private final ShippingPreference shippingPreference;
    @JasonerProperty("user_action")
    private final UserAction userAction;
    @JasonerProperty("payment_method")
    private final PaymentMethod paymentMethod;
    @JasonerProperty("return_url")
    private final String returnUrl;
    @JasonerProperty("cancel_url")
    private final String cancelUrl;

    private ApplicationContext(Builder builder) {
        brandName = builder.brandName;
        locale = builder.locale;
        shippingPreference = builder.shippingPreference;
        userAction = builder.userAction;
        paymentMethod = builder.paymentMethod;
        returnUrl = Objects.requireNonNull(builder.returnUrl);
        cancelUrl = Objects.requireNonNull(builder.cancelUrl);
    }

    /**
     * The label that overrides the business name in the PayPal account on the PayPal site.
     */
    @JasonerProperty("brand_name")
    public String brandName() {
        return brandName;
    }

    /**
     * locale
     */
    
    public Language locale() {
        return locale;
    }

    /**
     * The location from which the shipping address is derived.
     */
    @JasonerProperty("shipping_preference")
    public ShippingPreference shippingPreference() {
        return shippingPreference;
    }

    /**
     * Configures the label name to `Continue` or `Subscribe Now` for subscription consent experience.
     */
    @JasonerProperty("user_action")
    public UserAction userAction() {
        return userAction;
    }

    /**
     * paymentMethod
     */
    @JasonerProperty("payment_method")
    public PaymentMethod paymentMethod() {
        return paymentMethod;
    }

    /**
     * The URL where the customer is redirected after the customer approves the payment.
     */
    @JasonerProperty("return_url")
    public String returnUrl() {
        return returnUrl;
    }

    /**
     * The URL where the customer is redirected after the customer cancels the payment.
     */
    @JasonerProperty("cancel_url")
    public String cancelUrl() {
        return cancelUrl;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String brandName;
        private Language locale;
        private ShippingPreference shippingPreference;
        private UserAction userAction;
        private PaymentMethod paymentMethod;
        private String returnUrl;
        private String cancelUrl;

        /**
         * The label that overrides the business name in the PayPal account on the PayPal site.
         */
        @JasonerProperty("brand_name")
        public Builder brandName(String value) {
            this.brandName = value;
            return this;
        }

        /**
         * locale
         */
        
        public Builder locale(Language value) {
            this.locale = value;
            return this;
        }

        /**
         * The location from which the shipping address is derived.
         */
        @JasonerProperty("shipping_preference")
        public Builder shippingPreference(ShippingPreference value) {
            this.shippingPreference = value;
            return this;
        }

        /**
         * Configures the label name to `Continue` or `Subscribe Now` for subscription consent experience.
         */
        @JasonerProperty("user_action")
        public Builder userAction(UserAction value) {
            this.userAction = value;
            return this;
        }

        /**
         * paymentMethod
         */
        @JasonerProperty("payment_method")
        public Builder paymentMethod(PaymentMethod value) {
            this.paymentMethod = value;
            return this;
        }

        /**
         * The URL where the customer is redirected after the customer approves the payment.
         */
        @JasonerProperty("return_url")
        public Builder returnUrl(String value) {
            this.returnUrl = value;
            return this;
        }

        /**
         * The URL where the customer is redirected after the customer cancels the payment.
         */
        @JasonerProperty("cancel_url")
        public Builder cancelUrl(String value) {
            this.cancelUrl = value;
            return this;
        }

        public ApplicationContext build() {
            return new ApplicationContext(this);
        }

    }
    /**
     * The location from which the shipping address is derived.
     */
    public enum ShippingPreference {
        GET_FROM_FILE,
        NO_SHIPPING,
        SET_PROVIDED_ADDRESS
    }
    /**
     * Configures the label name to `Continue` or `Subscribe Now` for subscription consent experience.
     */
    public enum UserAction {
        CONTINUE,
        SUBSCRIBE_NOW
    }
}

