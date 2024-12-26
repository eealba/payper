package io.github.eealba.payper.orders.v2.model;



/**
 * The last characters of the IBAN used to pay.
 */
public record IbanLastChars(String value) {

    public IbanLastChars(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        if (!value.matches("[a-zA-Z0-9]{4}")) {
            throw new IllegalArgumentException("Invalid pattern for field value");
        }
        this.value = value;
    }

}
