package io.github.eealba.payper.invoices.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The contact information of the user. Includes name and address.
 */
public class ContactNameAddress {


    @JasonerProperty("business_name")
    private final String businessName;
    
    private final Name name;
    
    private final AddressPortable address;

    private ContactNameAddress(Builder builder) {
        businessName = builder.businessName;
        name = builder.name;
        address = builder.address;
    }

    /**
     * Required. The business name of the party.
     */
    @JasonerProperty("business_name")
    public String businessName() {
        return businessName;
    }

    /**
     * name
     */
    
    public Name name() {
        return name;
    }

    /**
     * address
     */
    
    public AddressPortable address() {
        return address;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String businessName;
        private Name name;
        private AddressPortable address;

        /**
         * Required. The business name of the party.
         */
        @JasonerProperty("business_name")
        public Builder businessName(String value) {
            this.businessName = value;
            return this;
        }

        /**
         * name
         */
        
        public Builder name(Name value) {
            this.name = value;
            return this;
        }

        /**
         * address
         */
        
        public Builder address(AddressPortable value) {
            this.address = value;
            return this;
        }

        public ContactNameAddress build() {
            return new ContactNameAddress(this);
        }

    }

}

