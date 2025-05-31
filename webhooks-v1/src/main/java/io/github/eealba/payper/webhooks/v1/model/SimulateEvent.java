package io.github.eealba.payper.webhooks.v1.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * Simulates a mock webhook event.
 */
public class SimulateEvent {


    @JasonerProperty("webhook_id")
    private final String webhookId;
    
    private final String url;
    @JasonerProperty("event_type")
    private final String eventType;
    @JasonerProperty("resource_version")
    private final String resourceVersion;

    private SimulateEvent(Builder builder) {
        webhookId = builder.webhookId;
        url = builder.url;
        eventType = builder.eventType;
        resourceVersion = builder.resourceVersion;
    }

    /**
     * The ID of the webhook. If omitted, the URL is required.
     */
    @JasonerProperty("webhook_id")
    public String webhookId() {
        return webhookId;
    }

    /**
     * The URL for the webhook endpoint. If omitted, the webhook ID is required.
     */
    
    public String url() {
        return url;
    }

    /**
     * The event name. Specify one of the subscribed events. For each request, provide only one event.
     */
    @JasonerProperty("event_type")
    public String eventType() {
        return eventType;
    }

    /**
     * The identifier for event type ex: 1.0/2.0 etc.
     */
    @JasonerProperty("resource_version")
    public String resourceVersion() {
        return resourceVersion;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String webhookId;
        private String url;
        private String eventType;
        private String resourceVersion;

        /**
         * The ID of the webhook. If omitted, the URL is required.
         */
        @JasonerProperty("webhook_id")
        public Builder webhookId(String value) {
            this.webhookId = value;
            return this;
        }

        /**
         * The URL for the webhook endpoint. If omitted, the webhook ID is required.
         */
        
        public Builder url(String value) {
            this.url = value;
            return this;
        }

        /**
         * The event name. Specify one of the subscribed events. For each request, provide only one event.
         */
        @JasonerProperty("event_type")
        public Builder eventType(String value) {
            this.eventType = value;
            return this;
        }

        /**
         * The identifier for event type ex: 1.0/2.0 etc.
         */
        @JasonerProperty("resource_version")
        public Builder resourceVersion(String value) {
            this.resourceVersion = value;
            return this;
        }

        public SimulateEvent build() {
            return new SimulateEvent(this);
        }

    }

}

