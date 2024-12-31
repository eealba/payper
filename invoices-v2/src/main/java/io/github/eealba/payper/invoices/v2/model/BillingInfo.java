package io.github.eealba.payper.invoices.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * The billing information of the invoice recipient. Includes name, address, email, phone, and language.
 */
public class BillingInfo {


    @JasonerProperty("business_name")
    private final String businessName;
    
    private final Name name;
    
    private final AddressPortable address;
    @JasonerProperty("email_address")
    private final Email emailAddress;
    
    private final List<PhoneDetail> phones;
    @JasonerProperty("additional_info")
    private final String additionalInfo;
    
    private final Language language;

    private BillingInfo(Builder builder) {
        businessName = builder.businessName;
        name = builder.name;
        address = builder.address;
        emailAddress = builder.emailAddress;
        phones = builder.phones;
        additionalInfo = builder.additionalInfo;
        language = builder.language;

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

    /**
     * emailAddress
     */
    @JasonerProperty("email_address")
    public Email emailAddress() {
        return emailAddress;
    }

    /**
     * The invoice recipient's phone numbers. Extension number is not supported.
     */
    
    public List<PhoneDetail> phones() {
        return phones;
    }

    /**
     * Any additional information about the recipient.
     */
    @JasonerProperty("additional_info")
    public String additionalInfo() {
        return additionalInfo;
    }

    /**
     * language
     */
    
    public Language language() {
        return language;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String businessName;
        private Name name;
        private AddressPortable address;
        private Email emailAddress;
        private List<PhoneDetail> phones;
        private String additionalInfo;
        private Language language;

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

        /**
         * emailAddress
         */
        @JasonerProperty("email_address")
        public Builder emailAddress(Email value) {
            this.emailAddress = value;
            return this;
        }

        /**
         * The invoice recipient's phone numbers. Extension number is not supported.
         */
        
        public Builder phones(List<PhoneDetail> value) {
            this.phones = value;
            return this;
        }

        /**
         * Any additional information about the recipient.
         */
        @JasonerProperty("additional_info")
        public Builder additionalInfo(String value) {
            this.additionalInfo = value;
            return this;
        }

        /**
         * language
         */
        
        public Builder language(Language value) {
            this.language = value;
            return this;
        }

        public BillingInfo build() {
            return new BillingInfo(this);
        }

    }

}

