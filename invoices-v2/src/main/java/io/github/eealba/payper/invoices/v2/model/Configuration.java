package io.github.eealba.payper.invoices.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The invoice configuration details. Includes partial payment, tip, and tax calculated after discount.
 */
public class Configuration {


    @JasonerProperty("tax_calculated_after_discount")
    private final Boolean taxCalculatedAfterDiscount;
    @JasonerProperty("tax_inclusive")
    private final Boolean taxInclusive;
    @JasonerProperty("allow_tip")
    private final Boolean allowTip;
    @JasonerProperty("partial_payment")
    private final PartialPayment partialPayment;
    @JasonerProperty("template_id")
    private final String templateId;

    private Configuration(Builder builder) {
        taxCalculatedAfterDiscount = builder.taxCalculatedAfterDiscount;
        taxInclusive = builder.taxInclusive;
        allowTip = builder.allowTip;
        partialPayment = builder.partialPayment;
        templateId = builder.templateId;
    }

    /**
     * Indicates whether the tax is calculated before or after a discount. If `false`, the tax is calculated before a 
discount. If `true`, the tax is calculated after a discount.
     */
    @JasonerProperty("tax_calculated_after_discount")
    public Boolean taxCalculatedAfterDiscount() {
        return taxCalculatedAfterDiscount;
    }

    /**
     * Indicates whether the unit price includes tax.
     */
    @JasonerProperty("tax_inclusive")
    public Boolean taxInclusive() {
        return taxInclusive;
    }

    /**
     * Indicates whether the invoice enables the customer to enter a tip amount during payment. If `true`, the 
invoice shows a tip amount field so that the customer can enter a tip amount. If `false`, the invoice does not 
show a tip amount field.<blockquote><strong>Note:</strong> This feature is not available for users in `Hong 
Kong`, `Taiwan`, `India`, or `Japan`.</blockquote>
     */
    @JasonerProperty("allow_tip")
    public Boolean allowTip() {
        return allowTip;
    }

    /**
     * partialPayment
     */
    @JasonerProperty("partial_payment")
    public PartialPayment partialPayment() {
        return partialPayment;
    }

    /**
     * The template ID. The template determines the layout of the invoice. Includes which fields to show and hide.
     */
    @JasonerProperty("template_id")
    public String templateId() {
        return templateId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Boolean taxCalculatedAfterDiscount;
        private Boolean taxInclusive;
        private Boolean allowTip;
        private PartialPayment partialPayment;
        private String templateId;

        /**
         * Indicates whether the tax is calculated before or after a discount. If `false`, the tax is calculated before a 
discount. If `true`, the tax is calculated after a discount.
         */
        @JasonerProperty("tax_calculated_after_discount")
        public Builder taxCalculatedAfterDiscount(Boolean value) {
            this.taxCalculatedAfterDiscount = value;
            return this;
        }

        /**
         * Indicates whether the unit price includes tax.
         */
        @JasonerProperty("tax_inclusive")
        public Builder taxInclusive(Boolean value) {
            this.taxInclusive = value;
            return this;
        }

        /**
         * Indicates whether the invoice enables the customer to enter a tip amount during payment. If `true`, the 
invoice shows a tip amount field so that the customer can enter a tip amount. If `false`, the invoice does not 
show a tip amount field.<blockquote><strong>Note:</strong> This feature is not available for users in `Hong 
Kong`, `Taiwan`, `India`, or `Japan`.</blockquote>
         */
        @JasonerProperty("allow_tip")
        public Builder allowTip(Boolean value) {
            this.allowTip = value;
            return this;
        }

        /**
         * partialPayment
         */
        @JasonerProperty("partial_payment")
        public Builder partialPayment(PartialPayment value) {
            this.partialPayment = value;
            return this;
        }

        /**
         * The template ID. The template determines the layout of the invoice. Includes which fields to show and hide.
         */
        @JasonerProperty("template_id")
        public Builder templateId(String value) {
            this.templateId = value;
            return this;
        }

        public Configuration build() {
            return new Configuration(this);
        }

    }

}

