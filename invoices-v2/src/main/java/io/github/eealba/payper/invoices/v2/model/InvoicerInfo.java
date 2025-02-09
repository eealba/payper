package io.github.eealba.payper.invoices.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * The invoicer business information that appears on the invoice.
 */
public class InvoicerInfo {


    @JasonerProperty("business_name")
    private final String businessName;
    
    private final Name name;
    
    private final AddressPortable address;
    @JasonerProperty("email_address")
    private final Email emailAddress;
    
    private final List<PhoneDetail> phones;
    
    private final String website;
    @JasonerProperty("tax_id")
    private final String taxId;
    @JasonerProperty("additional_notes")
    private final String additionalNotes;
    @JasonerProperty("logo_url")
    private final String logoUrl;

    private InvoicerInfo(Builder builder) {
        businessName = builder.businessName;
        name = builder.name;
        address = builder.address;
        emailAddress = builder.emailAddress;
        phones = builder.phones;
        website = builder.website;
        taxId = builder.taxId;
        additionalNotes = builder.additionalNotes;
        logoUrl = builder.logoUrl;
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
     * An array of invoicer's phone numbers. The invoicer can choose to hide the phone number on the invoice.
     */
    
    public List<PhoneDetail> phones() {
        return phones;
    }

    /**
     * The invoicer's website.
     */
    
    public String website() {
        return website;
    }

    /**
     * The invoicer's tax ID.
     */
    @JasonerProperty("tax_id")
    public String taxId() {
        return taxId;
    }

    /**
     * Any additional information. Includes business hours.
     */
    @JasonerProperty("additional_notes")
    public String additionalNotes() {
        return additionalNotes;
    }

    /**
     * The full URL to an external logo image. The logo image must not be larger than 250 pixels wide by 90 pixels 
high.
     */
    @JasonerProperty("logo_url")
    public String logoUrl() {
        return logoUrl;
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
        private String website;
        private String taxId;
        private String additionalNotes;
        private String logoUrl;

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
         * An array of invoicer's phone numbers. The invoicer can choose to hide the phone number on the invoice.
         */
        
        public Builder phones(List<PhoneDetail> value) {
            this.phones = value;
            return this;
        }

        /**
         * The invoicer's website.
         */
        
        public Builder website(String value) {
            this.website = value;
            return this;
        }

        /**
         * The invoicer's tax ID.
         */
        @JasonerProperty("tax_id")
        public Builder taxId(String value) {
            this.taxId = value;
            return this;
        }

        /**
         * Any additional information. Includes business hours.
         */
        @JasonerProperty("additional_notes")
        public Builder additionalNotes(String value) {
            this.additionalNotes = value;
            return this;
        }

        /**
         * The full URL to an external logo image. The logo image must not be larger than 250 pixels wide by 90 pixels 
high.
         */
        @JasonerProperty("logo_url")
        public Builder logoUrl(String value) {
            this.logoUrl = value;
            return this;
        }

        public InvoicerInfo build() {
            return new InvoicerInfo(this);
        }

    }

}

