package io.github.eealba.payper.webhooks.v1.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * Resends a webhook event notification, by ID.
 */
public class EventResend {


    @JasonerProperty("webhook_ids")
    private final List<String> webhookIds;

    private EventResend(Builder builder) {
        webhookIds = builder.webhookIds;
    }

    /**
     * An array of webhook account IDs.
     */
    @JasonerProperty("webhook_ids")
    public List<String> webhookIds() {
        return webhookIds;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private List<String> webhookIds;

        /**
         * An array of webhook account IDs.
         */
        @JasonerProperty("webhook_ids")
        public Builder webhookIds(List<String> value) {
            this.webhookIds = value;
            return this;
        }

        public EventResend build() {
            return new EventResend(this);
        }

    }

}

