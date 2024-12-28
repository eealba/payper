package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The net amount. Returned when the currency of the refund is different from the currency of the PayPal account where the 
merchant holds their funds.
 */
public class NetAmountBreakdownItem {


    @JasonerProperty("payable_amount")
    private final Money payableAmount;
    @JasonerProperty("converted_amount")
    private final Money convertedAmount;
    @JasonerProperty("exchange_rate")
    private final ExchangeRate exchangeRate;

    private NetAmountBreakdownItem(Builder builder) {
        payableAmount = builder.payableAmount;
        convertedAmount = builder.convertedAmount;
        exchangeRate = builder.exchangeRate;

    }

    /**
     * payableAmount
     */
    @JasonerProperty("payable_amount")
    public Money payableAmount() {
        return payableAmount;
    }

    /**
     * convertedAmount
     */
    @JasonerProperty("converted_amount")
    public Money convertedAmount() {
        return convertedAmount;
    }

    /**
     * exchangeRate
     */
    @JasonerProperty("exchange_rate")
    public ExchangeRate exchangeRate() {
        return exchangeRate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Money payableAmount;
        private Money convertedAmount;
        private ExchangeRate exchangeRate;

        /**
         * payableAmount
         */
        @JasonerProperty("payable_amount")
        public Builder payableAmount(Money value) {
            this.payableAmount = value;
            return this;
        }

        /**
         * convertedAmount
         */
        @JasonerProperty("converted_amount")
        public Builder convertedAmount(Money value) {
            this.convertedAmount = value;
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

        public NetAmountBreakdownItem build() {
            return new NetAmountBreakdownItem(this);
        }

    }

}

