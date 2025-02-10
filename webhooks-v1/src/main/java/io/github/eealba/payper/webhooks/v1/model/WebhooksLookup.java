package io.github.eealba.payper.webhooks.v1.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * The webhook lookup details.
 */
public class WebhooksLookup {


    
    private final String id;
    @JasonerProperty("client_id")
    private final String clientId;
    
    private final List<LinkDescription> links;

    private WebhooksLookup(Builder builder) {
        id = builder.id;
        clientId = builder.clientId;
        links = builder.links;
    }

    /**
     * The ID of the webhook lookup.
     */
    
    public String id() {
        return id;
    }

    /**
     * The application client ID.
     */
    @JasonerProperty("client_id")
    public String clientId() {
        return clientId;
    }

    /**
     * An array of request-related [HATEOAS links](/docs/api/reference/api-responses/#hateoas-links/).
     */
    
    public List<LinkDescription> links() {
        return links;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String id;
        private String clientId;
        private List<LinkDescription> links;

        /**
         * The ID of the webhook lookup.
         */
        
        public Builder id(String value) {
            this.id = value;
            return this;
        }

        /**
         * The application client ID.
         */
        @JasonerProperty("client_id")
        public Builder clientId(String value) {
            this.clientId = value;
            return this;
        }

        /**
         * An array of request-related [HATEOAS links](/docs/api/reference/api-responses/#hateoas-links/).
         */
        
        public Builder links(List<LinkDescription> value) {
            this.links = value;
            return this;
        }

        public WebhooksLookup build() {
            return new WebhooksLookup(this);
        }

    }

}

