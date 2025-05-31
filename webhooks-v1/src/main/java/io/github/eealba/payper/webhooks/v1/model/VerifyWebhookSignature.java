package io.github.eealba.payper.webhooks.v1.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * A verify webhook signature request.
 */
public record VerifyWebhookSignature(@JasonerProperty("auth_algo") String authAlgo, @JasonerProperty("cert_url") String certUrl, 
@JasonerProperty("transmission_id") String transmissionId, @JasonerProperty("transmission_sig") String transmissionSig, 
@JasonerProperty("transmission_time") String transmissionTime, @JasonerProperty("webhook_id") String webhookId, 
@JasonerProperty("webhook_event") Event webhookEvent) {

    public VerifyWebhookSignature(String authAlgo, String certUrl, String transmissionId, String transmissionSig, String transmissionTime, String 
webhookId, Event webhookEvent) {
        if (authAlgo == null) {
            throw new IllegalArgumentException("Field authAlgo can`t be null");
        }
        if (certUrl == null) {
            throw new IllegalArgumentException("Field certUrl can`t be null");
        }
        if (transmissionId == null) {
            throw new IllegalArgumentException("Field transmissionId can`t be null");
        }
        if (transmissionSig == null) {
            throw new IllegalArgumentException("Field transmissionSig can`t be null");
        }
        if (transmissionTime == null) {
            throw new IllegalArgumentException("Field transmissionTime can`t be null");
        }
        if (webhookId == null) {
            throw new IllegalArgumentException("Field webhookId can`t be null");
        }
        if (webhookEvent == null) {
            throw new IllegalArgumentException("Field webhookEvent can`t be null");
        }
        if (!authAlgo.matches("^[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException("The value: " + authAlgo + " does not match the required pattern");
        }
        if (!transmissionId.matches("^(?!\\d+$)\\w+\\S+")) {
            throw new IllegalArgumentException("The value: " + transmissionId + " does not match the required pattern");
        }
        if (!transmissionSig.matches("^(?!\\d+$)\\w+\\S+")) {
            throw new IllegalArgumentException("The value: " + transmissionSig + " does not match the required pattern");
        }
        if (!webhookId.matches("^[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException("The value: " + webhookId + " does not match the required pattern");
        }
        this.authAlgo = authAlgo;
        this.certUrl = certUrl;
        this.transmissionId = transmissionId;
        this.transmissionSig = transmissionSig;
        this.transmissionTime = transmissionTime;
        this.webhookId = webhookId;
        this.webhookEvent = webhookEvent;
    }

}
