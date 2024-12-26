package io.github.eealba.payper.orders.v2.model;



/**
 * The full name representation like Mr J Smith.
 */
public record FullName(String value) {

    public FullName(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        this.value = value;
    }

}
