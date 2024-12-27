package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * Information needed to pay using Trustly.
 */
public class Trustly {


    
    private final FullName name;
    @JasonerProperty("country_code")
    private final CountryCode countryCode;
    
    private final Bic bic;
    @JasonerProperty("iban_last_chars")
    private final IbanLastChars ibanLastChars;

    private Trustly(Builder builder) {
        name = builder.name;
        countryCode = builder.countryCode;
        bic = builder.bic;
        ibanLastChars = builder.ibanLastChars;

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
     * ibanLastChars
     */
    @JasonerProperty("iban_last_chars")
    public IbanLastChars ibanLastChars() {
        return ibanLastChars;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private FullName name;
        private CountryCode countryCode;
        private Bic bic;
        private IbanLastChars ibanLastChars;

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
         * ibanLastChars
         */
        @JasonerProperty("iban_last_chars")
        public Builder ibanLastChars(IbanLastChars value) {
            this.ibanLastChars = value;
            return this;
        }

        public Trustly build() {
            return new Trustly(this);
        }

    }

}

