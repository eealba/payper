package io.github.eealba.payper.orders.v2.model;
/**
 * The intent to either capture payment immediately or authorize a payment for an order after order creation.
 */
public enum CheckoutPaymentIntent {
    CAPTURE,
    AUTHORIZE
}
