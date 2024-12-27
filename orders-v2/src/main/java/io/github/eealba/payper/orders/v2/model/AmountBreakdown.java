package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The breakdown of the amount. Breakdown provides details such as total item amount, total tax amount, shipping, handling, 
insurance, and discounts, if any.
 */
public class AmountBreakdown {


    @JasonerProperty("item_total")
    private final Money itemTotal;
    
    private final Money shipping;
    
    private final Money handling;
    @JasonerProperty("tax_total")
    private final Money taxTotal;
    
    private final Money insurance;
    @JasonerProperty("shipping_discount")
    private final Money shippingDiscount;
    
    private final Money discount;

    private AmountBreakdown(Builder builder) {
        itemTotal = builder.itemTotal;
        shipping = builder.shipping;
        handling = builder.handling;
        taxTotal = builder.taxTotal;
        insurance = builder.insurance;
        shippingDiscount = builder.shippingDiscount;
        discount = builder.discount;

    }

    /**
     * itemTotal
     */
    @JasonerProperty("item_total")
    public Money itemTotal() {
        return itemTotal;
    }

    /**
     * shipping
     */
    
    public Money shipping() {
        return shipping;
    }

    /**
     * handling
     */
    
    public Money handling() {
        return handling;
    }

    /**
     * taxTotal
     */
    @JasonerProperty("tax_total")
    public Money taxTotal() {
        return taxTotal;
    }

    /**
     * insurance
     */
    
    public Money insurance() {
        return insurance;
    }

    /**
     * shippingDiscount
     */
    @JasonerProperty("shipping_discount")
    public Money shippingDiscount() {
        return shippingDiscount;
    }

    /**
     * discount
     */
    
    public Money discount() {
        return discount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Money itemTotal;
        private Money shipping;
        private Money handling;
        private Money taxTotal;
        private Money insurance;
        private Money shippingDiscount;
        private Money discount;

        /**
         * itemTotal
         */
        @JasonerProperty("item_total")
        public Builder itemTotal(Money value) {
            this.itemTotal = value;
            return this;
        }

        /**
         * shipping
         */
        
        public Builder shipping(Money value) {
            this.shipping = value;
            return this;
        }

        /**
         * handling
         */
        
        public Builder handling(Money value) {
            this.handling = value;
            return this;
        }

        /**
         * taxTotal
         */
        @JasonerProperty("tax_total")
        public Builder taxTotal(Money value) {
            this.taxTotal = value;
            return this;
        }

        /**
         * insurance
         */
        
        public Builder insurance(Money value) {
            this.insurance = value;
            return this;
        }

        /**
         * shippingDiscount
         */
        @JasonerProperty("shipping_discount")
        public Builder shippingDiscount(Money value) {
            this.shippingDiscount = value;
            return this;
        }

        /**
         * discount
         */
        
        public Builder discount(Money value) {
            this.discount = value;
            return this;
        }

        public AmountBreakdown build() {
            return new AmountBreakdown(this);
        }

    }

}

