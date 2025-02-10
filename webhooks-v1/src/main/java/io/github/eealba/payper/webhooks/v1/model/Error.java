package io.github.eealba.payper.webhooks.v1.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * The error details.
 */
public class Error {


    
    private final String name;
    
    private final String message;
    @JasonerProperty("debug_id")
    private final String debugId;
    @JasonerProperty("information_link")
    private final String informationLink;
    
    private final List<ErrorDetails> details;
    
    private final List<LinkDescription> links;

    private Error(Builder builder) {
        name = builder.name;
        message = builder.message;
        debugId = builder.debugId;
        informationLink = builder.informationLink;
        details = builder.details;
        links = builder.links;
    }

    /**
     * The human-readable, unique name of the error.
     */
    
    public String name() {
        return name;
    }

    /**
     * The message that describes the error.
     */
    
    public String message() {
        return message;
    }

    /**
     * The PayPal internal ID. Used for correlation purposes.
     */
    @JasonerProperty("debug_id")
    public String debugId() {
        return debugId;
    }

    /**
     * The information link, or URI, that shows detailed information about this error for the developer.
     */
    @JasonerProperty("information_link")
    public String informationLink() {
        return informationLink;
    }

    /**
     * An array of additional details about the error.
     */
    
    public List<ErrorDetails> details() {
        return details;
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

        private String name;
        private String message;
        private String debugId;
        private String informationLink;
        private List<ErrorDetails> details;
        private List<LinkDescription> links;

        /**
         * The human-readable, unique name of the error.
         */
        
        public Builder name(String value) {
            this.name = value;
            return this;
        }

        /**
         * The message that describes the error.
         */
        
        public Builder message(String value) {
            this.message = value;
            return this;
        }

        /**
         * The PayPal internal ID. Used for correlation purposes.
         */
        @JasonerProperty("debug_id")
        public Builder debugId(String value) {
            this.debugId = value;
            return this;
        }

        /**
         * The information link, or URI, that shows detailed information about this error for the developer.
         */
        @JasonerProperty("information_link")
        public Builder informationLink(String value) {
            this.informationLink = value;
            return this;
        }

        /**
         * An array of additional details about the error.
         */
        
        public Builder details(List<ErrorDetails> value) {
            this.details = value;
            return this;
        }

        /**
         * An array of request-related [HATEOAS links](/docs/api/reference/api-responses/#hateoas-links).
         */
        
        public Builder links(List<LinkDescription> value) {
            this.links = value;
            return this;
        }

        public Error build() {
            return new Error(this);
        }

    }

}

