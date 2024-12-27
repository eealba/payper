package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * Information used to pay using iDEAL.
 */
public class Ideal {


    
    private final FullName name;
    @JasonerProperty("country_code")
    private final CountryCode countryCode;
    
    private final Bic bic;
    @JasonerProperty("iban_last_chars")
    private final IbanLastChars ibanLastChars;
    
    private final AltpayRecurringAttributes attributes;

    private Ideal(Builder builder) {
        name = builder.name;
        countryCode = builder.countryCode;
        bic = builder.bic;
        ibanLastChars = builder.ibanLastChars;
        attributes = builder.attributes;

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

    /**
     * attributes
     */
    
    public AltpayRecurringAttributes attributes() {
        return attributes;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private FullName name;
        private CountryCode countryCode;
        private Bic bic;
        private IbanLastChars ibanLastChars;
        private AltpayRecurringAttributes attributes;

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

        /**
         * attributes
         */
        
        public Builder attributes(AltpayRecurringAttributes value) {
            this.attributes = value;
            return this;
        }

        public Ideal build() {
            return new Ideal(this);
        }

    }

}

