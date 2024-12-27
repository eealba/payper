package io.github.eealba.payper.orders.v2.model;


import java.util.Objects;

/**
 * The options that the payee or merchant offers to the payer to ship or pick up their items.
 */
public class ShippingOption {


    
    private final String id;
    
    private final String label;
    
    private final ShippingType type;
    
    private final Money amount;
    
    private final Boolean selected;

    private ShippingOption(Builder builder) {
        type = builder.type;
        amount = builder.amount;
        id = Objects.requireNonNull(builder.id);
        label = Objects.requireNonNull(builder.label);
        selected = Objects.requireNonNull(builder.selected);
    }

    /**
     * A unique ID that identifies a payer-selected shipping option.
     */
    
    public String id() {
        return id;
    }

    /**
     * A description that the payer sees, which helps them choose an appropriate shipping option. For example, `Free 
Shipping`, `USPS Priority Shipping`, `Expédition prioritaire USPS`, or `USPS yōuxiān fā huò`. Localize this 
description to the payer's locale.
     */
    
    public String label() {
        return label;
    }

    /**
     * type
     */
    
    public ShippingType type() {
        return type;
    }

    /**
     * amount
     */
    
    public Money amount() {
        return amount;
    }

    /**
     * If the API request sets `selected = true`, it represents the shipping option that the payee or merchant 
expects to be pre-selected for the payer when they first view the `shipping.options` in the PayPal Checkout 
experience. As part of the response if a `shipping.option` contains `selected=true`, it represents the 
shipping option that the payer selected during the course of checkout with PayPal. Only one `shipping.option` 
can be set to `selected=true`.
     */
    
    public Boolean selected() {
        return selected;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String id;
        private String label;
        private ShippingType type;
        private Money amount;
        private Boolean selected;

        /**
         * A unique ID that identifies a payer-selected shipping option.
         */
        
        public Builder id(String value) {
            this.id = value;
            return this;
        }

        /**
         * A description that the payer sees, which helps them choose an appropriate shipping option. For example, `Free 
Shipping`, `USPS Priority Shipping`, `Expédition prioritaire USPS`, or `USPS yōuxiān fā huò`. Localize this 
description to the payer's locale.
         */
        
        public Builder label(String value) {
            this.label = value;
            return this;
        }

        /**
         * type
         */
        
        public Builder type(ShippingType value) {
            this.type = value;
            return this;
        }

        /**
         * amount
         */
        
        public Builder amount(Money value) {
            this.amount = value;
            return this;
        }

        /**
         * If the API request sets `selected = true`, it represents the shipping option that the payee or merchant 
expects to be pre-selected for the payer when they first view the `shipping.options` in the PayPal Checkout 
experience. As part of the response if a `shipping.option` contains `selected=true`, it represents the 
shipping option that the payer selected during the course of checkout with PayPal. Only one `shipping.option` 
can be set to `selected=true`.
         */
        
        public Builder selected(Boolean value) {
            this.selected = value;
            return this;
        }

        public ShippingOption build() {
            return new ShippingOption(this);
        }

    }

}

