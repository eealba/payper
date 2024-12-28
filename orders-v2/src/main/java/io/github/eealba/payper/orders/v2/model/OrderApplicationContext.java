package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * Customizes the payer experience during the approval process for the payment with 
PayPal.<blockquote><strong>Note:</strong> Partners and Marketplaces might configure <code>brand_name</code> and 
<code>shipping_preference</code> during partner account setup, which overrides the request values.</blockquote>
 */
public class OrderApplicationContext {


    @JasonerProperty("brand_name")
    private final String brandName;
    
    private final Language locale;
    @JasonerProperty("landing_page")
    private final LandingPage landingPage;
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
    @JasonerProperty("stored_payment_source")
    private final StoredPaymentSource storedPaymentSource;

    private OrderApplicationContext(Builder builder) {
        brandName = builder.brandName;
        locale = builder.locale;
        landingPage = builder.landingPage;
        shippingPreference = builder.shippingPreference;
        userAction = builder.userAction;
        paymentMethod = builder.paymentMethod;
        returnUrl = builder.returnUrl;
        cancelUrl = builder.cancelUrl;
        storedPaymentSource = builder.storedPaymentSource;

    }

    /**
     * DEPRECATED. The label that overrides the business name in the PayPal account on the PayPal site. The fields in 
`application_context` are now available in the `experience_context` object under the `payment_source` which 
supports them (eg. `payment_source.paypal.experience_context.brand_name`). Please specify this field in the 
`experience_context` object instead of the `application_context` object.
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
     * DEPRECATED. DEPRECATED. The type of landing page to show on the PayPal site for customer checkout. The fields 
in `application_context` are now available in the `experience_context` object under the `payment_source` which 
supports them (eg. `payment_source.paypal.experience_context.landing_page`). Please specify this field in the 
`experience_context` object instead of the `application_context` object.
     */
    @JasonerProperty("landing_page")
    public LandingPage landingPage() {
        return landingPage;
    }

    /**
     * DEPRECATED. DEPRECATED. The shipping preference:<ul><li>Displays the shipping address to the 
customer.</li><li>Enables the customer to choose an address on the PayPal site.</li><li>Restricts the customer 
from changing the address during the payment-approval process.</li></ul>. The fields in `application_context` 
are now available in the `experience_context` object under the `payment_source` which supports them (eg. 
`payment_source.paypal.experience_context.shipping_preference`). Please specify this field in the 
`experience_context` object instead of the `application_context` object.
     */
    @JasonerProperty("shipping_preference")
    public ShippingPreference shippingPreference() {
        return shippingPreference;
    }

    /**
     * DEPRECATED. Configures a <strong>Continue</strong> or <strong>Pay Now</strong> checkout flow. The fields in 
`application_context` are now available in the `experience_context` object under the `payment_source` which 
supports them (eg. `payment_source.paypal.experience_context.user_action`). Please specify this field in the 
`experience_context` object instead of the `application_context` object.
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
     * DEPRECATED. The URL where the customer is redirected after the customer approves the payment. The fields in 
`application_context` are now available in the `experience_context` object under the `payment_source` which 
supports them (eg. `payment_source.paypal.experience_context.return_url`). Please specify this field in the 
`experience_context` object instead of the `application_context` object.
     */
    @JasonerProperty("return_url")
    public String returnUrl() {
        return returnUrl;
    }

    /**
     * DEPRECATED. The URL where the customer is redirected after the customer cancels the payment. The fields in 
`application_context` are now available in the `experience_context` object under the `payment_source` which 
supports them (eg. `payment_source.paypal.experience_context.cancel_url`). Please specify this field in the 
`experience_context` object instead of the `application_context` object.
     */
    @JasonerProperty("cancel_url")
    public String cancelUrl() {
        return cancelUrl;
    }

