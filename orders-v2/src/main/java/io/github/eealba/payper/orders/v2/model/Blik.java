package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * Information used to pay using BLIK.
 */
public class Blik {


    
    private final FullName name;
    @JasonerProperty("country_code")
    private final CountryCode countryCode;
    
    private final Email email;
    @JasonerProperty("one_click")
    private final BlikOneClickResponse oneClick;

    private Blik(Builder builder) {
        name = builder.name;
        countryCode = builder.countryCode;
        email = builder.email;
        oneClick = builder.oneClick;

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
    
    public Email email() {
        return email;
    }

    /**
     * oneClick
     */
    @JasonerProperty("one_click")
    public BlikOneClickResponse oneClick() {
        return oneClick;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private FullName name;
        private CountryCode countryCode;
        private Email email;
        private BlikOneClickResponse oneClick;

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
        
        public Builder email(Email value) {
            this.email = value;
            return this;
        }

        /**
         * oneClick
         */
        @JasonerProperty("one_click")
        public Builder oneClick(BlikOneClickResponse value) {
            this.oneClick = value;
            return this;
        }

        public Blik build() {
            return new Blik(this);
        }

    }

}

