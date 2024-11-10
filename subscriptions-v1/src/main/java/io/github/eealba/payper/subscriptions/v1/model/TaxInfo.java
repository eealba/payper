package io.github.eealba.payper.subscriptions.v1.model;


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
