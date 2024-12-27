package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * Information needed to pay using ApplePay.
 */
public class ApplePayRequest {


    
    private final String id;
    
    private final FullName name;
    @JasonerProperty("email_address")
    private final Email emailAddress;
    @JasonerProperty("phone_number")
    private final Phone phoneNumber;
    @JasonerProperty("decrypted_token")
    private final ApplePayDecryptedTokenData decryptedToken;
    @JasonerProperty("stored_credential")
    private final CardStoredCredential storedCredential;
    @JasonerProperty("vault_id")
    private final VaultId vaultId;
    
    private final ApplePayAttributes attributes;

    private ApplePayRequest(Builder builder) {
        id = builder.id;
        name = builder.name;
        emailAddress = builder.emailAddress;
        phoneNumber = builder.phoneNumber;
        decryptedToken = builder.decryptedToken;
        storedCredential = builder.storedCredential;
        vaultId = builder.vaultId;
        attributes = builder.attributes;

    }

    /**
     * ApplePay transaction identifier, this will be the unique identifier for this transaction provided by Apple. 
The pattern is defined by an external party and supports Unicode.
     */
    
    public String id() {
        return id;
    }

    /**
     * name
     */
    
    public FullName name() {
        return name;
    }

    /**
     * emailAddress
     */
    @JasonerProperty("email_address")
    public Email emailAddress() {
        return emailAddress;
    }

    /**
     * phoneNumber
     */
    @JasonerProperty("phone_number")
    public Phone phoneNumber() {
        return phoneNumber;
    }

    /**
     * decryptedToken
     */
    @JasonerProperty("decrypted_token")
    public ApplePayDecryptedTokenData decryptedToken() {
        return decryptedToken;
    }

    /**
     * storedCredential
     */
    @JasonerProperty("stored_credential")
    public CardStoredCredential storedCredential() {
        return storedCredential;
    }

    /**
     * vaultId
     */
    @JasonerProperty("vault_id")
    public VaultId vaultId() {
        return vaultId;
    }

    /**
     * attributes
     */
    
    public ApplePayAttributes attributes() {
        return attributes;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String id;
        private FullName name;
        private Email emailAddress;
        private Phone phoneNumber;
        private ApplePayDecryptedTokenData decryptedToken;
        private CardStoredCredential storedCredential;
        private VaultId vaultId;
        private ApplePayAttributes attributes;

        /**
         * ApplePay transaction identifier, this will be the unique identifier for this transaction provided by Apple. 
The pattern is defined by an external party and supports Unicode.
         */
        
        public Builder id(String value) {
            this.id = value;
            return this;
        }

        /**
         * name
         */
        
        public Builder name(FullName value) {
            this.name = value;
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
         * phoneNumber
         */
        @JasonerProperty("phone_number")
        public Builder phoneNumber(Phone value) {
            this.phoneNumber = value;
            return this;
        }

        /**
         * decryptedToken
         */
        @JasonerProperty("decrypted_token")
        public Builder decryptedToken(ApplePayDecryptedTokenData value) {
            this.decryptedToken = value;
            return this;
        }

        /**
         * storedCredential
         */
        @JasonerProperty("stored_credential")
        public Builder storedCredential(CardStoredCredential value) {
            this.storedCredential = value;
            return this;
        }

        /**
         * vaultId
         */
        @JasonerProperty("vault_id")
        public Builder vaultId(VaultId value) {
            this.vaultId = value;
            return this;
        }

        /**
         * attributes
         */
        
        public Builder attributes(ApplePayAttributes value) {
            this.attributes = value;
            return this;
        }

        public ApplePayRequest build() {
            return new ApplePayRequest(this);
        }

    }

}

