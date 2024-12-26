package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;


/**
 * Information needed to pay using iDEAL.
 */
public class IdealRequest {


    
    private final FullName name;
    @JasonerProperty("country_code")
    private final CountryCode countryCode;
    
    private final Bic bic;
    @JasonerProperty("experience_context")
    private final ExperienceContextBase experienceContext;
    
    private final AltpayRecurringAttributesRequest attributes;

    private IdealRequest(Builder builder) {
        bic = builder.bic;
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
     * bic
     */
    
    public Bic bic() {
        return bic;
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
        private Bic bic;
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
         * bic
         */
        
        public Builder bic(Bic value) {
            this.bic = value;
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

        public IdealRequest build() {
            return new IdealRequest(this);
        }

    }


}

