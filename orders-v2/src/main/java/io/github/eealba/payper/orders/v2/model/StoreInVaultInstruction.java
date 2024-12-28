package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerSingleVO;

/**
 * Defines how and when the payment source gets vaulted.
 */
@JasonerSingleVO
public record StoreInVaultInstruction(String value) {

    public StoreInVaultInstruction(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        if (!value.matches("^[0-9A-Z_]+$")) {
            throw new IllegalArgumentException("The value: " + value + " does not match the required pattern");
        }
        this.value = value;
    }

}
