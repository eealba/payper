package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;


/**
 * The PayPal Wallet response.
 */
public class PaypalWalletResponse {


    @JasonerProperty("email_address")
    private final Email emailAddress;
    @JasonerProperty("account_id")
    private final AccountId accountId;
    @JasonerProperty("account_status")
    private final AccountStatus accountStatus;
    
    private final Name2 name;
    @JasonerProperty("phone_type")
    private final PhoneType2 phoneType;
    @JasonerProperty("phone_number")
    private final Phone2 phoneNumber;
    @JasonerProperty("birth_date")
    private final DateNoTime birthDate;
    @JasonerProperty("tax_info")
    private final TaxInfo taxInfo;
    
    private final AddressPortable address;
    
    private final PaypalWalletAttributesResponse attributes;

    private PaypalWalletResponse(Builder builder) {
        emailAddress = builder.emailAddress;
        accountId = builder.accountId;
        accountStatus = builder.accountStatus;
        name = builder.name;
        phoneType = builder.phoneType;
        phoneNumber = builder.phoneNumber;
        birthDate = builder.birthDate;
        taxInfo = builder.taxInfo;
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
     * The account status indicates whether the buyer has verified the financial details associated with their PayPal 
account.
     */
    @JasonerProperty("account_status")
    public AccountStatus accountStatus() {
        return accountStatus;
    }

    /**
     * name
     */
    
    public Name2 name() {
        return name;
    }

    /**
     * phoneType
     */
    @JasonerProperty("phone_type")
    public PhoneType2 phoneType() {
        return phoneType;
    }

    /**
     * phoneNumber
     */
    @JasonerProperty("phone_number")
    public Phone2 phoneNumber() {
        return phoneNumber;
    }

    /**
     * birthDate
     */
    @JasonerProperty("birth_date")
    public DateNoTime birthDate() {
        return birthDate;
    }

    /**
     * taxInfo
     */
    @JasonerProperty("tax_info")
    public TaxInfo taxInfo() {
        return taxInfo;
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
    
    public PaypalWalletAttributesResponse attributes() {
        return attributes;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Email emailAddress;
        private AccountId accountId;
        private AccountStatus accountStatus;
        private Name2 name;
        private PhoneType2 phoneType;
        private Phone2 phoneNumber;
        private DateNoTime birthDate;
        private TaxInfo taxInfo;
        private AddressPortable address;
        private PaypalWalletAttributesResponse attributes;

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
         * The account status indicates whether the buyer has verified the financial details associated with their PayPal 
account.
         */
        @JasonerProperty("account_status")
        public Builder accountStatus(AccountStatus value) {
            this.accountStatus = value;
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
         * phoneType
         */
        @JasonerProperty("phone_type")
        public Builder phoneType(PhoneType2 value) {
            this.phoneType = value;
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
         * birthDate
         */
        @JasonerProperty("birth_date")
        public Builder birthDate(DateNoTime value) {
            this.birthDate = value;
            return this;
        }

        /**
         * taxInfo
         */
        @JasonerProperty("tax_info")
        public Builder taxInfo(TaxInfo value) {
            this.taxInfo = value;
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
        
        public Builder attributes(PaypalWalletAttributesResponse value) {
            this.attributes = value;
            return this;
        }

        public PaypalWalletResponse build() {
            return new PaypalWalletResponse(this);
        }

    }
    /**
     * The account status indicates whether the buyer has verified the financial details associated with their PayPal account.
     */
    public enum AccountStatus {
        VERIFIED,
        UNVERIFIED
    }

}

