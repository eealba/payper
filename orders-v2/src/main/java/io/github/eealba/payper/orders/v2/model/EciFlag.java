package io.github.eealba.payper.orders.v2.model;
/**
 * Electronic Commerce Indicator (ECI). The ECI value is part of the 2 data elements that indicate the transaction was 
processed electronically. This should be passed on the authorization transaction to the Gateway/Processor.
 */
public enum EciFlag {
    MASTERCARD_NON_3D_SECURE_TRANSACTION,
    MASTERCARD_ATTEMPTED_AUTHENTICATION_TRANSACTION,
    MASTERCARD_FULLY_AUTHENTICATED_TRANSACTION,
    FULLY_AUTHENTICATED_TRANSACTION,
    ATTEMPTED_AUTHENTICATION_TRANSACTION,
    NON_3D_SECURE_TRANSACTION
}
