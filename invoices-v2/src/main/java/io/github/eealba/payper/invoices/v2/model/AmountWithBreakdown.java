package io.github.eealba.payper.invoices.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The breakdown of the amount. Includes total item amount, total tax amount, custom amount, and shipping and discounts, if 
any.
 */
public class AmountWithBreakdown {


    @JasonerProperty("item_total")
    private final Money itemTotal;
    
    private final AggregatedDiscount discount;
    @JasonerProperty("tax_total")
    private final Money taxTotal;
    
    private final ShippingCost shipping;
    
    private final CustomAmount custom;

    private AmountWithBreakdown(Builder builder) {
        itemTotal = builder.itemTotal;
        discount = builder.discount;
        taxTotal = builder.taxTotal;
        shipping = builder.shipping;
        custom = builder.custom;

    }

    /**
     * itemTotal
     */
    @JasonerProperty("item_total")
    public Money itemTotal() {
        return itemTotal;
    }

    /**
     * discount
     */
    
    public AggregatedDiscount discount() {
        return discount;
    }

    /**
     * taxTotal
     */
    @JasonerProperty("tax_total")
    public Money taxTotal() {
        return taxTotal;
    }

    /**
     * shipping
     */
    
    public ShippingCost shipping() {
        return shipping;
    }

    /**
     * custom
     */
    
    public CustomAmount custom() {
        return custom;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Money itemTotal;
        private AggregatedDiscount discount;
        private Money taxTotal;
        private ShippingCost shipping;
        private CustomAmount custom;

        /**
         * itemTotal
         */
        @JasonerProperty("item_total")
        public Builder itemTotal(Money value) {
            this.itemTotal = value;
            return this;
        }

        /**
         * discount
         */
        
        public Builder discount(AggregatedDiscount value) {
            this.discount = value;
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
         * shipping
         */
        
        public Builder shipping(ShippingCost value) {
            this.shipping = value;
            return this;
        }

        /**
         * custom
         */
        
        public Builder custom(CustomAmount value) {
            this.custom = value;
            return this;
        }

        public AmountWithBreakdown build() {
            return new AmountWithBreakdown(this);
        }

    }

}

