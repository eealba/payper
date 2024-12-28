package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * Information used to pay using BLIK level_0 flow.
 */
public record BlikSeamless(@JasonerProperty("auth_code") String authCode) {

    public BlikSeamless(String authCode) {
        if (authCode == null) {
            throw new IllegalArgumentException("Field authCode can`t be null");
        }
        if (!authCode.matches("^[0-9]{6}$")) {
            throw new IllegalArgumentException("The value: " + authCode + " does not match the required pattern");
        }
        this.authCode = authCode;
    }

}
