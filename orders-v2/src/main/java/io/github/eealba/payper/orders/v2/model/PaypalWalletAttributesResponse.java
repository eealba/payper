package io.github.eealba.payper.orders.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * Additional attributes associated with the use of a PayPal Wallet.
 */
public class PaypalWalletAttributesResponse {


    
    private final PaypalWalletVaultResponse vault;
    @JasonerProperty("cobranded_cards")
    private final List<CobrandedCard> cobrandedCards;

    private PaypalWalletAttributesResponse(Builder builder) {
        vault = builder.vault;
        cobrandedCards = builder.cobrandedCards;

    }

    /**
     * vault
     */
    
    public PaypalWalletVaultResponse vault() {
        return vault;
    }

    /**
     * An array of merchant cobranded cards used by buyer to complete an order. This array will be present if a 
merchant has onboarded their cobranded card with PayPal and provided corresponding label(s).
     */
    @JasonerProperty("cobranded_cards")
    public List<CobrandedCard> cobrandedCards() {
        return cobrandedCards;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private PaypalWalletVaultResponse vault;
        private List<CobrandedCard> cobrandedCards;

        /**
         * vault
         */
        
        public Builder vault(PaypalWalletVaultResponse value) {
            this.vault = value;
            return this;
        }

        /**
         * An array of merchant cobranded cards used by buyer to complete an order. This array will be present if a 
merchant has onboarded their cobranded card with PayPal and provided corresponding label(s).
         */
        @JasonerProperty("cobranded_cards")
        public Builder cobrandedCards(List<CobrandedCard> value) {
            this.cobrandedCards = value;
            return this;
        }

        public PaypalWalletAttributesResponse build() {
            return new PaypalWalletAttributesResponse(this);
        }

    }

}

