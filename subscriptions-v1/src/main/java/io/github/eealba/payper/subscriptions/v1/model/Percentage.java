package io.github.eealba.payper.subscriptions.v1.model;



/**
 * The percentage, as a fixed-point, signed decimal number. For example, define a 19.99% interest rate as `19.99`.
 */
public record Percentage(String value) {

    public Percentage(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        if (!value.matches("^((-?[0-9]+)|(-?([0-9]+)?[.][0-9]+))$")) {
            throw new IllegalArgumentException("Invalid pattern for field value");
        }
        this.value = value;
    }

}
