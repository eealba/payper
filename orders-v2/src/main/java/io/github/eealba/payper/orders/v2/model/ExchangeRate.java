package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The exchange rate that determines the amount to convert from one currency to another currency.
 */
public class ExchangeRate {


    @JasonerProperty("source_currency")
    private final CurrencyCode sourceCurrency;
    @JasonerProperty("target_currency")
    private final CurrencyCode targetCurrency;
    
    private final String value;

    private ExchangeRate(Builder builder) {
        sourceCurrency = builder.sourceCurrency;
        targetCurrency = builder.targetCurrency;
        value = builder.value;

    }

    /**
     * sourceCurrency
     */
    @JasonerProperty("source_currency")
    public CurrencyCode sourceCurrency() {
        return sourceCurrency;
    }

    /**
     * targetCurrency
     */
    @JasonerProperty("target_currency")
    public CurrencyCode targetCurrency() {
        return targetCurrency;
    }

    /**
     * The target currency amount. Equivalent to one unit of the source currency. Formatted as integer or decimal 
value with one to 15 digits to the right of the decimal point.
     */
    
    public String value() {
        return value;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private CurrencyCode sourceCurrency;
        private CurrencyCode targetCurrency;
        private String value;

        /**
         * sourceCurrency
         */
        @JasonerProperty("source_currency")
        public Builder sourceCurrency(CurrencyCode value) {
            this.sourceCurrency = value;
            return this;
        }

        /**
         * targetCurrency
         */
        @JasonerProperty("target_currency")
        public Builder targetCurrency(CurrencyCode value) {
            this.targetCurrency = value;
            return this;
        }

        /**
         * The target currency amount. Equivalent to one unit of the source currency. Formatted as integer or decimal 
value with one to 15 digits to the right of the decimal point.
         */
        
        public Builder value(String value) {
            this.value = value;
            return this;
        }

        public ExchangeRate build() {
            return new ExchangeRate(this);
        }

    }

}

