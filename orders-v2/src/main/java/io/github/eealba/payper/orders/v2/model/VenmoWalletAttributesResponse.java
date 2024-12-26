package io.github.eealba.payper.orders.v2.model;



/**
 * Additional attributes associated with the use of a Venmo Wallet.
 */
public class VenmoWalletAttributesResponse {


    
    private final VaultResponse vault;

    private VenmoWalletAttributesResponse(Builder builder) {
        vault = builder.vault;

    }

    /**
     * vault
     */
    
    public VaultResponse vault() {
        return vault;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private VaultResponse vault;

        /**
         * vault
         */
        
        public Builder vault(VaultResponse value) {
            this.vault = value;
            return this;
        }

        public VenmoWalletAttributesResponse build() {
            return new VenmoWalletAttributesResponse(this);
        }

    }


}

