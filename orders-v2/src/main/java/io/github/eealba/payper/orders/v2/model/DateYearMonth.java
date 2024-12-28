package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerSingleVO;

/**
 * The year and month, in ISO-8601 `YYYY-MM` date format. See [Internet date and time 
format](https://tools.ietf.org/html/rfc3339#section-5.6).
 */
@JasonerSingleVO
public record DateYearMonth(String value) {

    public DateYearMonth(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        if (!value.matches("^[0-9]{4}-(0[1-9]|1[0-2])$")) {
            throw new IllegalArgumentException("The value: " + value + " does not match the required pattern");
        }
        this.value = value;
    }

}
