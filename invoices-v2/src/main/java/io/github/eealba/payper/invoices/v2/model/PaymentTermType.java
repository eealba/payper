package io.github.eealba.payper.invoices.v2.model;
/**
 * The payment term. Payment can be due upon receipt, a specified date, or in a set number of days.
 */
public enum PaymentTermType {
    DUE_ON_RECEIPT,
    DUE_ON_DATE_SPECIFIED,
    NET_10,
    NET_15,
    NET_30,
    NET_45,
    NET_60,
    NET_90,
    NO_DUE_DATE
}
