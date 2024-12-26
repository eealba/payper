package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;


/**
 * Information needed to pay using BLIK.
 */
public class BlikRequest {


    
    private final FullName name;
    @JasonerProperty("country_code")
    private final CountryCode countryCode;
    
    private final EmailAddress email;
    @JasonerProperty("experience_context")
    private final BlikExperienceContext experienceContext;
    @JasonerProperty("level_0")
    private final BlikSeamless level0;
    @JasonerProperty("one_click")
    private final BlikOneClick oneClick;

    private BlikRequest(Builder builder) {
        email = builder.email;
        experienceContext = builder.experienceContext;
        level0 = builder.level0;
        oneClick = builder.oneClick;
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
     * email
     */
    
    public EmailAddress email() {
        return email;
    }

    /**
     * experienceContext
     */
    @JasonerProperty("experience_context")
    public BlikExperienceContext experienceContext() {
        return experienceContext;
    }

    /**
     * level0
     */
    @JasonerProperty("level_0")
    public BlikSeamless level0() {
        return level0;
    }

    /**
     * oneClick
     */
    @JasonerProperty("one_click")
    public BlikOneClick oneClick() {
        return oneClick;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private FullName name;
        private CountryCode countryCode;
        private EmailAddress email;
        private BlikExperienceContext experienceContext;
        private BlikSeamless level0;
        private BlikOneClick oneClick;

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
         * email
         */
        
        public Builder email(EmailAddress value) {
            this.email = value;
            return this;
        }

        /**
         * experienceContext
         */
        @JasonerProperty("experience_context")
        public Builder experienceContext(BlikExperienceContext value) {
            this.experienceContext = value;
            return this;
        }

        /**
         * level0
         */
        @JasonerProperty("level_0")
        public Builder level0(BlikSeamless value) {
            this.level0 = value;
            return this;
        }

        /**
         * oneClick
         */
        @JasonerProperty("one_click")
        public Builder oneClick(BlikOneClick value) {
            this.oneClick = value;
            return this;
        }

        public BlikRequest build() {
            return new BlikRequest(this);
        }

    }


}

