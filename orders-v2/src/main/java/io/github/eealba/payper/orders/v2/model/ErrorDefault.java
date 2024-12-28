package io.github.eealba.payper.orders.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * The default error response.
 */
public class ErrorDefault {


    
    private final String name;
    
    private final String message;
    
    private final List<ErrorDetails> details;
    @JasonerProperty("debug_id")
    private final String debugId;
    
    private final List<ErrorLinkDescription> links;

    private ErrorDefault(Builder builder) {
        name = builder.name;
        message = builder.message;
        details = builder.details;
        debugId = builder.debugId;
        links = builder.links;

    }

    /**
     * name
     */
    
    public String name() {
        return name;
    }

    /**
     * message
     */
    
    public String message() {
        return message;
    }

    /**
     * details
     */
    
    public List<ErrorDetails> details() {
        return details;
    }

    /**
     * The PayPal internal ID. Used for correlation purposes.
     */
    @JasonerProperty("debug_id")
    public String debugId() {
        return debugId;
    }

    /**
     * An array of request-related [HATEOAS links](https://en.wikipedia.org/wiki/HATEOAS).
     */
    
    public List<ErrorLinkDescription> links() {
        return links;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String name;
        private String message;
        private List<ErrorDetails> details;
        private String debugId;
        private List<ErrorLinkDescription> links;

        /**
         * name
         */
        
        public Builder name(String value) {
            this.name = value;
            return this;
        }

        /**
         * message
         */
        
        public Builder message(String value) {
            this.message = value;
            return this;
        }

        /**
         * details
         */
        
        public Builder details(List<ErrorDetails> value) {
            this.details = value;
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
         * An array of request-related [HATEOAS links](https://en.wikipedia.org/wiki/HATEOAS).
         */
        
        public Builder links(List<ErrorLinkDescription> value) {
            this.links = value;
            return this;
        }

        public ErrorDefault build() {
            return new ErrorDefault(this);
        }

    }

}

