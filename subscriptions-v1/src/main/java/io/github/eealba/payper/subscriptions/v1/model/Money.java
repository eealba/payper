package io.github.eealba.payper.subscriptions.v1.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The currency and amount for a financial transaction, such as a balance or payment due.
 */
public record Money(@JasonerProperty("currency_code") CurrencyCode currencyCode, String value) {

    public Money(CurrencyCode currencyCode, String value) {
        if (currencyCode == null) {
            throw new IllegalArgumentException("Field currencyCode can`t be null");
        }
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        if (!value.matches("^((-?[0-9]+)|(-?([0-9]+)?[.][0-9]+))$")) {
            throw new IllegalArgumentException("Invalid pattern for field value");
        }
        this.currencyCode = currencyCode;
        this.value = value;
    }

}
