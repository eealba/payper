package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * Resource consolidating common request and response attributes for vaulting PayPal Wallet.
 */
public class VaultPaypalWalletBase {


    @JasonerProperty("store_in_vault")
    private final StoreInVaultInstruction storeInVault;
    
    private final String description;
    @JasonerProperty("usage_pattern")
    private final UsagePattern usagePattern;
    
    private final ShippingDetail shipping;
    @JasonerProperty("usage_type")
    private final UsageType usageType;
    @JasonerProperty("owner_id")
    private final VaultOwnerId ownerId;
    @JasonerProperty("customer_type")
    private final CustomerType customerType;
    @JasonerProperty("permit_multiple_payment_tokens")
    private final Boolean permitMultiplePaymentTokens;

    private VaultPaypalWalletBase(Builder builder) {
        storeInVault = builder.storeInVault;
        description = builder.description;
        usagePattern = builder.usagePattern;
        shipping = builder.shipping;
        usageType = builder.usageType;
        ownerId = builder.ownerId;
        customerType = builder.customerType;
        permitMultiplePaymentTokens = builder.permitMultiplePaymentTokens;

    }

    /**
     * storeInVault
     */
    @JasonerProperty("store_in_vault")
    public StoreInVaultInstruction storeInVault() {
        return storeInVault;
    }

    /**
     * The description displayed to PayPal consumer on the approval flow for PayPal, as well as on the PayPal payment 
token management experience on PayPal.com.
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
     * shipping
     */
    
    public ShippingDetail shipping() {
        return shipping;
    }

    /**
     * The usage type associated with the PayPal payment token.
     */
    @JasonerProperty("usage_type")
    public UsageType usageType() {
        return usageType;
    }

    /**
     * ownerId
     */
    @JasonerProperty("owner_id")
    public VaultOwnerId ownerId() {
        return ownerId;
    }

    /**
     * The customer type associated with the PayPal payment token. This is to indicate whether the customer acting on 
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
may share the same PayPal account. This only applies to PayPal payment source.
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
        private ShippingDetail shipping;
        private UsageType usageType;
        private VaultOwnerId ownerId;
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
         * The description displayed to PayPal consumer on the approval flow for PayPal, as well as on the PayPal payment 
token management experience on PayPal.com.
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
         * shipping
         */
        
        public Builder shipping(ShippingDetail value) {
            this.shipping = value;
            return this;
        }

        /**
         * The usage type associated with the PayPal payment token.
         */
        @JasonerProperty("usage_type")
        public Builder usageType(UsageType value) {
            this.usageType = value;
            return this;
        }

        /**
         * ownerId
         */
        @JasonerProperty("owner_id")
        public Builder ownerId(VaultOwnerId value) {
            this.ownerId = value;
            return this;
        }

        /**
         * The customer type associated with the PayPal payment token. This is to indicate whether the customer acting on 
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
may share the same PayPal account. This only applies to PayPal payment source.
         */
        @JasonerProperty("permit_multiple_payment_tokens")
        public Builder permitMultiplePaymentTokens(Boolean value) {
            this.permitMultiplePaymentTokens = value;
            return this;
        }

        public VaultPaypalWalletBase build() {
            return new VaultPaypalWalletBase(this);
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
     * The usage type associated with the PayPal payment token.
     */
    public enum UsageType {
        MERCHANT,
        PLATFORM
    }
    /**
     * The customer type associated with the PayPal payment token. This is to indicate whether the customer acting on the merchant / platform is either a business or a consumer.
     */
    public enum CustomerType {
        CONSUMER,
        BUSINESS
    }
}

