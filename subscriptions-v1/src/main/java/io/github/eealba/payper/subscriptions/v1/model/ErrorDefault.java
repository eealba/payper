package io.github.eealba.payper.subscriptions.v1.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * The default error response.
 */
public class ErrorDefault {


    
    private final String name;
    
    private final String message;
    
    private final List<ErrorDetails> issues;
    @JasonerProperty("debug_id")
    private final String debugId;
    @JasonerProperty("information_link")
    private final String informationLink;

    private ErrorDefault(Builder builder) {
        name = builder.name;
        message = builder.message;
        issues = builder.issues;
        debugId = builder.debugId;
        informationLink = builder.informationLink;

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
     * issues
     */
    
    public List<ErrorDetails> issues() {
        return issues;
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String name;
        private String message;
        private List<ErrorDetails> issues;
        private String debugId;
        private String informationLink;

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
         * issues
         */
        
        public Builder issues(List<ErrorDetails> value) {
            this.issues = value;
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

        public ErrorDefault build() {
            return new ErrorDefault(this);
        }

    }

}

