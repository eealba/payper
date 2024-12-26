package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;


/**
 * Information needed to pay using Venmo.
 */
public class VenmoWalletRequest {


    @JasonerProperty("vault_id")
    private final VaultId vaultId;
    @JasonerProperty("email_address")
    private final Email emailAddress;
    @JasonerProperty("experience_context")
    private final VenmoWalletExperienceContext experienceContext;
    
    private final VenmoWalletAttributes attributes;

    private VenmoWalletRequest(Builder builder) {
        vaultId = builder.vaultId;
        emailAddress = builder.emailAddress;
        experienceContext = builder.experienceContext;
        attributes = builder.attributes;

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
     * experienceContext
     */
    @JasonerProperty("experience_context")
    public VenmoWalletExperienceContext experienceContext() {
        return experienceContext;
    }

    /**
     * attributes
     */
    
    public VenmoWalletAttributes attributes() {
        return attributes;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private VaultId vaultId;
        private Email emailAddress;
        private VenmoWalletExperienceContext experienceContext;
        private VenmoWalletAttributes attributes;

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
         * experienceContext
         */
        @JasonerProperty("experience_context")
        public Builder experienceContext(VenmoWalletExperienceContext value) {
            this.experienceContext = value;
            return this;
        }

        /**
         * attributes
         */
        
        public Builder attributes(VenmoWalletAttributes value) {
            this.attributes = value;
            return this;
        }

        public VenmoWalletRequest build() {
            return new VenmoWalletRequest(this);
        }

    }


}

