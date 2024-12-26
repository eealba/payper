package io.github.eealba.payper.orders.v2.model;



/**
 * The business identification code (BIC). In payments systems, a BIC is used to identify a specific business, most 
commonly a bank.
 */
public record Bic(String value) {

    public Bic(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        if (!value.matches("^[A-Z-a-z0-9]{4}[A-Z-a-z]{2}[A-Z-a-z0-9]{2}([A-Z-a-z0-9]{3})?$")) {
            throw new IllegalArgumentException("Invalid pattern for field value");
        }
        this.value = value;
    }

}
