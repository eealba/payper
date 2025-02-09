package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerSingleVO;

/**
 * The PayPal-generated ID for the vaulted payment source. This ID should be stored on the merchant's server so the saved 
payment source can be used for future transactions.
 */
@JasonerSingleVO
public record VaultId(String value) {

    public VaultId(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        if (!value.matches("^[0-9a-zA-Z_-]+$")) {
            throw new IllegalArgumentException("The value: " + value + " does not match the required pattern");
        }
        this.value = value;
    }

}
