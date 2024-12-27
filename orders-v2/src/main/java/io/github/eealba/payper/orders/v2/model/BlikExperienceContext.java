package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * Customizes the payer experience during the approval process for the BLIK payment.
 */
public class BlikExperienceContext {


    @JasonerProperty("brand_name")
    private final String brandName;
    
    private final Language locale;
    @JasonerProperty("shipping_preference")
    private final ShippingPreference shippingPreference;
    @JasonerProperty("return_url")
    private final Url returnUrl;
    @JasonerProperty("cancel_url")
    private final Url cancelUrl;
    @JasonerProperty("consumer_ip")
    private final IpAddress consumerIp;
    @JasonerProperty("consumer_user_agent")
    private final String consumerUserAgent;

    private BlikExperienceContext(Builder builder) {
        brandName = builder.brandName;
        locale = builder.locale;
        shippingPreference = builder.shippingPreference;
        returnUrl = builder.returnUrl;
        cancelUrl = builder.cancelUrl;
        consumerIp = builder.consumerIp;
        consumerUserAgent = builder.consumerUserAgent;

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
     * consumerIp
     */
    @JasonerProperty("consumer_ip")
    public IpAddress consumerIp() {
        return consumerIp;
    }

    /**
     * The payer's User Agent. For example, Mozilla/5.0 (Macintosh; Intel Mac OS X x.y; rv:42.0).
     */
    @JasonerProperty("consumer_user_agent")
    public String consumerUserAgent() {
        return consumerUserAgent;
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
        private IpAddress consumerIp;
        private String consumerUserAgent;

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
         * consumerIp
         */
        @JasonerProperty("consumer_ip")
        public Builder consumerIp(IpAddress value) {
            this.consumerIp = value;
            return this;
        }

        /**
         * The payer's User Agent. For example, Mozilla/5.0 (Macintosh; Intel Mac OS X x.y; rv:42.0).
         */
        @JasonerProperty("consumer_user_agent")
        public Builder consumerUserAgent(String value) {
            this.consumerUserAgent = value;
            return this;
        }

        public BlikExperienceContext build() {
            return new BlikExperienceContext(this);
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
}

