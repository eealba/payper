package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;

/**
 * Resource consolidating common request and response attirbutes for vaulting Venmo Wallet.
 */
public class VaultVenmoWalletBase {


    @JasonerProperty("store_in_vault")
    private final StoreInVaultInstruction storeInVault;
    
    private final String description;
    @JasonerProperty("usage_pattern")
    private final UsagePattern usagePattern;
    @JasonerProperty("usage_type")
    private final UsageType usageType;
    @JasonerProperty("customer_type")
    private final CustomerType customerType;
    @JasonerProperty("permit_multiple_payment_tokens")
    private final Boolean permitMultiplePaymentTokens;

    private VaultVenmoWalletBase(Builder builder) {
        description = builder.description;
        usagePattern = builder.usagePattern;
        usageType = builder.usageType;
        customerType = builder.customerType;
        permitMultiplePaymentTokens = builder.permitMultiplePaymentTokens;
        storeInVault = Objects.requireNonNull(builder.storeInVault);
    }

    /**
     * storeInVault
     */
    @JasonerProperty("store_in_vault")
    public StoreInVaultInstruction storeInVault() {
        return storeInVault;
    }

    /**
     * The description displayed to Venmo consumer on the approval flow for Venmo, as well as on the Venmo payment 
token management experience on Venmo.com.
     */
    
    public String description() {
        return description;
    }

    /**
     * Expected business/pricing model for the billing agreement.
     */
    @JasonerProperty("usage_pattern")
    public UsagePattern usagePattern() {
        return usagePattern;
    }

    /**
     * The usage type associated with the Venmo payment token.
     */
    @JasonerProperty("usage_type")
    public UsageType usageType() {
        return usageType;
    }

    /**
     * The customer type associated with the Venmo payment token. This is to indicate whether the customer acting on 
the merchant / platform is either a business or a consumer.
     */
    @JasonerProperty("customer_type")
    public CustomerType customerType() {
        return customerType;
    }

    /**
     * Create multiple payment tokens for the same payer, merchant/platform combination. Use this when the customer 
has not logged in at merchant/platform. The payment token thus generated, can then also be used to create the 
customer account at merchant/platform. Use this also when multiple payment tokens are required for the same 
payer, different customer at merchant/platform. This helps to identify customers distinctly even though they 
may share the same Venmo account.
     */
    @JasonerProperty("permit_multiple_payment_tokens")
    public Boolean permitMultiplePaymentTokens() {
        return permitMultiplePaymentTokens;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private StoreInVaultInstruction storeInVault;
        private String description;
        private UsagePattern usagePattern;
        private UsageType usageType;
        private CustomerType customerType;
        private Boolean permitMultiplePaymentTokens;

        /**
         * storeInVault
         */
        @JasonerProperty("store_in_vault")
        public Builder storeInVault(StoreInVaultInstruction value) {
            this.storeInVault = value;
            return this;
        }

        /**
         * The description displayed to Venmo consumer on the approval flow for Venmo, as well as on the Venmo payment 
token management experience on Venmo.com.
         */
        
        public Builder description(String value) {
            this.description = value;
            return this;
        }

        /**
         * Expected business/pricing model for the billing agreement.
         */
        @JasonerProperty("usage_pattern")
        public Builder usagePattern(UsagePattern value) {
            this.usagePattern = value;
            return this;
        }

        /**
         * The usage type associated with the Venmo payment token.
         */
        @JasonerProperty("usage_type")
        public Builder usageType(UsageType value) {
            this.usageType = value;
            return this;
        }

        /**
         * The customer type associated with the Venmo payment token. This is to indicate whether the customer acting on 
the merchant / platform is either a business or a consumer.
         */
        @JasonerProperty("customer_type")
        public Builder customerType(CustomerType value) {
            this.customerType = value;
            return this;
        }

        /**
         * Create multiple payment tokens for the same payer, merchant/platform combination. Use this when the customer 
has not logged in at merchant/platform. The payment token thus generated, can then also be used to create the 
customer account at merchant/platform. Use this also when multiple payment tokens are required for the same 
payer, different customer at merchant/platform. This helps to identify customers distinctly even though they 
may share the same Venmo account.
         */
        @JasonerProperty("permit_multiple_payment_tokens")
        public Builder permitMultiplePaymentTokens(Boolean value) {
            this.permitMultiplePaymentTokens = value;
            return this;
        }

        public VaultVenmoWalletBase build() {
            return new VaultVenmoWalletBase(this);
        }

    }
    /**
     * Expected business/pricing model for the billing agreement.
     */
    public enum UsagePattern {
        IMMEDIATE,
        DEFERRED,
        RECURRING_PREPAID,
        RECURRING_POSTPAID,
        THRESHOLD_PREPAID,
        THRESHOLD_POSTPAID
    }
    /**
     * The usage type associated with the Venmo payment token.
     */
    public enum UsageType {
        MERCHANT,
        PLATFORM
    }
    /**
     * The customer type associated with the Venmo payment token. This is to indicate whether the customer acting on the merchant / platform is either a business or a consumer.
     */
    public enum CustomerType {
        CONSUMER,
        BUSINESS
    }
}

