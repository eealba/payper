package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;


/**
 * The tax ID of the customer. The customer is also known as the payer. Both `tax_id` and `tax_id_type` are required.
 */
public record TaxInfo(@JasonerProperty("tax_id") String taxId, @JasonerProperty("tax_id_type") TaxIdType taxIdType) {

    public TaxInfo(String taxId, TaxIdType taxIdType) {
        if (taxId == null) {
            throw new IllegalArgumentException("Field taxId can`t be null");
        }
        if (taxIdType == null) {
            throw new IllegalArgumentException("Field taxIdType can`t be null");
        }
        if (!taxId.matches("([a-zA-Z0-9])")) {
            throw new IllegalArgumentException("Invalid pattern for field taxId");
        }
        this.taxId = taxId;
        this.taxIdType = taxIdType;
    }

    /**
     * The customer's tax ID type.
     */
    public enum TaxIdType {
        BR_CPF,
        BR_CNPJ
    }}
