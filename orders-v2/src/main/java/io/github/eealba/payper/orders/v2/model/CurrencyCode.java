package io.github.eealba.payper.orders.v2.model;



/**
 * The [3-character ISO-4217 currency code](/api/rest/reference/currency-codes/) that identifies the currency.
 */
public record CurrencyCode(String value) {

    public CurrencyCode(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        this.value = value;
    }

}
