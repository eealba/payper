package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * Customizes the buyer experience during the approval process for payment with Venmo.<blockquote><strong>Note:</strong> 
Partners and Marketplaces might configure <code>shipping_preference</code> during partner account setup, which overrides 
the request values.</blockquote>
 */
public class VenmoWalletExperienceContext {


    @JasonerProperty("brand_name")
    private final String brandName;
    @JasonerProperty("shipping_preference")
    private final ShippingPreference shippingPreference;

    private VenmoWalletExperienceContext(Builder builder) {
        brandName = builder.brandName;
        shippingPreference = builder.shippingPreference;

    }

    /**
     * The business name of the merchant. The pattern is defined by an external party and supports Unicode.
     */
    @JasonerProperty("brand_name")
    public String brandName() {
        return brandName;
    }

    /**
     * The location from which the shipping address is derived.
     */
    @JasonerProperty("shipping_preference")
    public ShippingPreference shippingPreference() {
        return shippingPreference;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String brandName;
        private ShippingPreference shippingPreference;

        /**
         * The business name of the merchant. The pattern is defined by an external party and supports Unicode.
         */
        @JasonerProperty("brand_name")
        public Builder brandName(String value) {
            this.brandName = value;
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

        public VenmoWalletExperienceContext build() {
            return new VenmoWalletExperienceContext(this);
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

