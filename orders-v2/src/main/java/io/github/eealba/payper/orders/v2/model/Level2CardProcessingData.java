package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;


/**
 * The level 2 card processing data collections. If your merchant account has been configured for Level 2 processing this 
field will be passed to the processor on your behalf. Please contact your PayPal Technical Account Manager to define 
level 2 data for your business.
 */
public class Level2CardProcessingData {


    @JasonerProperty("invoice_id")
    private final String invoiceId;
    @JasonerProperty("tax_total")
    private final Money taxTotal;

    private Level2CardProcessingData(Builder builder) {
        invoiceId = builder.invoiceId;
        taxTotal = builder.taxTotal;

    }

    /**
     * Use this field to pass a purchase identification value of up to 12 ASCII characters for AIB and 17 ASCII 
characters for all other processors.
     */
    @JasonerProperty("invoice_id")
    public String invoiceId() {
        return invoiceId;
    }

    /**
     * taxTotal
     */
    @JasonerProperty("tax_total")
    public Money taxTotal() {
        return taxTotal;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String invoiceId;
        private Money taxTotal;

        /**
         * Use this field to pass a purchase identification value of up to 12 ASCII characters for AIB and 17 ASCII 
characters for all other processors.
         */
        @JasonerProperty("invoice_id")
        public Builder invoiceId(String value) {
            this.invoiceId = value;
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

        public Level2CardProcessingData build() {
            return new Level2CardProcessingData(this);
        }

    }


}

