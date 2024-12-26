package io.github.eealba.payper.orders.v2.model;



/**
 * 
 */
public record AltpayRecurringAttributesRequest(Object value) {

    public AltpayRecurringAttributesRequest(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        this.value = value;
    }

}
