package io.github.eealba.payper.webhooks.v1.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * A list of webhook lookups.
 */
public class WebhookLookupList {


    @JasonerProperty("webhooks_lookups")
    private final List<WebhooksLookup> webhooksLookups;

    private WebhookLookupList(Builder builder) {
        webhooksLookups = builder.webhooksLookups;
    }

    /**
     * An array of webhook lookups.
     */
    @JasonerProperty("webhooks_lookups")
    public List<WebhooksLookup> webhooksLookups() {
        return webhooksLookups;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private List<WebhooksLookup> webhooksLookups;

        /**
         * An array of webhook lookups.
         */
        @JasonerProperty("webhooks_lookups")
        public Builder webhooksLookups(List<WebhooksLookup> value) {
            this.webhooksLookups = value;
            return this;
        }

        public WebhookLookupList build() {
            return new WebhookLookupList(this);
        }

    }

}

