package io.github.eealba.payper.payments.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;
import java.util.Objects;

/**
 * The detailed breakdown of the capture activity. This is not available for transactions that are in pending state.
 */
public class SellerReceivableBreakdown {


    @JasonerProperty("gross_amount")
    private final Money grossAmount;
    @JasonerProperty("paypal_fee")
    private final Money paypalFee;
    @JasonerProperty("paypal_fee_in_receivable_currency")
    private final Money paypalFeeInReceivableCurrency;
    @JasonerProperty("net_amount")
    private final Money netAmount;
    @JasonerProperty("receivable_amount")
    private final Money receivableAmount;
    @JasonerProperty("exchange_rate")
    private final ExchangeRate exchangeRate;
    @JasonerProperty("platform_fees")
    private final List<PlatformFee> platformFees;

    private SellerReceivableBreakdown(Builder builder) {
        paypalFee = builder.paypalFee;
        paypalFeeInReceivableCurrency = builder.paypalFeeInReceivableCurrency;
        netAmount = builder.netAmount;
        receivableAmount = builder.receivableAmount;
        exchangeRate = builder.exchangeRate;
        platformFees = builder.platformFees;
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
     * paypalFee
     */
    @JasonerProperty("paypal_fee")
    public Money paypalFee() {
        return paypalFee;
    }

    /**
     * paypalFeeInReceivableCurrency
     */
    @JasonerProperty("paypal_fee_in_receivable_currency")
    public Money paypalFeeInReceivableCurrency() {
        return paypalFeeInReceivableCurrency;
    }

    /**
     * netAmount
     */
    @JasonerProperty("net_amount")
    public Money netAmount() {
        return netAmount;
    }

    /**
     * receivableAmount
     */
    @JasonerProperty("receivable_amount")
    public Money receivableAmount() {
        return receivableAmount;
    }

    /**
     * exchangeRate
     */
    @JasonerProperty("exchange_rate")
    public ExchangeRate exchangeRate() {
        return exchangeRate;
    }

    /**
     * An array of platform or partner fees, commissions, or brokerage fees that associated with the captured 
payment.
     */
    @JasonerProperty("platform_fees")
    public List<PlatformFee> platformFees() {
        return platformFees;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Money grossAmount;
        private Money paypalFee;
        private Money paypalFeeInReceivableCurrency;
        private Money netAmount;
        private Money receivableAmount;
        private ExchangeRate exchangeRate;
        private List<PlatformFee> platformFees;

        /**
         * grossAmount
         */
        @JasonerProperty("gross_amount")
        public Builder grossAmount(Money value) {
            this.grossAmount = value;
            return this;
        }

        /**
         * paypalFee
         */
        @JasonerProperty("paypal_fee")
        public Builder paypalFee(Money value) {
            this.paypalFee = value;
            return this;
        }

        /**
         * paypalFeeInReceivableCurrency
         */
        @JasonerProperty("paypal_fee_in_receivable_currency")
        public Builder paypalFeeInReceivableCurrency(Money value) {
            this.paypalFeeInReceivableCurrency = value;
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

        /**
         * receivableAmount
         */
        @JasonerProperty("receivable_amount")
        public Builder receivableAmount(Money value) {
            this.receivableAmount = value;
            return this;
        }

        /**
         * exchangeRate
         */
        @JasonerProperty("exchange_rate")
        public Builder exchangeRate(ExchangeRate value) {
            this.exchangeRate = value;
            return this;
        }

        /**
         * An array of platform or partner fees, commissions, or brokerage fees that associated with the captured 
payment.
         */
        @JasonerProperty("platform_fees")
        public Builder platformFees(List<PlatformFee> value) {
            this.platformFees = value;
            return this;
        }

        public SellerReceivableBreakdown build() {
            return new SellerReceivableBreakdown(this);
        }

    }

}

