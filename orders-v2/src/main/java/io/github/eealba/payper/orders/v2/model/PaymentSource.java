package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The payment source definition.
 */
public class PaymentSource {


    
    private final CardRequest card;
    
    private final Token token;
    
    private final PaypalWallet paypal;
    
    private final BancontactRequest bancontact;
    
    private final BlikRequest blik;
    
    private final EpsRequest eps;
    
    private final GiropayRequest giropay;
    
    private final IdealRequest ideal;
    
    private final MybankRequest mybank;
    
    private final P24Request p24;
    
    private final SofortRequest sofort;
    
    private final TrustlyRequest trustly;
    @JasonerProperty("apple_pay")
    private final ApplePayRequest applePay;
    @JasonerProperty("google_pay")
    private final GooglePayRequest googlePay;
    
    private final VenmoWalletRequest venmo;

    private PaymentSource(Builder builder) {
        card = builder.card;
        token = builder.token;
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
        applePay = builder.applePay;
        googlePay = builder.googlePay;
        venmo = builder.venmo;

    }

    /**
     * card
     */
    
    public CardRequest card() {
        return card;
    }

    /**
     * token
     */
    
    public Token token() {
        return token;
    }

    /**
     * paypal
     */
    
    public PaypalWallet paypal() {
        return paypal;
    }

    /**
     * bancontact
     */
    
    public BancontactRequest bancontact() {
        return bancontact;
    }

    /**
     * blik
     */
    
    public BlikRequest blik() {
        return blik;
    }

    /**
     * eps
     */
    
    public EpsRequest eps() {
        return eps;
    }

    /**
     * giropay
     */
    
    public GiropayRequest giropay() {
        return giropay;
    }

    /**
     * ideal
     */
    
    public IdealRequest ideal() {
        return ideal;
    }

    /**
     * mybank
     */
    
    public MybankRequest mybank() {
        return mybank;
    }

    /**
     * p24
     */
    
    public P24Request p24() {
        return p24;
    }

    /**
     * sofort
     */
    
    public SofortRequest sofort() {
        return sofort;
    }

    /**
     * trustly
     */
    
    public TrustlyRequest trustly() {
        return trustly;
    }

    /**
     * applePay
     */
    @JasonerProperty("apple_pay")
    public ApplePayRequest applePay() {
        return applePay;
    }

    /**
     * googlePay
     */
    @JasonerProperty("google_pay")
    public GooglePayRequest googlePay() {
        return googlePay;
    }

    /**
     * venmo
     */
    
    public VenmoWalletRequest venmo() {
        return venmo;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private CardRequest card;
        private Token token;
        private PaypalWallet paypal;
        private BancontactRequest bancontact;
        private BlikRequest blik;
        private EpsRequest eps;
        private GiropayRequest giropay;
        private IdealRequest ideal;
        private MybankRequest mybank;
        private P24Request p24;
        private SofortRequest sofort;
        private TrustlyRequest trustly;
        private ApplePayRequest applePay;
        private GooglePayRequest googlePay;
        private VenmoWalletRequest venmo;

        /**
         * card
         */
        
        public Builder card(CardRequest value) {
            this.card = value;
            return this;
        }

        /**
         * token
         */
        
        public Builder token(Token value) {
            this.token = value;
            return this;
        }

        /**
         * paypal
         */
        
        public Builder paypal(PaypalWallet value) {
            this.paypal = value;
            return this;
        }

        /**
         * bancontact
         */
        
        public Builder bancontact(BancontactRequest value) {
            this.bancontact = value;
            return this;
        }

        /**
         * blik
         */
        
        public Builder blik(BlikRequest value) {
            this.blik = value;
            return this;
        }

        /**
         * eps
         */
        
        public Builder eps(EpsRequest value) {
            this.eps = value;
            return this;
        }

        /**
         * giropay
         */
        
        public Builder giropay(GiropayRequest value) {
            this.giropay = value;
            return this;
        }

        /**
         * ideal
         */
        
        public Builder ideal(IdealRequest value) {
            this.ideal = value;
            return this;
        }

        /**
         * mybank
         */
        
        public Builder mybank(MybankRequest value) {
            this.mybank = value;
            return this;
        }

        /**
         * p24
         */
        
        public Builder p24(P24Request value) {
            this.p24 = value;
            return this;
        }

        /**
         * sofort
         */
        
        public Builder sofort(SofortRequest value) {
            this.sofort = value;
            return this;
        }

        /**
         * trustly
         */
        
        public Builder trustly(TrustlyRequest value) {
            this.trustly = value;
            return this;
        }

        /**
         * applePay
         */
        @JasonerProperty("apple_pay")
        public Builder applePay(ApplePayRequest value) {
            this.applePay = value;
            return this;
        }

        /**
         * googlePay
         */
        @JasonerProperty("google_pay")
        public Builder googlePay(GooglePayRequest value) {
            this.googlePay = value;
            return this;
        }

        /**
         * venmo
         */
        
        public Builder venmo(VenmoWalletRequest value) {
            this.venmo = value;
            return this;
        }

        public PaymentSource build() {
            return new PaymentSource(this);
        }

    }

}

