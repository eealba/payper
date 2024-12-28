package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;

/**
 * The details for the items to be purchased.
 */
public class Item {


    
    private final String name;
    @JasonerProperty("unit_amount")
    private final Money unitAmount;
    
    private final Money tax;
    
    private final String quantity;
    
    private final String description;
    
    private final String sku;
    
    private final Category category;

    private Item(Builder builder) {
        tax = builder.tax;
        description = builder.description;
        sku = builder.sku;
        category = builder.category;
        name = Objects.requireNonNull(builder.name);
        unitAmount = Objects.requireNonNull(builder.unitAmount);
        quantity = Objects.requireNonNull(builder.quantity);
    }

    /**
     * The item name or title.
     */
    
    public String name() {
        return name;
    }

    /**
     * unitAmount
     */
    @JasonerProperty("unit_amount")
    public Money unitAmount() {
        return unitAmount;
    }

    /**
     * tax
     */
    
    public Money tax() {
        return tax;
    }

    /**
     * The item quantity. Must be a whole number.
     */
    
    public String quantity() {
        return quantity;
    }

    /**
     * The detailed item description.
     */
    
    public String description() {
        return description;
    }

    /**
     * The stock keeping unit (SKU) for the item.
     */
    
    public String sku() {
        return sku;
    }

    /**
     * The item category type.
     */
    
    public Category category() {
        return category;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String name;
        private Money unitAmount;
        private Money tax;
        private String quantity;
        private String description;
        private String sku;
        private Category category;

        /**
         * The item name or title.
         */
        
        public Builder name(String value) {
            this.name = value;
            return this;
        }

        /**
         * unitAmount
         */
        @JasonerProperty("unit_amount")
        public Builder unitAmount(Money value) {
            this.unitAmount = value;
            return this;
        }

        /**
         * tax
         */
        
        public Builder tax(Money value) {
            this.tax = value;
            return this;
        }

        /**
         * The item quantity. Must be a whole number.
         */
        
        public Builder quantity(String value) {
            this.quantity = value;
            return this;
        }

        /**
         * The detailed item description.
         */
        
        public Builder description(String value) {
            this.description = value;
            return this;
        }

        /**
         * The stock keeping unit (SKU) for the item.
         */
        
        public Builder sku(String value) {
            this.sku = value;
            return this;
        }

        /**
         * The item category type.
         */
        
        public Builder category(Category value) {
            this.category = value;
            return this;
        }

        public Item build() {
            return new Item(this);
        }

    }
    /**
     * The item category type.
     */
    public enum Category {
        DIGITAL_GOODS,
        PHYSICAL_GOODS,
        DONATION
    }
}

