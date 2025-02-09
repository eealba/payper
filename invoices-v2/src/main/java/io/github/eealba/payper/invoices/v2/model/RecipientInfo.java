package io.github.eealba.payper.invoices.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The billing and shipping information. Includes name, email, address, phone, and language.
 */
public class RecipientInfo {


    @JasonerProperty("billing_info")
    private final BillingInfo billingInfo;
    @JasonerProperty("shipping_info")
    private final ContactNameAddress shippingInfo;

    private RecipientInfo(Builder builder) {
        billingInfo = builder.billingInfo;
        shippingInfo = builder.shippingInfo;
    }

    /**
     * billingInfo
     */
    @JasonerProperty("billing_info")
    public BillingInfo billingInfo() {
        return billingInfo;
    }

    /**
     * shippingInfo
     */
    @JasonerProperty("shipping_info")
    public ContactNameAddress shippingInfo() {
        return shippingInfo;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private BillingInfo billingInfo;
        private ContactNameAddress shippingInfo;

        /**
         * billingInfo
         */
        @JasonerProperty("billing_info")
        public Builder billingInfo(BillingInfo value) {
            this.billingInfo = value;
            return this;
        }

        /**
         * shippingInfo
         */
        @JasonerProperty("shipping_info")
        public Builder shippingInfo(ContactNameAddress value) {
            this.shippingInfo = value;
            return this;
        }

        public RecipientInfo build() {
            return new RecipientInfo(this);
        }

    }

}

