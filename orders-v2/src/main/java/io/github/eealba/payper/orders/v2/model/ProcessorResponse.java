package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The processor response information for payment requests, such as direct credit card transactions.
 */
public class ProcessorResponse {


    @JasonerProperty("avs_code")
    private final AvsCode avsCode;
    @JasonerProperty("cvv_code")
    private final CvvCode cvvCode;
    @JasonerProperty("response_code")
    private final ResponseCode responseCode;
    @JasonerProperty("payment_advice_code")
    private final PaymentAdviceCode paymentAdviceCode;

    private ProcessorResponse(Builder builder) {
        avsCode = builder.avsCode;
        cvvCode = builder.cvvCode;
        responseCode = builder.responseCode;
        paymentAdviceCode = builder.paymentAdviceCode;

    }

    /**
     * The address verification code for Visa, Discover, Mastercard, or American Express transactions.
     */
    @JasonerProperty("avs_code")
    public AvsCode avsCode() {
        return avsCode;
    }

    /**
     * The card verification value code for for Visa, Discover, Mastercard, or American Express.
     */
    @JasonerProperty("cvv_code")
    public CvvCode cvvCode() {
        return cvvCode;
    }

    /**
     * Processor response code for the non-PayPal payment processor errors.
     */
    @JasonerProperty("response_code")
    public ResponseCode responseCode() {
        return responseCode;
    }

