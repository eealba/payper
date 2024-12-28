package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;

/**
 * Information needed to pay using eps.
 */
public class EpsRequest {


    
    private final FullName name;
    @JasonerProperty("country_code")
    private final CountryCode countryCode;
    @JasonerProperty("experience_context")
    private final ExperienceContextBase experienceContext;

    private EpsRequest(Builder builder) {
        experienceContext = builder.experienceContext;
        name = Objects.requireNonNull(builder.name);
        countryCode = Objects.requireNonNull(builder.countryCode);
    }

    /**
     * name
     */
    
    public FullName name() {
        return name;
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

        public EpsRequest build() {
            return new EpsRequest(this);
        }

    }

}

