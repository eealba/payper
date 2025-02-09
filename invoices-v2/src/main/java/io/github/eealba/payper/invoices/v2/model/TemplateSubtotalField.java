package io.github.eealba.payper.invoices.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

/**
 * The field names in the template for discount, shipping, and custom amounts.
 */
public enum TemplateSubtotalField {
    @JasonerProperty("discount")
    DISCOUNT,
    @JasonerProperty("shipping")
    SHIPPING,
    @JasonerProperty("custom")
    CUSTOM
}
