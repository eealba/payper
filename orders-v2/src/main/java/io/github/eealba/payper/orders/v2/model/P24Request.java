package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;


/**
 * Information needed to pay using P24 (Przelewy24).
 */
public class P24Request {


    
    private final FullName name;
    
    private final EmailAddress email;
    @JasonerProperty("country_code")
    private final CountryCode countryCode;
    @JasonerProperty("experience_context")
    private final ExperienceContextBase experienceContext;

    private P24Request(Builder builder) {
        experienceContext = builder.experienceContext;
        name = Objects.requireNonNull(builder.name);
        email = Objects.requireNonNull(builder.email);
        countryCode = Objects.requireNonNull(builder.countryCode);
    }

    /**
     * name
     */
    
    public FullName name() {
        return name;
    }

    /**
     * email
     */
    
    public EmailAddress email() {
        return email;
    }

    /**
     * countryCode
     */
    @JasonerProperty("country_code")
    public CountryCode countryCode() {
        return countryCode;
    }

    /**
     * experienceContext
     */
    @JasonerProperty("experience_context")
    public ExperienceContextBase experienceContext() {
        return experienceContext;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private FullName name;
        private EmailAddress email;
        private CountryCode countryCode;
        private ExperienceContextBase experienceContext;

        /**
         * name
         */
        
        public Builder name(FullName value) {
            this.name = value;
            return this;
        }

        /**
         * email
         */
        
        public Builder email(EmailAddress value) {
            this.email = value;
            return this;
        }

        /**
         * countryCode
         */
        @JasonerProperty("country_code")
        public Builder countryCode(CountryCode value) {
            this.countryCode = value;
            return this;
        }

        /**
         * experienceContext
         */
        @JasonerProperty("experience_context")
        public Builder experienceContext(ExperienceContextBase value) {
            this.experienceContext = value;
            return this;
        }

        public P24Request build() {
            return new P24Request(this);
        }

    }


}

