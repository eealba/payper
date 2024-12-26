package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;


/**
 * The total order amount with an optional breakdown that provides details, such as the total item amount, total tax 
amount, shipping, handling, insurance, and discounts, if any.<br/>If you specify `amount.breakdown`, the amount equals 
`item_total` plus `tax_total` plus `shipping` plus `handling` plus `insurance` minus `shipping_discount` minus 
discount.<br/>The amount must be a positive number. For listed of supported currencies and decimal precision, see the 
PayPal REST APIs <a href="/docs/integration/direct/rest/currency-codes/">Currency Codes</a>.
 */
public class AmountWithBreakdown {


    @JasonerProperty("currency_code")
    private final CurrencyCode currencyCode;
    
    private final String value;
    
    private final AmountBreakdown breakdown;

    private AmountWithBreakdown(Builder builder) {
        breakdown = builder.breakdown;
        currencyCode = Objects.requireNonNull(builder.currencyCode);
        value = Objects.requireNonNull(builder.value);
    }

    /**
     * currencyCode
     */
    @JasonerProperty("currency_code")
    public CurrencyCode currencyCode() {
        return currencyCode;
    }

    /**
     * The value, which might be:<ul><li>An integer for currencies like `JPY` that are not typically 
fractional.</li><li>A decimal fraction for currencies like `TND` that are subdivided into 
thousandths.</li></ul>For the required number of decimal places for a currency code, see [Currency 
Codes](/api/rest/reference/currency-codes/).
     */
    
    public String value() {
        return value;
    }

    /**
     * breakdown
     */
    
    public AmountBreakdown breakdown() {
        return breakdown;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private CurrencyCode currencyCode;
        private String value;
        private AmountBreakdown breakdown;

        /**
         * currencyCode
         */
        @JasonerProperty("currency_code")
        public Builder currencyCode(CurrencyCode value) {
            this.currencyCode = value;
            return this;
        }

        /**
         * The value, which might be:<ul><li>An integer for currencies like `JPY` that are not typically 
fractional.</li><li>A decimal fraction for currencies like `TND` that are subdivided into 
thousandths.</li></ul>For the required number of decimal places for a currency code, see [Currency 
Codes](/api/rest/reference/currency-codes/).
         */
        
        public Builder value(String value) {
            this.value = value;
            return this;
        }

        /**
         * breakdown
         */
        
        public Builder breakdown(AmountBreakdown value) {
            this.breakdown = value;
            return this;
        }

        public AmountWithBreakdown build() {
            return new AmountWithBreakdown(this);
        }

    }


}

