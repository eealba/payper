package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * Basic vault instruction specification that can be extended by specific payment sources that supports vaulting.
 */
public class VaultInstructionBase {


    @JasonerProperty("store_in_vault")
    private final StoreInVaultInstruction storeInVault;

    private VaultInstructionBase(Builder builder) {
        storeInVault = builder.storeInVault;

    }

    /**
     * storeInVault
     */
    @JasonerProperty("store_in_vault")
    public StoreInVaultInstruction storeInVault() {
        return storeInVault;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private StoreInVaultInstruction storeInVault;

        /**
         * storeInVault
         */
        @JasonerProperty("store_in_vault")
        public Builder storeInVault(StoreInVaultInstruction value) {
            this.storeInVault = value;
            return this;
        }

        public VaultInstructionBase build() {
            return new VaultInstructionBase(this);
        }

    }

}

