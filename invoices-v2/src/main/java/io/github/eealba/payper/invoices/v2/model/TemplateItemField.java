package io.github.eealba.payper.invoices.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

/**
 * The field names for the invoice line items in the template.
 */
public enum TemplateItemField {
    @JasonerProperty("items.quantity")
    ITEMS_QUANTITY,
    @JasonerProperty("items.description")
    ITEMS_DESCRIPTION,
    @JasonerProperty("items.date")
    ITEMS_DATE,
    @JasonerProperty("items.discount")
    ITEMS_DISCOUNT,
    @JasonerProperty("items.tax")
    ITEMS_TAX
}
