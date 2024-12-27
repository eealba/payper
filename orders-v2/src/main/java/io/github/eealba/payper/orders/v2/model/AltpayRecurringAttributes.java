package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerSingleVO;

/**
 * 
 */
@JasonerSingleVO
public record AltpayRecurringAttributes(Object value) {

    public AltpayRecurringAttributes(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        this.value = value;
    }

}
