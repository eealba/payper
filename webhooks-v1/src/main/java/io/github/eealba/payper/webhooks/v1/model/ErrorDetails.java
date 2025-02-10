package io.github.eealba.payper.webhooks.v1.model;



/**
 * The error details. Required for client-side `4XX` errors.
 */
public class ErrorDetails {


    
    private final String field;
    
    private final String value;
    
    private final String location;
    
    private final String issue;
    
    private final String description;

    private ErrorDetails(Builder builder) {
        field = builder.field;
        value = builder.value;
        location = builder.location;
        issue = builder.issue;
        description = builder.description;
    }

    /**
     * The field that caused the error. If this field is in the body, set this value to the field's JSON pointer 
value. Required for client-side errors.
     */
    
    public String field() {
        return field;
    }

    /**
     * The value of the field that caused the error.
     */
    
    public String value() {
        return value;
    }

    /**
     * The location of the field that caused the error. Value is `body`, `path`, or `query`.
     */
    
    public String location() {
        return location;
    }

    /**
     * The unique, fine-grained application-level error code.
     */
    
    public String issue() {
        return issue;
    }

    /**
     * The human-readable description for an issue. The description can change over the lifetime of an API, so 
clients must not depend on this value.
     */
    
    public String description() {
        return description;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String field;
        private String value;
        private String location;
        private String issue;
        private String description;

        /**
         * The field that caused the error. If this field is in the body, set this value to the field's JSON pointer 
value. Required for client-side errors.
         */
        
        public Builder field(String value) {
            this.field = value;
            return this;
        }

        /**
         * The value of the field that caused the error.
         */
        
        public Builder value(String value) {
            this.value = value;
            return this;
        }

        /**
         * The location of the field that caused the error. Value is `body`, `path`, or `query`.
         */
        
        public Builder location(String value) {
            this.location = value;
            return this;
        }

        /**
         * The unique, fine-grained application-level error code.
         */
        
        public Builder issue(String value) {
            this.issue = value;
            return this;
        }

        /**
         * The human-readable description for an issue. The description can change over the lifetime of an API, so 
clients must not depend on this value.
         */
        
        public Builder description(String value) {
            this.description = value;
            return this;
        }

        public ErrorDetails build() {
            return new ErrorDetails(this);
        }

    }

}

