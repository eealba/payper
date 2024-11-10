package io.github.eealba.payper.subscriptions.v1.model;


import java.util.Objects;
import io.github.eealba.jasoner.JasonerProperty;

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

    @JasonerProperty("brand_name")
    public String brandName() {
        return brandName;
    }

    
    public Language locale() {
        return locale;
    }

    @JasonerProperty("shipping_preference")
    public ShippingPreference shippingPreference() {
        return shippingPreference;
    }

    @JasonerProperty("user_action")
    public UserAction userAction() {
        return userAction;
    }

    @JasonerProperty("payment_method")
    public PaymentMethod paymentMethod() {
        return paymentMethod;
    }

    @JasonerProperty("return_url")
    public String returnUrl() {
        return returnUrl;
    }

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

        @JasonerProperty("brand_name")
        public Builder brandName(String value) {
            this.brandName = value;
            return this;
        }

        
        public Builder locale(Language value) {
            this.locale = value;
            return this;
        }

        @JasonerProperty("shipping_preference")
        public Builder shippingPreference(ShippingPreference value) {
            this.shippingPreference = value;
            return this;
        }

        @JasonerProperty("user_action")
        public Builder userAction(UserAction value) {
            this.userAction = value;
            return this;
        }

        @JasonerProperty("payment_method")
        public Builder paymentMethod(PaymentMethod value) {
            this.paymentMethod = value;
            return this;
        }

        @JasonerProperty("return_url")
        public Builder returnUrl(String value) {
            this.returnUrl = value;
            return this;
        }

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

