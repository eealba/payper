package io.github.eealba.payper.subscriptions.v1.model;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;

/**
 * The breakdown details for the amount. Includes the gross, tax, fee, and shipping amounts.
 */
public class AmountWithBreakdown {


    @JasonerProperty("gross_amount")
    private final Money grossAmount;
    @JasonerProperty("total_item_amount")
    private final Money totalItemAmount;
    @JasonerProperty("fee_amount")
    private final Money feeAmount;
    @JasonerProperty("shipping_amount")
    private final Money shippingAmount;
    @JasonerProperty("tax_amount")
    private final Money taxAmount;
    @JasonerProperty("net_amount")
    private final Money netAmount;

    private AmountWithBreakdown(Builder builder) {
        totalItemAmount = builder.totalItemAmount;
        feeAmount = builder.feeAmount;
        shippingAmount = builder.shippingAmount;
        taxAmount = builder.taxAmount;
        netAmount = builder.netAmount;
        grossAmount = Objects.requireNonNull(builder.grossAmount);
    }

    /**
     * grossAmount
     */
    @JasonerProperty("gross_amount")
    public Money grossAmount() {
        return grossAmount;
    }

    /**
     * totalItemAmount
     */
    @JasonerProperty("total_item_amount")
    public Money totalItemAmount() {
        return totalItemAmount;
    }

    /**
     * feeAmount
     */
    @JasonerProperty("fee_amount")
    public Money feeAmount() {
        return feeAmount;
    }

    /**
     * shippingAmount
     */
    @JasonerProperty("shipping_amount")
    public Money shippingAmount() {
        return shippingAmount;
    }

    /**
     * taxAmount
     */
    @JasonerProperty("tax_amount")
    public Money taxAmount() {
        return taxAmount;
    }

    /**
     * netAmount
     */
    @JasonerProperty("net_amount")
    public Money netAmount() {
        return netAmount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Money grossAmount;
        private Money totalItemAmount;
        private Money feeAmount;
        private Money shippingAmount;
        private Money taxAmount;
        private Money netAmount;

        /**
         * grossAmount
         */
        @JasonerProperty("gross_amount")
        public Builder grossAmount(Money value) {
            this.grossAmount = value;
            return this;
        }

        /**
         * totalItemAmount
         */
        @JasonerProperty("total_item_amount")
        public Builder totalItemAmount(Money value) {
            this.totalItemAmount = value;
            return this;
        }

        /**
         * feeAmount
         */
        @JasonerProperty("fee_amount")
        public Builder feeAmount(Money value) {
            this.feeAmount = value;
            return this;
        }

        /**
         * shippingAmount
         */
        @JasonerProperty("shipping_amount")
        public Builder shippingAmount(Money value) {
            this.shippingAmount = value;
            return this;
        }

        /**
         * taxAmount
         */
        @JasonerProperty("tax_amount")
        public Builder taxAmount(Money value) {
            this.taxAmount = value;
            return this;
        }

        /**
         * netAmount
         */
        @JasonerProperty("net_amount")
        public Builder netAmount(Money value) {
            this.netAmount = value;
            return this;
        }

        public AmountWithBreakdown build() {
            return new AmountWithBreakdown(this);
        }

    }

}

