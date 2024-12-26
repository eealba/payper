package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;


/**
 * Information needed to pay using Bancontact.
 */
public class BancontactRequest {


    
    private final FullName name;
    @JasonerProperty("country_code")
    private final CountryCode countryCode;
    @JasonerProperty("experience_context")
    private final ExperienceContextBase experienceContext;
    
    private final AltpayRecurringAttributesRequest attributes;

    private BancontactRequest(Builder builder) {
        experienceContext = builder.experienceContext;
        attributes = builder.attributes;
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

    /**
     * attributes
     */
    
    public AltpayRecurringAttributesRequest attributes() {
        return attributes;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private FullName name;
        private CountryCode countryCode;
        private ExperienceContextBase experienceContext;
        private AltpayRecurringAttributesRequest attributes;

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

        /**
         * attributes
         */
        
        public Builder attributes(AltpayRecurringAttributesRequest value) {
            this.attributes = value;
            return this;
        }

        public BancontactRequest build() {
            return new BancontactRequest(this);
        }

    }


}

