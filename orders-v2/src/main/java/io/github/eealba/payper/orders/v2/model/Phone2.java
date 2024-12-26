package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;


/**
 * The phone number in its canonical international [E.164 numbering plan format](https://www.itu.int/rec/T-REC-E.164/en).
 */
public record Phone2(@JasonerProperty("national_number") String nationalNumber) {

    public Phone2(String nationalNumber) {
        if (nationalNumber == null) {
            throw new IllegalArgumentException("Field nationalNumber can`t be null");
        }
        if (!nationalNumber.matches("^[0-9]{1,14}?$")) {
            throw new IllegalArgumentException("Invalid pattern for field nationalNumber");
        }
        this.nationalNumber = nationalNumber;
    }

}
