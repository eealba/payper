package io.github.eealba.payper.orders.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;


/**
 * The level 3 card processing data collections, If your merchant account has been configured for Level 3 processing this 
field will be passed to the processor on your behalf. Please contact your PayPal Technical Account Manager to define 
level 3 data for your business.
 */
public class Level3CardProcessingData {


    @JasonerProperty("shipping_amount")
    private final Money shippingAmount;
    @JasonerProperty("duty_amount")
    private final Money dutyAmount;
    @JasonerProperty("discount_amount")
    private final Money discountAmount;
    @JasonerProperty("shipping_address")
    private final AddressPortable shippingAddress;
    @JasonerProperty("ships_from_postal_code")
    private final String shipsFromPostalCode;
    @JasonerProperty("line_items")
    private final List<LineItem> lineItems;

    private Level3CardProcessingData(Builder builder) {
        shippingAmount = builder.shippingAmount;
        dutyAmount = builder.dutyAmount;
        discountAmount = builder.discountAmount;
        shippingAddress = builder.shippingAddress;
        shipsFromPostalCode = builder.shipsFromPostalCode;
        lineItems = builder.lineItems;

    }

    /**
     * shippingAmount
     */
    @JasonerProperty("shipping_amount")
    public Money shippingAmount() {
        return shippingAmount;
    }

    /**
     * dutyAmount
     */
    @JasonerProperty("duty_amount")
    public Money dutyAmount() {
        return dutyAmount;
    }

    /**
     * discountAmount
     */
    @JasonerProperty("discount_amount")
    public Money discountAmount() {
        return discountAmount;
    }

    /**
     * shippingAddress
     */
    @JasonerProperty("shipping_address")
    public AddressPortable shippingAddress() {
        return shippingAddress;
    }

    /**
     * Use this field to specify the postal code of the shipping location.
     */
    @JasonerProperty("ships_from_postal_code")
    public String shipsFromPostalCode() {
        return shipsFromPostalCode;
    }

    /**
     * A list of the items that were purchased with this payment. If your merchant account has been configured for 
Level 3 processing this field will be passed to the processor on your behalf.
     */
    @JasonerProperty("line_items")
    public List<LineItem> lineItems() {
        return lineItems;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Money shippingAmount;
        private Money dutyAmount;
        private Money discountAmount;
        private AddressPortable shippingAddress;
        private String shipsFromPostalCode;
        private List<LineItem> lineItems;

        /**
         * shippingAmount
         */
        @JasonerProperty("shipping_amount")
        public Builder shippingAmount(Money value) {
            this.shippingAmount = value;
            return this;
        }

        /**
         * dutyAmount
         */
        @JasonerProperty("duty_amount")
        public Builder dutyAmount(Money value) {
            this.dutyAmount = value;
            return this;
        }

        /**
         * discountAmount
         */
        @JasonerProperty("discount_amount")
        public Builder discountAmount(Money value) {
            this.discountAmount = value;
            return this;
        }

        /**
         * shippingAddress
         */
        @JasonerProperty("shipping_address")
        public Builder shippingAddress(AddressPortable value) {
            this.shippingAddress = value;
            return this;
        }

        /**
         * Use this field to specify the postal code of the shipping location.
         */
        @JasonerProperty("ships_from_postal_code")
        public Builder shipsFromPostalCode(String value) {
            this.shipsFromPostalCode = value;
            return this;
        }

        /**
         * A list of the items that were purchased with this payment. If your merchant account has been configured for 
Level 3 processing this field will be passed to the processor on your behalf.
         */
        @JasonerProperty("line_items")
        public Builder lineItems(List<LineItem> value) {
            this.lineItems = value;
            return this;
        }

        public Level3CardProcessingData build() {
            return new Level3CardProcessingData(this);
        }

    }


}

