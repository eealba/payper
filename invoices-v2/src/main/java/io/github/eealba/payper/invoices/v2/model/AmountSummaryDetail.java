package io.github.eealba.payper.invoices.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The invoice amount summary of item total, discount, tax total, and shipping.
 */
public class AmountSummaryDetail {


    @JasonerProperty("currency_code")
    private final CurrencyCode currencyCode;
    
    private final String value;
    
    private final AmountWithBreakdown breakdown;

    private AmountSummaryDetail(Builder builder) {
        currencyCode = builder.currencyCode;
        value = builder.value;
        breakdown = builder.breakdown;
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
Codes](/docs/integration/direct/rest/currency-codes/).
     */
    
    public String value() {
        return value;
    }

    /**
     * breakdown
     */
    
    public AmountWithBreakdown breakdown() {
        return breakdown;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private CurrencyCode currencyCode;
        private String value;
        private AmountWithBreakdown breakdown;

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
Codes](/docs/integration/direct/rest/currency-codes/).
         */
        
        public Builder value(String value) {
            this.value = value;
            return this;
        }

        /**
         * breakdown
         */
        
        public Builder breakdown(AmountWithBreakdown value) {
            this.breakdown = value;
            return this;
        }

        public AmountSummaryDetail build() {
            return new AmountSummaryDetail(this);
        }

    }

}

