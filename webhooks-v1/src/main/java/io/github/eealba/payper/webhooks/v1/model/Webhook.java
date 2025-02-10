package io.github.eealba.payper.webhooks.v1.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * One or more webhook objects.
 */
public class Webhook {


    
    private final String id;
    
    private final String url;
    @JasonerProperty("event_types")
    private final List<EventType> eventTypes;
    
    private final List<LinkDescription> links;

    private Webhook(Builder builder) {
        id = builder.id;
        url = builder.url;
        eventTypes = builder.eventTypes;
        links = builder.links;
    }

    /**
     * The ID of the webhook.
     */
    
    public String id() {
        return id;
    }

    /**
     * The URL that is configured to listen on `localhost` for incoming `POST` notification messages that contain 
event information.
     */
    
    public String url() {
        return url;
    }

    /**
     * An array of events to which to subscribe your webhook. To subscribe to all events, including events as they 
are added, specify the asterisk wild card. To replace the `event_types` array, specify the asterisk wild card. 
To list all supported events, <a href="#event-type_list">list available events</a>.
     */
    @JasonerProperty("event_types")
    public List<EventType> eventTypes() {
        return eventTypes;
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
        private String url;
        private List<EventType> eventTypes;
        private List<LinkDescription> links;

        /**
         * The ID of the webhook.
         */
        
        public Builder id(String value) {
            this.id = value;
            return this;
        }

        /**
         * The URL that is configured to listen on `localhost` for incoming `POST` notification messages that contain 
event information.
         */
        
        public Builder url(String value) {
            this.url = value;
            return this;
        }

        /**
         * An array of events to which to subscribe your webhook. To subscribe to all events, including events as they 
are added, specify the asterisk wild card. To replace the `event_types` array, specify the asterisk wild card. 
To list all supported events, <a href="#event-type_list">list available events</a>.
         */
        @JasonerProperty("event_types")
        public Builder eventTypes(List<EventType> value) {
            this.eventTypes = value;
            return this;
        }

        /**
         * An array of request-related [HATEOAS links](/docs/api/reference/api-responses/#hateoas-links/).
         */
        
        public Builder links(List<LinkDescription> value) {
            this.links = value;
            return this;
        }

        public Webhook build() {
            return new Webhook(this);
        }

    }

}

