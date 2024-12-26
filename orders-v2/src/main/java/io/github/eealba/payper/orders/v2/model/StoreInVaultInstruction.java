package io.github.eealba.payper.orders.v2.model;



/**
 * Defines how and when the payment source gets vaulted.
 */
public record StoreInVaultInstruction(String value) {

    public StoreInVaultInstruction(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        if (!value.matches("^[0-9A-Z_]+$")) {
            throw new IllegalArgumentException("Invalid pattern for field value");
        }
        this.value = value;
    }

}
