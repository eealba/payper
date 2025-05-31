package io.github.eealba.payper.webhooks.v1.model;


import io.github.eealba.jasoner.JasonerSingleVO;

/**
 * The event version in the webhook notification.
 */
@JasonerSingleVO
public record EventVersion(String value) {

    public EventVersion(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        if (!value.matches("^([0-9]+.[0-9]+)$")) {
            throw new IllegalArgumentException("The value: " + value + " does not match the required pattern");
        }
        this.value = value;
    }

}
