package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;


/**
 * Venmo wallet response.
 */
public class VenmoWalletResponse {


    @JasonerProperty("email_address")
    private final Email emailAddress;
    @JasonerProperty("account_id")
    private final AccountId accountId;
    @JasonerProperty("user_name")
    private final String userName;
    
    private final Name2 name;
    @JasonerProperty("phone_number")
    private final Phone2 phoneNumber;
    
    private final AddressPortable address;
    
    private final VenmoWalletAttributesResponse attributes;

    private VenmoWalletResponse(Builder builder) {
        emailAddress = builder.emailAddress;
        accountId = builder.accountId;
        userName = builder.userName;
        name = builder.name;
        phoneNumber = builder.phoneNumber;
        address = builder.address;
        attributes = builder.attributes;

    }

    /**
     * emailAddress
     */
    @JasonerProperty("email_address")
    public Email emailAddress() {
        return emailAddress;
    }

    /**
     * accountId
     */
    @JasonerProperty("account_id")
    public AccountId accountId() {
        return accountId;
    }

    /**
     * The Venmo user name chosen by the user, also know as a Venmo handle.
     */
    @JasonerProperty("user_name")
    public String userName() {
        return userName;
    }

    /**
     * name
     */
    
    public Name2 name() {
        return name;
    }

    /**
     * phoneNumber
     */
    @JasonerProperty("phone_number")
    public Phone2 phoneNumber() {
        return phoneNumber;
    }

    /**
     * address
     */
    
    public AddressPortable address() {
        return address;
    }

    /**
     * attributes
     */
    
    public VenmoWalletAttributesResponse attributes() {
        return attributes;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Email emailAddress;
        private AccountId accountId;
        private String userName;
        private Name2 name;
        private Phone2 phoneNumber;
        private AddressPortable address;
        private VenmoWalletAttributesResponse attributes;

        /**
         * emailAddress
         */
        @JasonerProperty("email_address")
        public Builder emailAddress(Email value) {
            this.emailAddress = value;
            return this;
        }

        /**
         * accountId
         */
        @JasonerProperty("account_id")
        public Builder accountId(AccountId value) {
            this.accountId = value;
            return this;
        }

        /**
         * The Venmo user name chosen by the user, also know as a Venmo handle.
         */
        @JasonerProperty("user_name")
        public Builder userName(String value) {
            this.userName = value;
            return this;
        }

        /**
         * name
         */
        
        public Builder name(Name2 value) {
            this.name = value;
            return this;
        }

        /**
         * phoneNumber
         */
        @JasonerProperty("phone_number")
        public Builder phoneNumber(Phone2 value) {
            this.phoneNumber = value;
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
         * attributes
         */
        
        public Builder attributes(VenmoWalletAttributesResponse value) {
            this.attributes = value;
            return this;
        }

        public VenmoWalletResponse build() {
            return new VenmoWalletResponse(this);
        }

    }


}

