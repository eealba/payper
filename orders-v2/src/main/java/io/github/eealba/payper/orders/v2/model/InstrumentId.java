package io.github.eealba.payper.orders.v2.model;



/**
 * The identifier of the instrument.
 */
public record InstrumentId(String value) {

    public InstrumentId(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        if (!value.matches("^[A-Za-z0-9-_.+=]+$")) {
            throw new IllegalArgumentException("Invalid pattern for field value");
        }
        this.value = value;
    }

}
