package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;

/**
 * The line items for this purchase. If your merchant account has been configured for Level 3 processing this field will be 
passed to the processor on your behalf.
 */
public class LineItem {


    
    private final String name;
    @JasonerProperty("unit_amount")
    private final Money unitAmount;
    
    private final Money tax;
    
    private final String quantity;
    
    private final String description;
    
    private final String sku;
    
    private final Category category;
    @JasonerProperty("commodity_code")
    private final String commodityCode;
    @JasonerProperty("discount_amount")
    private final Money discountAmount;
    @JasonerProperty("total_amount")
    private final Money totalAmount;
    @JasonerProperty("unit_of_measure")
    private final String unitOfMeasure;

    private LineItem(Builder builder) {
        tax = builder.tax;
        description = builder.description;
        sku = builder.sku;
        category = builder.category;
        commodityCode = builder.commodityCode;
        discountAmount = builder.discountAmount;
        totalAmount = builder.totalAmount;
        unitOfMeasure = builder.unitOfMeasure;
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

    /**
     * Code used to classify items purchased and track the total amount spent across various categories of products 
and services. Different corporate purchasing organizations may use different standards, but the United Nations 
Standard Products and Services Code (UNSPSC) is frequently used.
     */
    @JasonerProperty("commodity_code")
    public String commodityCode() {
        return commodityCode;
    }

    /**
     * discountAmount
     */
    @JasonerProperty("discount_amount")
    public Money discountAmount() {
        return discountAmount;
    }

    /**
     * totalAmount
     */
    @JasonerProperty("total_amount")
    public Money totalAmount() {
        return totalAmount;
    }

    /**
     * Unit of measure is a standard used to express the magnitude of a quantity in international trade. Most 
commonly used (but not limited to) examples are: Acre (ACR), Ampere (AMP), Centigram (CGM), Centimetre (CMT), 
Cubic inch (INQ), Cubic metre (MTQ), Fluid ounce (OZA), Foot (FOT), Hour (HUR), Item (ITM), Kilogram (KGM), 
Kilometre (KMT), Kilowatt (KWT), Liquid gallon (GLL), Liter (LTR), Pounds (LBS), Square foot (FTK).
     */
    @JasonerProperty("unit_of_measure")
    public String unitOfMeasure() {
        return unitOfMeasure;
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
        private String commodityCode;
        private Money discountAmount;
        private Money totalAmount;
        private String unitOfMeasure;

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

        /**
         * Code used to classify items purchased and track the total amount spent across various categories of products 
and services. Different corporate purchasing organizations may use different standards, but the United Nations 
Standard Products and Services Code (UNSPSC) is frequently used.
         */
        @JasonerProperty("commodity_code")
        public Builder commodityCode(String value) {
            this.commodityCode = value;
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
         * totalAmount
         */
        @JasonerProperty("total_amount")
        public Builder totalAmount(Money value) {
            this.totalAmount = value;
            return this;
        }

        /**
         * Unit of measure is a standard used to express the magnitude of a quantity in international trade. Most 
commonly used (but not limited to) examples are: Acre (ACR), Ampere (AMP), Centigram (CGM), Centimetre (CMT), 
Cubic inch (INQ), Cubic metre (MTQ), Fluid ounce (OZA), Foot (FOT), Hour (HUR), Item (ITM), Kilogram (KGM), 
Kilometre (KMT), Kilowatt (KWT), Liquid gallon (GLL), Liter (LTR), Pounds (LBS), Square foot (FTK).
         */
        @JasonerProperty("unit_of_measure")
        public Builder unitOfMeasure(String value) {
            this.unitOfMeasure = value;
            return this;
        }

        public LineItem build() {
            return new LineItem(this);
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

