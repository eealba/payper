package io.github.eealba.payper.webhooks.v1.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The verify webhook signature response.
 */
public record VerifyWebhookSignatureResponse(@JasonerProperty("verification_status") VerificationStatus verificationStatus) {

    public VerifyWebhookSignatureResponse(VerificationStatus verificationStatus) {
        if (verificationStatus == null) {
            throw new IllegalArgumentException("Field verificationStatus can`t be null");
        }
        this.verificationStatus = verificationStatus;
    }

    /**
     * The status of the signature verification.
     */
    public enum VerificationStatus {
        SUCCESS,
        FAILURE
    }}
