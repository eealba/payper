package io.github.eealba.payper.orders.v2.model;



/**
 * 
 */
public record GooglePayRequest(Object value) {

    public GooglePayRequest(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        this.value = value;
    }

}
