package io.github.eealba.payper.orders.v2.model;
/**
 * Indicates if this is a `first` or `subsequent` payment using a stored payment source (also referred to as stored 
credential or card on file).
 */
public enum StoredPaymentSourceUsageType {
    FIRST,
    SUBSEQUENT,
    DERIVED
}
