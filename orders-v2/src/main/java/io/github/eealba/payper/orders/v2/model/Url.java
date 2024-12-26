package io.github.eealba.payper.orders.v2.model;



/**
 * Describes the URL.
 */
public record Url(String value) {

    public Url(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        this.value = value;
    }

}
