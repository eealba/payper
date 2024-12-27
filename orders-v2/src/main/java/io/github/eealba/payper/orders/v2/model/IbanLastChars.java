package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerSingleVO;

/**
 * The last characters of the IBAN used to pay.
 */
@JasonerSingleVO
public record IbanLastChars(String value) {

    public IbanLastChars(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        if (!value.matches("[a-zA-Z0-9]{4}")) {
            throw new IllegalArgumentException("The value: " + value + " does not match the required pattern");
        }
        this.value = value;
    }

}
