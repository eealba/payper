package io.github.eealba.payper.subscriptions.v1.model;


import java.util.Objects;

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
        description = builder.description;
        issue = Objects.requireNonNull(builder.issue);
    }

    
    public String field() {
        return field;
    }

    
    public String value() {
        return value;
    }

    
    public String location() {
        return location;
    }

    
    public String issue() {
        return issue;
    }

    
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

        
        public Builder field(String value) {
            field = value;
            return this;
        }

        
        public Builder value(String value) {
            value = value;
            return this;
        }

        
        public Builder location(String value) {
            location = value;
            return this;
        }

        
        public Builder issue(String value) {
            issue = value;
            return this;
        }

        
        public Builder description(String value) {
            description = value;
            return this;
        }

        public ErrorDetails build() {
            return new ErrorDetails(this);
        }

    }

}

