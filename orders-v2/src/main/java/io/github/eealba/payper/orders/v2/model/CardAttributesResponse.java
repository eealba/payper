package io.github.eealba.payper.orders.v2.model;



/**
 * Additional attributes associated with the use of this card.
 */
public class CardAttributesResponse {


    
    private final VaultResponse vault;

    private CardAttributesResponse(Builder builder) {
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

        public CardAttributesResponse build() {
            return new CardAttributesResponse(this);
        }

    }


}

