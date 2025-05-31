package io.github.eealba.payper.webhooks.v1.model;

import io.github.eealba.jasoner.JasonerProperty;
import io.github.eealba.jasoner.JsonObject;

import java.util.List;

/**
 * A webhook event notification.
 */
public class Event {


    
    private final String id;
    @JasonerProperty("create_time")
    private final String createTime;
    @JasonerProperty("resource_type")
    private final String resourceType;
    @JasonerProperty("event_version")
    private final EventVersion eventVersion;
    @JasonerProperty("event_type")
    private final String eventType;
    
    private final String summary;
    @JasonerProperty("resource_version")
    private final ResourceVersion resourceVersion;
    
    private final JsonObject resource;
    
    private final List<LinkDescription> links;

    private Event(Builder builder) {
        id = builder.id;
        createTime = builder.createTime;
        resourceType = builder.resourceType;
        eventVersion = builder.eventVersion;
        eventType = builder.eventType;
        summary = builder.summary;
        resourceVersion = builder.resourceVersion;
        resource = builder.resource;
        links = builder.links;
    }

    /**
     * The ID of the webhook event notification.
     */
    
    public String id() {
        return id;
    }

    /**
     * The date and time when the webhook event notification was created, in [Internet date and time 
format](https://tools.ietf.org/html/rfc3339#section-5.6).
     */
    @JasonerProperty("create_time")
    public String createTime() {
        return createTime;
    }

    /**
     * The name of the resource related to the webhook notification event.
     */
    @JasonerProperty("resource_type")
    public String resourceType() {
        return resourceType;
    }

    /**
     * eventVersion
     */
    @JasonerProperty("event_version")
    public EventVersion eventVersion() {
        return eventVersion;
    }

    /**
     * The event that triggered the webhook event notification.
     */
    @JasonerProperty("event_type")
    public String eventType() {
        return eventType;
    }

    /**
     * A summary description for the event notification.
     */
    
    public String summary() {
        return summary;
    }

    /**
     * resourceVersion
     */
    @JasonerProperty("resource_version")
    public ResourceVersion resourceVersion() {
        return resourceVersion;
    }

    /**
     * The resource that triggered the webhook event notification.
     */
    
    public JsonObject resource() {
        return resource;
    }

    /**
     * An array of request-related [HATEOAS links](/docs/api/reference/api-responses/#hateoas-links).
     */
    
    public List<LinkDescription> links() {
        return links;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String id;
        private String createTime;
        private String resourceType;
        private EventVersion eventVersion;
        private String eventType;
        private String summary;
        private ResourceVersion resourceVersion;
        private JsonObject resource;
        private List<LinkDescription> links;

        /**
         * The ID of the webhook event notification.
         */
        
        public Builder id(String value) {
            this.id = value;
            return this;
        }

        /**
         * The date and time when the webhook event notification was created, in [Internet date and time 
format](https://tools.ietf.org/html/rfc3339#section-5.6).
         */
        @JasonerProperty("create_time")
        public Builder createTime(String value) {
            this.createTime = value;
            return this;
        }

        /**
         * The name of the resource related to the webhook notification event.
         */
        @JasonerProperty("resource_type")
        public Builder resourceType(String value) {
            this.resourceType = value;
            return this;
        }

        /**
         * eventVersion
         */
        @JasonerProperty("event_version")
        public Builder eventVersion(EventVersion value) {
            this.eventVersion = value;
            return this;
        }

        /**
         * The event that triggered the webhook event notification.
         */
        @JasonerProperty("event_type")
        public Builder eventType(String value) {
            this.eventType = value;
            return this;
        }

        /**
         * A summary description for the event notification.
         */
        
        public Builder summary(String value) {
            this.summary = value;
            return this;
        }

        /**
         * resourceVersion
         */
        @JasonerProperty("resource_version")
        public Builder resourceVersion(ResourceVersion value) {
            this.resourceVersion = value;
            return this;
        }

        /**
         * The resource that triggered the webhook event notification.
         */
        
        public Builder resource(JsonObject value) {
            this.resource = value;
            return this;
        }

        /**
         * An array of request-related [HATEOAS links](/docs/api/reference/api-responses/#hateoas-links).
         */
        
        public Builder links(List<LinkDescription> value) {
            this.links = value;
            return this;
        }

        public Event build() {
            return new Event(this);
        }

    }



}

