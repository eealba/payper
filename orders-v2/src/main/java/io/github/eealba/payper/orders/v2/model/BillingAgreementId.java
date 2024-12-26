package io.github.eealba.payper.orders.v2.model;



/**
 * The PayPal billing agreement ID. References an approved recurring payment for goods or services.
 */
public record BillingAgreementId(String value) {

    public BillingAgreementId(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        if (!value.matches("^[a-zA-Z0-9-]+$")) {
            throw new IllegalArgumentException("Invalid pattern for field value");
        }
        this.value = value;
    }

}
