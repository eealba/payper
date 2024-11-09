package io.github.eealba.payper.subscriptions.v1.model;


import java.util.Objects;
import io.github.eealba.jasoner.JasonerProperty;

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

    @JasonerProperty("gross_amount")
    public Money grossAmount() {
        return grossAmount;
    }

    @JasonerProperty("total_item_amount")
    public Money totalItemAmount() {
        return totalItemAmount;
    }

    @JasonerProperty("fee_amount")
    public Money feeAmount() {
        return feeAmount;
    }

    @JasonerProperty("shipping_amount")
    public Money shippingAmount() {
        return shippingAmount;
    }

    @JasonerProperty("tax_amount")
    public Money taxAmount() {
        return taxAmount;
    }

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

        @JasonerProperty("gross_amount")
        public Builder grossAmount(Money value) {
            grossAmount = value;
            return this;
        }

        @JasonerProperty("total_item_amount")
        public Builder totalItemAmount(Money value) {
            totalItemAmount = value;
            return this;
        }

        @JasonerProperty("fee_amount")
        public Builder feeAmount(Money value) {
            feeAmount = value;
            return this;
        }

        @JasonerProperty("shipping_amount")
        public Builder shippingAmount(Money value) {
            shippingAmount = value;
            return this;
        }

        @JasonerProperty("tax_amount")
        public Builder taxAmount(Money value) {
            taxAmount = value;
            return this;
        }

        @JasonerProperty("net_amount")
        public Builder netAmount(Money value) {
            netAmount = value;
            return this;
        }

        public AmountWithBreakdown build() {
            return new AmountWithBreakdown(this);
        }

    }

}

