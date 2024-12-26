package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;


/**
 * Customizes the payer experience during the approval process for payment with PayPal.<blockquote><strong>Note:</strong> 
Partners and Marketplaces might configure <code>brand_name</code> and <code>shipping_preference</code> during partner 
account setup, which overrides the request values.</blockquote>
 */
public class PaypalWalletExperienceContext {


    @JasonerProperty("brand_name")
    private final String brandName;
    
    private final Language locale;
    @JasonerProperty("shipping_preference")
    private final ShippingPreference shippingPreference;
    @JasonerProperty("return_url")
    private final Url returnUrl;
    @JasonerProperty("cancel_url")
    private final Url cancelUrl;
    @JasonerProperty("landing_page")
    private final LandingPage landingPage;
    @JasonerProperty("user_action")
    private final UserAction userAction;
    @JasonerProperty("payment_method_preference")
    private final PaymentMethodPreference paymentMethodPreference;

    private PaypalWalletExperienceContext(Builder builder) {
        brandName = builder.brandName;
        locale = builder.locale;
        shippingPreference = builder.shippingPreference;
        returnUrl = builder.returnUrl;
        cancelUrl = builder.cancelUrl;
        landingPage = builder.landingPage;
        userAction = builder.userAction;
        paymentMethodPreference = builder.paymentMethodPreference;

    }

    /**
     * The label that overrides the business name in the PayPal account on the PayPal site. The pattern is defined by 
an external party and supports Unicode.
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
     * returnUrl
     */
    @JasonerProperty("return_url")
    public Url returnUrl() {
        return returnUrl;
    }

    /**
     * cancelUrl
     */
    @JasonerProperty("cancel_url")
    public Url cancelUrl() {
        return cancelUrl;
    }

    /**
     * The type of landing page to show on the PayPal site for customer checkout.
     */
    @JasonerProperty("landing_page")
    public LandingPage landingPage() {
        return landingPage;
    }

    /**
     * Configures a <strong>Continue</strong> or <strong>Pay Now</strong> checkout flow.
     */
    @JasonerProperty("user_action")
    public UserAction userAction() {
        return userAction;
    }

    /**
     * The merchant-preferred payment methods.
     */
    @JasonerProperty("payment_method_preference")
    public PaymentMethodPreference paymentMethodPreference() {
        return paymentMethodPreference;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String brandName;
        private Language locale;
        private ShippingPreference shippingPreference;
        private Url returnUrl;
        private Url cancelUrl;
        private LandingPage landingPage;
        private UserAction userAction;
        private PaymentMethodPreference paymentMethodPreference;

        /**
         * The label that overrides the business name in the PayPal account on the PayPal site. The pattern is defined by 
an external party and supports Unicode.
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
         * returnUrl
         */
        @JasonerProperty("return_url")
        public Builder returnUrl(Url value) {
            this.returnUrl = value;
            return this;
        }

        /**
         * cancelUrl
         */
        @JasonerProperty("cancel_url")
        public Builder cancelUrl(Url value) {
            this.cancelUrl = value;
            return this;
        }

        /**
         * The type of landing page to show on the PayPal site for customer checkout.
         */
        @JasonerProperty("landing_page")
        public Builder landingPage(LandingPage value) {
            this.landingPage = value;
            return this;
        }

        /**
         * Configures a <strong>Continue</strong> or <strong>Pay Now</strong> checkout flow.
         */
        @JasonerProperty("user_action")
        public Builder userAction(UserAction value) {
            this.userAction = value;
            return this;
        }

        /**
         * The merchant-preferred payment methods.
         */
        @JasonerProperty("payment_method_preference")
        public Builder paymentMethodPreference(PaymentMethodPreference value) {
            this.paymentMethodPreference = value;
            return this;
        }

        public PaypalWalletExperienceContext build() {
            return new PaypalWalletExperienceContext(this);
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
     * The type of landing page to show on the PayPal site for customer checkout.
     */
    public enum LandingPage {
        LOGIN,
        GUEST_CHECKOUT,
        NO_PREFERENCE
    }
    /**
     * Configures a <strong>Continue</strong> or <strong>Pay Now</strong> checkout flow.
     */
    public enum UserAction {
        CONTINUE,
        PAY_NOW
    }
    /**
     * The merchant-preferred payment methods.
     */
    public enum PaymentMethodPreference {
        UNRESTRICTED,
        IMMEDIATE_PAYMENT_REQUIRED
    }

}

