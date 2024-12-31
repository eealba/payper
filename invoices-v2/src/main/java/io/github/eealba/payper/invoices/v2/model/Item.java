package io.github.eealba.payper.invoices.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.time.LocalDate;
import java.util.Objects;

/**
 * An array of invoice line item information. The maximum items for an invoice is `100`.
 */
public class Item {


    
    private final String id;
    
    private final String name;
    
    private final String description;
    
    private final String quantity;
    @JasonerProperty("unit_amount")
    private final Money unitAmount;
    
    private final Tax tax;
    @JasonerProperty("item_date")
    private final LocalDate itemDate;
    
    private final Discount discount;
    @JasonerProperty("unit_of_measure")
    private final UnitOfMeasure unitOfMeasure;

    private Item(Builder builder) {
        id = builder.id;
        description = builder.description;
        tax = builder.tax;
        itemDate = builder.itemDate;
        discount = builder.discount;
        unitOfMeasure = builder.unitOfMeasure;
        name = Objects.requireNonNull(builder.name);
        quantity = Objects.requireNonNull(builder.quantity);
        unitAmount = Objects.requireNonNull(builder.unitAmount);
    }

    /**
     * The ID of the invoice line item.
     */
    
    public String id() {
        return id;
    }

    /**
     * The item name for the invoice line item.
     */
    
    public String name() {
        return name;
    }

    /**
     * The item description for the invoice line item.
     */
    
    public String description() {
        return description;
    }

    /**
     * The quantity of the item that the invoicer provides to the payer. Value is from `-1000000` to `1000000`. 
Supports up to five decimal places.
     */
    
    public String quantity() {
        return quantity;
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
    
    public Tax tax() {
        return tax;
    }

    /**
     * itemDate
     */
    @JasonerProperty("item_date")
    public LocalDate itemDate() {
        return itemDate;
    }

    /**
     * discount
     */
    
    public Discount discount() {
        return discount;
    }

    /**
     * unitOfMeasure
     */
    @JasonerProperty("unit_of_measure")
    public UnitOfMeasure unitOfMeasure() {
        return unitOfMeasure;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String id;
        private String name;
        private String description;
        private String quantity;
        private Money unitAmount;
        private Tax tax;
        private LocalDate itemDate;
        private Discount discount;
        private UnitOfMeasure unitOfMeasure;

        /**
         * The ID of the invoice line item.
         */
        
        public Builder id(String value) {
            this.id = value;
            return this;
        }

        /**
         * The item name for the invoice line item.
         */
        
        public Builder name(String value) {
            this.name = value;
            return this;
        }

        /**
         * The item description for the invoice line item.
         */
        
        public Builder description(String value) {
            this.description = value;
            return this;
        }

        /**
         * The quantity of the item that the invoicer provides to the payer. Value is from `-1000000` to `1000000`. 
Supports up to five decimal places.
         */
        
        public Builder quantity(String value) {
            this.quantity = value;
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
        
        public Builder tax(Tax value) {
            this.tax = value;
            return this;
        }

        /**
         * itemDate
         */
        @JasonerProperty("item_date")
        public Builder itemDate(LocalDate value) {
            this.itemDate = value;
            return this;
        }

        /**
         * discount
         */
        
        public Builder discount(Discount value) {
            this.discount = value;
            return this;
        }

        /**
         * unitOfMeasure
         */
        @JasonerProperty("unit_of_measure")
        public Builder unitOfMeasure(UnitOfMeasure value) {
            this.unitOfMeasure = value;
            return this;
        }

        public Item build() {
            return new Item(this);
        }

    }

}

