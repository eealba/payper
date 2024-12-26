package io.github.eealba.payper.orders.v2.model;



/**
 * The account identifier for a PayPal account.
 */
public record AccountId(String value) {

    public AccountId(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        if (!value.matches("^[2-9A-HJ-NP-Z]{13}$")) {
            throw new IllegalArgumentException("Invalid pattern for field value");
        }
        this.value = value;
    }

}
