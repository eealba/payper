package io.github.eealba.payper.subscriptions.v1.model;
/**
 * The details of the captured payment status.
 */
public enum CaptureStatusDetails {
    BUYER_COMPLAINT,
    CHARGEBACK,
    ECHECK,
    INTERNATIONAL_WITHDRAWAL,
    OTHER,
    PENDING_REVIEW,
    RECEIVING_PREFERENCE_MANDATES_MANUAL_ACTION,
    REFUNDED,
    TRANSACTION_APPROVED_AWAITING_FUNDING,
    UNILATERAL,
    VERIFICATION_REQUIRED
}