    /**
     * storedPaymentSource
     */
    @JasonerProperty("stored_payment_source")
    public StoredPaymentSource storedPaymentSource() {
        return storedPaymentSource;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String brandName;
        private Language locale;
        private LandingPage landingPage;
        private ShippingPreference shippingPreference;
        private UserAction userAction;
        private PaymentMethod paymentMethod;
        private String returnUrl;
        private String cancelUrl;
        private StoredPaymentSource storedPaymentSource;

        /**
         * DEPRECATED. The label that overrides the business name in the PayPal account on the PayPal site. The fields in 
`application_context` are now available in the `experience_context` object under the `payment_source` which 
supports them (eg. `payment_source.paypal.experience_context.brand_name`). Please specify this field in the 
`experience_context` object instead of the `application_context` object.
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
         * DEPRECATED. DEPRECATED. The type of landing page to show on the PayPal site for customer checkout. The fields 
in `application_context` are now available in the `experience_context` object under the `payment_source` which 
supports them (eg. `payment_source.paypal.experience_context.landing_page`). Please specify this field in the 
`experience_context` object instead of the `application_context` object.
         */
        @JasonerProperty("landing_page")
        public Builder landingPage(LandingPage value) {
            this.landingPage = value;
            return this;
        }

        /**
         * DEPRECATED. DEPRECATED. The shipping preference:<ul><li>Displays the shipping address to the 
customer.</li><li>Enables the customer to choose an address on the PayPal site.</li><li>Restricts the customer 
from changing the address during the payment-approval process.</li></ul>. The fields in `application_context` 
are now available in the `experience_context` object under the `payment_source` which supports them (eg. 
`payment_source.paypal.experience_context.shipping_preference`). Please specify this field in the 
`experience_context` object instead of the `application_context` object.
         */
        @JasonerProperty("shipping_preference")
        public Builder shippingPreference(ShippingPreference value) {
            this.shippingPreference = value;
            return this;
        }

        /**
         * DEPRECATED. Configures a <strong>Continue</strong> or <strong>Pay Now</strong> checkout flow. The fields in 
`application_context` are now available in the `experience_context` object under the `payment_source` which 
supports them (eg. `payment_source.paypal.experience_context.user_action`). Please specify this field in the 
`experience_context` object instead of the `application_context` object.
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
         * DEPRECATED. The URL where the customer is redirected after the customer approves the payment. The fields in 
`application_context` are now available in the `experience_context` object under the `payment_source` which 
supports them (eg. `payment_source.paypal.experience_context.return_url`). Please specify this field in the 
`experience_context` object instead of the `application_context` object.
         */
        @JasonerProperty("return_url")
        public Builder returnUrl(String value) {
            this.returnUrl = value;
            return this;
        }

        /**
         * DEPRECATED. The URL where the customer is redirected after the customer cancels the payment. The fields in 
`application_context` are now available in the `experience_context` object under the `payment_source` which 
supports them (eg. `payment_source.paypal.experience_context.cancel_url`). Please specify this field in the 
`experience_context` object instead of the `application_context` object.
         */
        @JasonerProperty("cancel_url")
        public Builder cancelUrl(String value) {
            this.cancelUrl = value;
            return this;
        }

        /**
         * storedPaymentSource
         */
        @JasonerProperty("stored_payment_source")
        public Builder storedPaymentSource(StoredPaymentSource value) {
            this.storedPaymentSource = value;
            return this;
        }

        public OrderApplicationContext build() {
            return new OrderApplicationContext(this);
        }

    }
    /**
     * DEPRECATED. DEPRECATED. The type of landing page to show on the PayPal site for customer checkout.  The fields in `application_context` are now available in the `experience_context` object under the `payment_source` which supports them (eg. `payment_source.paypal.experience_context.landing_page`). Please specify this field in the `experience_context` object instead of the `application_context` object.
     */
    public enum LandingPage {
        LOGIN,
        BILLING,
        NO_PREFERENCE
    }
    /**
     * DEPRECATED. DEPRECATED. The shipping preference:<ul><li>Displays the shipping address to the customer.</li><li>Enables the customer to choose an address on the PayPal site.</li><li>Restricts the customer from changing the address during the payment-approval process.</li></ul>.  The fields in `application_context` are now available in the `experience_context` object under the `payment_source` which supports them (eg. `payment_source.paypal.experience_context.shipping_preference`). Please specify this field in the `experience_context` object instead of the `application_context` object.
     */
    public enum ShippingPreference {
        GET_FROM_FILE,
        NO_SHIPPING,
        SET_PROVIDED_ADDRESS
    }
    /**
     * DEPRECATED. Configures a <strong>Continue</strong> or <strong>Pay Now</strong> checkout flow.  The fields in `application_context` are now available in the `experience_context` object under the `payment_source` which supports them (eg. `payment_source.paypal.experience_context.user_action`). Please specify this field in the `experience_context` object instead of the `application_context` object.
     */
    public enum UserAction {
        CONTINUE,
        PAY_NOW
    }
}

