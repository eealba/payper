package io.github.eealba.payper.webhooks.v1.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * An event type.
 */
public class EventType {


    
    private final String name;
    
    private final String description;
    
    private final String status;
    @JasonerProperty("resource_versions")
    private final List<String> resourceVersions;

    private EventType(Builder builder) {
        name = builder.name;
        description = builder.description;
        status = builder.status;
        resourceVersions = builder.resourceVersions;
    }

    /**
     * The unique event name.<blockquote><strong>Note:</strong> To subscribe to all events, including events as they 
are added, specify an `*` as the value to represent a wildcard.</blockquote>
     */
    
    public String name() {
        return name;
    }

    /**
     * A human-readable description of the event.
     */
    
    public String description() {
        return description;
    }

    /**
     * The status of a webhook event.
     */
    
    public String status() {
        return status;
    }

    /**
     * Identifier for the event type example: 1.0/2.0 etc.
     */
    @JasonerProperty("resource_versions")
    public List<String> resourceVersions() {
        return resourceVersions;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String name;
        private String description;
        private String status;
        private List<String> resourceVersions;

        /**
         * The unique event name.<blockquote><strong>Note:</strong> To subscribe to all events, including events as they 
are added, specify an `*` as the value to represent a wildcard.</blockquote>
         */
        
        public Builder name(String value) {
            this.name = value;
            return this;
        }

        /**
         * A human-readable description of the event.
         */
        
        public Builder description(String value) {
            this.description = value;
            return this;
        }

        /**
         * The status of a webhook event.
         */
        
        public Builder status(String value) {
            this.status = value;
            return this;
        }

        /**
         * Identifier for the event type example: 1.0/2.0 etc.
         */
        @JasonerProperty("resource_versions")
        public Builder resourceVersions(List<String> value) {
            this.resourceVersions = value;
            return this;
        }

        public EventType build() {
            return new EventType(this);
        }

    }

}

