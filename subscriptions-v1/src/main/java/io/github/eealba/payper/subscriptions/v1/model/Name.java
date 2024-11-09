package io.github.eealba.payper.subscriptions.v1.model;


import io.github.eealba.jasoner.JasonerProperty;

public class Name {

    
    private final String prefix;
    @JasonerProperty("given_name")
    private final String givenName;
    
    private final String surname;
    @JasonerProperty("middle_name")
    private final String middleName;
    
    private final String suffix;
    @JasonerProperty("alternate_full_name")
    private final String alternateFullName;
    @JasonerProperty("full_name")
    private final String fullName;

    private Name(Builder builder) {
        prefix = builder.prefix;
        givenName = builder.givenName;
        surname = builder.surname;
        middleName = builder.middleName;
        suffix = builder.suffix;
        alternateFullName = builder.alternateFullName;
        fullName = builder.fullName;

    }

    
    public String prefix() {
        return prefix;
    }

    @JasonerProperty("given_name")
    public String givenName() {
        return givenName;
    }

    
    public String surname() {
        return surname;
    }

    @JasonerProperty("middle_name")
    public String middleName() {
        return middleName;
    }

    
    public String suffix() {
        return suffix;
    }

    @JasonerProperty("alternate_full_name")
    public String alternateFullName() {
        return alternateFullName;
    }

    @JasonerProperty("full_name")
    public String fullName() {
        return fullName;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String prefix;
        private String givenName;
        private String surname;
        private String middleName;
        private String suffix;
        private String alternateFullName;
        private String fullName;

        
        public Builder prefix(String value) {
            this.prefix = value;
            return this;
        }

        @JasonerProperty("given_name")
        public Builder givenName(String value) {
            this.givenName = value;
            return this;
        }

        
        public Builder surname(String value) {
            this.surname = value;
            return this;
        }

        @JasonerProperty("middle_name")
        public Builder middleName(String value) {
            this.middleName = value;
            return this;
        }

        
        public Builder suffix(String value) {
            this.suffix = value;
            return this;
        }

        @JasonerProperty("alternate_full_name")
        public Builder alternateFullName(String value) {
            this.alternateFullName = value;
            return this;
        }

        @JasonerProperty("full_name")
        public Builder fullName(String value) {
            this.fullName = value;
            return this;
        }

        public Name build() {
            return new Name(this);
        }

    }

}

