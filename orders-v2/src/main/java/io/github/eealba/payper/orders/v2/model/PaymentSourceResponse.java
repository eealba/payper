package io.github.eealba.payper.orders.v2.model;



/**
 * The payment source used to fund the payment.
 */
public class PaymentSourceResponse {


    
    private final CardResponse card;
    
    private final PaypalWalletResponse paypal;
    
    private final Bancontact bancontact;
    
    private final Blik blik;
    
    private final Eps eps;
    
    private final Giropay giropay;
    
    private final Ideal ideal;
    
    private final Mybank mybank;
    
    private final P24 p24;
    
    private final Sofort sofort;
    
    private final Trustly trustly;
    
    private final VenmoWalletResponse venmo;

    private PaymentSourceResponse(Builder builder) {
        card = builder.card;
        paypal = builder.paypal;
        bancontact = builder.bancontact;
        blik = builder.blik;
        eps = builder.eps;
        giropay = builder.giropay;
        ideal = builder.ideal;
        mybank = builder.mybank;
        p24 = builder.p24;
        sofort = builder.sofort;
        trustly = builder.trustly;
        venmo = builder.venmo;

    }

    /**
     * card
     */
    
    public CardResponse card() {
        return card;
    }

    /**
     * paypal
     */
    
    public PaypalWalletResponse paypal() {
        return paypal;
    }

    /**
     * bancontact
     */
    
    public Bancontact bancontact() {
        return bancontact;
    }

    /**
     * blik
     */
    
    public Blik blik() {
        return blik;
    }

    /**
     * eps
     */
    
    public Eps eps() {
        return eps;
    }

    /**
     * giropay
     */
    
    public Giropay giropay() {
        return giropay;
    }

    /**
     * ideal
     */
    
    public Ideal ideal() {
        return ideal;
    }

    /**
     * mybank
     */
    
    public Mybank mybank() {
        return mybank;
    }

    /**
     * p24
     */
    
    public P24 p24() {
        return p24;
    }

    /**
     * sofort
     */
    
    public Sofort sofort() {
        return sofort;
    }

    /**
     * trustly
     */
    
    public Trustly trustly() {
        return trustly;
    }

    /**
     * venmo
     */
    
    public VenmoWalletResponse venmo() {
        return venmo;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private CardResponse card;
        private PaypalWalletResponse paypal;
        private Bancontact bancontact;
        private Blik blik;
        private Eps eps;
        private Giropay giropay;
        private Ideal ideal;
        private Mybank mybank;
        private P24 p24;
        private Sofort sofort;
        private Trustly trustly;
        private VenmoWalletResponse venmo;

        /**
         * card
         */
        
        public Builder card(CardResponse value) {
            this.card = value;
            return this;
        }

        /**
         * paypal
         */
        
        public Builder paypal(PaypalWalletResponse value) {
            this.paypal = value;
            return this;
        }

        /**
         * bancontact
         */
        
        public Builder bancontact(Bancontact value) {
            this.bancontact = value;
            return this;
        }

        /**
         * blik
         */
        
        public Builder blik(Blik value) {
            this.blik = value;
            return this;
        }

        /**
         * eps
         */
        
        public Builder eps(Eps value) {
            this.eps = value;
            return this;
        }

        /**
         * giropay
         */
        
        public Builder giropay(Giropay value) {
            this.giropay = value;
            return this;
        }

        /**
         * ideal
         */
        
        public Builder ideal(Ideal value) {
            this.ideal = value;
            return this;
        }

        /**
         * mybank
         */
        
        public Builder mybank(Mybank value) {
            this.mybank = value;
            return this;
        }

        /**
         * p24
         */
        
        public Builder p24(P24 value) {
            this.p24 = value;
            return this;
        }

        /**
         * sofort
         */
        
        public Builder sofort(Sofort value) {
            this.sofort = value;
            return this;
        }

        /**
         * trustly
         */
        
        public Builder trustly(Trustly value) {
            this.trustly = value;
            return this;
        }

        /**
         * venmo
         */
        
        public Builder venmo(VenmoWalletResponse value) {
            this.venmo = value;
            return this;
        }

        public PaymentSourceResponse build() {
            return new PaymentSourceResponse(this);
        }

    }


}

