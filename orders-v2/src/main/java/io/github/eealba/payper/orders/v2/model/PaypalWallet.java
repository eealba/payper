package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;


/**
 * A resource that identifies a PayPal Wallet is used for payment.
 */
public class PaypalWallet {


    @JasonerProperty("vault_id")
    private final VaultId vaultId;
    @JasonerProperty("email_address")
    private final Email emailAddress;
    
    private final Name2 name;
    
    private final PhoneWithType phone;
    @JasonerProperty("birth_date")
    private final DateNoTime birthDate;
    @JasonerProperty("tax_info")
    private final TaxInfo taxInfo;
    
    private final AddressPortable address;
    
    private final PaypalWalletAttributes attributes;
    @JasonerProperty("experience_context")
    private final PaypalWalletExperienceContext experienceContext;
    @JasonerProperty("billing_agreement_id")
    private final BillingAgreementId billingAgreementId;

    private PaypalWallet(Builder builder) {
        vaultId = builder.vaultId;
        emailAddress = builder.emailAddress;
        name = builder.name;
        phone = builder.phone;
        birthDate = builder.birthDate;
        taxInfo = builder.taxInfo;
        address = builder.address;
        attributes = builder.attributes;
        experienceContext = builder.experienceContext;
        billingAgreementId = builder.billingAgreementId;

    }

    /**
     * vaultId
     */
    @JasonerProperty("vault_id")
    public VaultId vaultId() {
        return vaultId;
    }

    /**
     * emailAddress
     */
    @JasonerProperty("email_address")
    public Email emailAddress() {
        return emailAddress;
    }

    /**
     * name
     */
    
    public Name2 name() {
        return name;
    }

    /**
     * phone
     */
    
    public PhoneWithType phone() {
        return phone;
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
    
    public PaypalWalletAttributes attributes() {
        return attributes;
    }

    /**
     * experienceContext
     */
    @JasonerProperty("experience_context")
    public PaypalWalletExperienceContext experienceContext() {
        return experienceContext;
    }

    /**
     * billingAgreementId
     */
    @JasonerProperty("billing_agreement_id")
    public BillingAgreementId billingAgreementId() {
        return billingAgreementId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private VaultId vaultId;
        private Email emailAddress;
        private Name2 name;
        private PhoneWithType phone;
        private DateNoTime birthDate;
        private TaxInfo taxInfo;
        private AddressPortable address;
        private PaypalWalletAttributes attributes;
        private PaypalWalletExperienceContext experienceContext;
        private BillingAgreementId billingAgreementId;

        /**
         * vaultId
         */
        @JasonerProperty("vault_id")
        public Builder vaultId(VaultId value) {
            this.vaultId = value;
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
         * name
         */
        
        public Builder name(Name2 value) {
            this.name = value;
            return this;
        }

        /**
         * phone
         */
        
        public Builder phone(PhoneWithType value) {
            this.phone = value;
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
        
        public Builder attributes(PaypalWalletAttributes value) {
            this.attributes = value;
            return this;
        }

        /**
         * experienceContext
         */
        @JasonerProperty("experience_context")
        public Builder experienceContext(PaypalWalletExperienceContext value) {
            this.experienceContext = value;
            return this;
        }

        /**
         * billingAgreementId
         */
        @JasonerProperty("billing_agreement_id")
        public Builder billingAgreementId(BillingAgreementId value) {
            this.billingAgreementId = value;
            return this;
        }

        public PaypalWallet build() {
            return new PaypalWallet(this);
        }

    }


}

