package io.github.eealba.payper.invoices.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The discount. Can be an item- or invoice-level discount, or both. Can be applied as a percent or amount. If you provide 
both amount and percent, amount takes precedent.
 */
public class AggregatedDiscount {


    @JasonerProperty("invoice_discount")
    private final Discount invoiceDiscount;
    @JasonerProperty("item_discount")
    private final Money itemDiscount;

    private AggregatedDiscount(Builder builder) {
        invoiceDiscount = builder.invoiceDiscount;
        itemDiscount = builder.itemDiscount;

    }

    /**
     * invoiceDiscount
     */
    @JasonerProperty("invoice_discount")
    public Discount invoiceDiscount() {
        return invoiceDiscount;
    }

    /**
     * itemDiscount
     */
    @JasonerProperty("item_discount")
    public Money itemDiscount() {
        return itemDiscount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Discount invoiceDiscount;
        private Money itemDiscount;

        /**
         * invoiceDiscount
         */
        @JasonerProperty("invoice_discount")
        public Builder invoiceDiscount(Discount value) {
            this.invoiceDiscount = value;
            return this;
        }

        /**
         * itemDiscount
         */
        @JasonerProperty("item_discount")
        public Builder itemDiscount(Money value) {
            this.itemDiscount = value;
            return this;
        }

        public AggregatedDiscount build() {
            return new AggregatedDiscount(this);
        }

    }

}

