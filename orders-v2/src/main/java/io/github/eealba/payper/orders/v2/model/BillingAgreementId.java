package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerSingleVO;

/**
 * The PayPal billing agreement ID. References an approved recurring payment for goods or services.
 */
@JasonerSingleVO
public record BillingAgreementId(String value) {

    public BillingAgreementId(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        if (!value.matches("^[a-zA-Z0-9-]+$")) {
            throw new IllegalArgumentException("The value: " + value + " does not match the required pattern");
        }
        this.value = value;
    }

}