    /**
     * The declined payment transactions might have payment advice codes. The card networks, like Visa and 
Mastercard, return payment advice codes.
     */
    @JasonerProperty("payment_advice_code")
    public PaymentAdviceCode paymentAdviceCode() {
        return paymentAdviceCode;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private AvsCode avsCode;
        private CvvCode cvvCode;
        private ResponseCode responseCode;
        private PaymentAdviceCode paymentAdviceCode;

        /**
         * The address verification code for Visa, Discover, Mastercard, or American Express transactions.
         */
        @JasonerProperty("avs_code")
        public Builder avsCode(AvsCode value) {
            this.avsCode = value;
            return this;
        }

        /**
         * The card verification value code for for Visa, Discover, Mastercard, or American Express.
         */
        @JasonerProperty("cvv_code")
        public Builder cvvCode(CvvCode value) {
            this.cvvCode = value;
            return this;
        }

        /**
         * Processor response code for the non-PayPal payment processor errors.
         */
        @JasonerProperty("response_code")
        public Builder responseCode(ResponseCode value) {
            this.responseCode = value;
            return this;
        }

        /**
         * The declined payment transactions might have payment advice codes. The card networks, like Visa and 
Mastercard, return payment advice codes.
         */
        @JasonerProperty("payment_advice_code")
        public Builder paymentAdviceCode(PaymentAdviceCode value) {
            this.paymentAdviceCode = value;
            return this;
        }

        public ProcessorResponse build() {
            return new ProcessorResponse(this);
        }

    }
    /**
     * The address verification code for Visa, Discover, Mastercard, or American Express transactions.
     */
    public enum AvsCode {
        A,
        B,
        C,
        D,
        E,
        F,
        G,
        I,
        M,
        N,
        P,
        R,
        S,
        U,
        W,
        X,
        Y,
        Z,
        @JasonerProperty("Null")
    NULL,
        @JasonerProperty("0")
    NUM_0,
        @JasonerProperty("1")
    NUM_1,
        @JasonerProperty("2")
    NUM_2,
        @JasonerProperty("3")
    NUM_3,
        @JasonerProperty("4")
    NUM_4
    }
    /**
     * The card verification value code for for Visa, Discover, Mastercard, or American Express.
     */
    public enum CvvCode {
        E,
        I,
        M,
        N,
        P,
        S,
        U,
        X,
        @JasonerProperty("All others")
    ALL_OTHERS,
        @JasonerProperty("0")
    NUM_0,
        @JasonerProperty("1")
    NUM_1,
        @JasonerProperty("2")
    NUM_2,
        @JasonerProperty("3")
    NUM_3,
        @JasonerProperty("4")
    NUM_4
    }
    /**
     * Processor response code for the non-PayPal payment processor errors.
     */
    public enum ResponseCode {
        @JasonerProperty("0000")
    NUM_0000,
        @JasonerProperty("00N7")
    NUM_00N7,
        @JasonerProperty("0100")
    NUM_0100,
        @JasonerProperty("0390")
    NUM_0390,
        @JasonerProperty("0500")
    NUM_0500,
        @JasonerProperty("0580")
    NUM_0580,
        @JasonerProperty("0800")
    NUM_0800,
        @JasonerProperty("0880")
    NUM_0880,
        @JasonerProperty("0890")
    NUM_0890,
        @JasonerProperty("0960")
    NUM_0960,
        @JasonerProperty("0R00")
    NUM_0R00,
        @JasonerProperty("1000")
    NUM_1000,
        @JasonerProperty("10BR")
    NUM_10BR,
        @JasonerProperty("1300")
    NUM_1300,
        @JasonerProperty("1310")
    NUM_1310,
        @JasonerProperty("1312")
    NUM_1312,
        @JasonerProperty("1317")
    NUM_1317,
        @JasonerProperty("1320")
    NUM_1320,
        @JasonerProperty("1330")
    NUM_1330,
        @JasonerProperty("1335")
    NUM_1335,
        @JasonerProperty("1340")
    NUM_1340,
        @JasonerProperty("1350")
    NUM_1350,
        @JasonerProperty("1352")
    NUM_1352,
        @JasonerProperty("1360")
    NUM_1360,
        @JasonerProperty("1370")
    NUM_1370,
        @JasonerProperty("1380")
    NUM_1380,
        @JasonerProperty("1382")
    NUM_1382,
        @JasonerProperty("1384")
    NUM_1384,
        @JasonerProperty("1390")
    NUM_1390,
        @JasonerProperty("1393")
    NUM_1393,
        @JasonerProperty("5100")
    NUM_5100,
        @JasonerProperty("5110")
    NUM_5110,
        @JasonerProperty("5120")
    NUM_5120,
        @JasonerProperty("5130")
    NUM_5130,
        @JasonerProperty("5135")
    NUM_5135,
        @JasonerProperty("5140")
    NUM_5140,
        @JasonerProperty("5150")
    NUM_5150,
        @JasonerProperty("5160")
    NUM_5160,
        @JasonerProperty("5170")
    NUM_5170,
        @JasonerProperty("5180")
    NUM_5180,
        @JasonerProperty("5190")
    NUM_5190,
        @JasonerProperty("5200")
    NUM_5200,
        @JasonerProperty("5210")
    NUM_5210,
        @JasonerProperty("5400")
    NUM_5400,
        @JasonerProperty("5500")
    NUM_5500,
        @JasonerProperty("5650")
    NUM_5650,
        @JasonerProperty("5700")
    NUM_5700,
        @JasonerProperty("5710")
    NUM_5710,
        @JasonerProperty("5800")
    NUM_5800,
        @JasonerProperty("5900")
    NUM_5900,
        @JasonerProperty("5910")
    NUM_5910,
        @JasonerProperty("5920")
    NUM_5920,
        @JasonerProperty("5930")
    NUM_5930,
        @JasonerProperty("5950")
    NUM_5950,
        @JasonerProperty("6300")
    NUM_6300,
        @JasonerProperty("7600")
    NUM_7600,
        @JasonerProperty("7700")
    NUM_7700,
        @JasonerProperty("7710")
    NUM_7710,
        @JasonerProperty("7800")
    NUM_7800,
        @JasonerProperty("7900")
    NUM_7900,
        @JasonerProperty("8000")
    NUM_8000,
        @JasonerProperty("8010")
    NUM_8010,
        @JasonerProperty("8020")
    NUM_8020,
        @JasonerProperty("8030")
    NUM_8030,
        @JasonerProperty("8100")
    NUM_8100,
        @JasonerProperty("8110")
    NUM_8110,
        @JasonerProperty("8220")
    NUM_8220,
        @JasonerProperty("9100")
    NUM_9100,
        @JasonerProperty("9500")
    NUM_9500,
        @JasonerProperty("9510")
    NUM_9510,
        @JasonerProperty("9520")
    NUM_9520,
        @JasonerProperty("9530")
    NUM_9530,
        @JasonerProperty("9540")
    NUM_9540,
        @JasonerProperty("9600")
    NUM_9600,
        PCNR,
        PCVV,
        PP06,
        PPRN,
        PPAD,
        PPAB,
        PPAE,
        PPAG,
        PPAI,
        PPAR,
        PPAU,
        PPAV,
        PPAX,
        PPBG,
        PPC2,
        PPCE,
        PPCO,
        PPCR,
        PPCT,
        PPCU,
        PPD3,
        PPDC,
        PPDI,
        PPDV,
        PPDT,
        PPEF,
        PPEL,
        PPER,
        PPEX,
        PPFE,
        PPFI,
        PPFR,
        PPFV,
        PPGR,
        PPH1,
        PPIF,
        PPII,
        PPIM,
        PPIT,
        PPLR,
        PPLS,
        PPMB,
        PPMC,
        PPMD,
        PPNC,
        PPNL,
        PPNM,
        PPNT,
        PPPH,
        PPPI,
        PPPM,
        PPQC,
        PPRE,
        PPRF,
        PPRR,
        PPS0,
        PPS1,
        PPS2,
        PPS3,
        PPS4,
        PPS5,
        PPS6,
        PPSC,
        PPSD,
        PPSE,
        PPTE,
        PPTF,
        PPTI,
        PPTR,
        PPTT,
        PPTV,
        PPUA,
        PPUC,
        PPUE,
        PPUI,
        PPUP,
        PPUR,
        PPVC,
        PPVE,
        PPVT
    }
    /**
     * The declined payment transactions might have payment advice codes. The card networks, like Visa and Mastercard, return payment advice codes.
     */
    public enum PaymentAdviceCode {
        @JasonerProperty("01")
    NUM_01,
        @JasonerProperty("02")
    NUM_02,
        @JasonerProperty("03")
    NUM_03,
        @JasonerProperty("21")
    NUM_21
    }
}

