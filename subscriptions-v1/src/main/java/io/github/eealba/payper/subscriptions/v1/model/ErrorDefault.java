package io.github.eealba.payper.subscriptions.v1.model;

import java.util.List;
import io.github.eealba.jasoner.JasonerProperty;

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

    
    public String name() {
        return name;
    }

    
    public String message() {
        return message;
    }

    
    public List<ErrorDetails> issues() {
        return issues;
    }

    @JasonerProperty("debug_id")
    public String debugId() {
        return debugId;
    }

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

        
        public Builder name(String value) {
            name = value;
            return this;
        }

        
        public Builder message(String value) {
            message = value;
            return this;
        }

        
        public Builder issues(List<ErrorDetails> value) {
            issues = value;
            return this;
        }

        @JasonerProperty("debug_id")
        public Builder debugId(String value) {
            debugId = value;
            return this;
        }

        @JasonerProperty("information_link")
        public Builder informationLink(String value) {
            informationLink = value;
            return this;
        }

        public ErrorDefault build() {
            return new ErrorDefault(this);
        }

    }

}

