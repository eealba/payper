package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * Information used to pay using eps.
 */
public class Eps {


    
    private final FullName name;
    @JasonerProperty("country_code")
    private final CountryCode countryCode;
    
    private final Bic bic;

    private Eps(Builder builder) {
        name = builder.name;
        countryCode = builder.countryCode;
        bic = builder.bic;

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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private FullName name;
        private CountryCode countryCode;
        private Bic bic;

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

        public Eps build() {
            return new Eps(this);
        }

    }

}

