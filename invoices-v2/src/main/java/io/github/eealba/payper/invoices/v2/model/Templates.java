package io.github.eealba.payper.invoices.v2.model;

import java.util.List;

/**
 * An array of merchant-created templates with associated details that include the emails, addresses, and phone numbers 
from the user's PayPal profile.
 */
public class Templates {


    
    private final List<AddressPortable> addresses;
    
    private final Email emails;
    
    private final List<PhoneDetail> phones;
    
    private final List<Template> templates;
    
    private final List<LinkDescription> links;

    private Templates(Builder builder) {
        addresses = builder.addresses;
        emails = builder.emails;
        phones = builder.phones;
        templates = builder.templates;
        links = builder.links;

    }

    /**
     * An array of addresses in the user's PayPal profile.
     */
    
    public List<AddressPortable> addresses() {
        return addresses;
    }

    /**
     * emails
     */
    
    public Email emails() {
        return emails;
    }

    /**
     * An array of phone numbers in the user's PayPal profile.
     */
    
    public List<PhoneDetail> phones() {
        return phones;
    }

    /**
     * An array of details for each template. If `fields` is `none`, returns only the template name, ID, and default 
status.
     */
    
    public List<Template> templates() {
        return templates;
    }

    /**
     * An array of request-related [HATEOAS links](/docs/api/reference/api-responses/#hateoas-links).
     */
    
    public List<LinkDescription> links() {
        return links;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private List<AddressPortable> addresses;
        private Email emails;
        private List<PhoneDetail> phones;
        private List<Template> templates;
        private List<LinkDescription> links;

        /**
         * An array of addresses in the user's PayPal profile.
         */
        
        public Builder addresses(List<AddressPortable> value) {
            this.addresses = value;
            return this;
        }

        /**
         * emails
         */
        
        public Builder emails(Email value) {
            this.emails = value;
            return this;
        }

        /**
         * An array of phone numbers in the user's PayPal profile.
         */
        
        public Builder phones(List<PhoneDetail> value) {
            this.phones = value;
            return this;
        }

        /**
         * An array of details for each template. If `fields` is `none`, returns only the template name, ID, and default 
status.
         */
        
        public Builder templates(List<Template> value) {
            this.templates = value;
            return this;
        }

        /**
         * An array of request-related [HATEOAS links](/docs/api/reference/api-responses/#hateoas-links).
         */
        
        public Builder links(List<LinkDescription> value) {
            this.links = value;
            return this;
        }

        public Templates build() {
            return new Templates(this);
        }

    }

}

