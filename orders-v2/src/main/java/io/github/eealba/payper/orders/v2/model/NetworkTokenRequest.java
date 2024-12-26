package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;


/**
 * The Third Party Network token used to fund a payment.
 */
public class NetworkTokenRequest {


    
    private final String number;
    
    private final DateYearMonth expiry;
    
    private final String cryptogram;
    @JasonerProperty("eci_flag")
    private final EciFlag eciFlag;
    @JasonerProperty("token_requestor_id")
    private final String tokenRequestorId;

    private NetworkTokenRequest(Builder builder) {
        cryptogram = builder.cryptogram;
        eciFlag = builder.eciFlag;
        tokenRequestorId = builder.tokenRequestorId;
        number = Objects.requireNonNull(builder.number);
        expiry = Objects.requireNonNull(builder.expiry);
    }

    /**
     * Third party network token number.
     */
    
    public String number() {
        return number;
    }

    /**
     * expiry
     */
    
    public DateYearMonth expiry() {
        return expiry;
    }

    /**
     * An Encrypted one-time use value that's sent along with Network Token. This field is not required to be present 
for recurring transactions.
     */
    
    public String cryptogram() {
        return cryptogram;
    }

    /**
     * eciFlag
     */
    @JasonerProperty("eci_flag")
    public EciFlag eciFlag() {
        return eciFlag;
    }

    /**
     * A TRID, or a Token Requestor ID, is an identifier used by merchants to request network tokens from card 
networks. A TRID is a precursor to obtaining a network token for a credit card primary account number (PAN), 
and will aid in enabling secure card on file (COF) payments and reducing fraud.
     */
    @JasonerProperty("token_requestor_id")
    public String tokenRequestorId() {
        return tokenRequestorId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String number;
        private DateYearMonth expiry;
        private String cryptogram;
        private EciFlag eciFlag;
        private String tokenRequestorId;

        /**
         * Third party network token number.
         */
        
        public Builder number(String value) {
            this.number = value;
            return this;
        }

        /**
         * expiry
         */
        
        public Builder expiry(DateYearMonth value) {
            this.expiry = value;
            return this;
        }

        /**
         * An Encrypted one-time use value that's sent along with Network Token. This field is not required to be present 
for recurring transactions.
         */
        
        public Builder cryptogram(String value) {
            this.cryptogram = value;
            return this;
        }

        /**
         * eciFlag
         */
        @JasonerProperty("eci_flag")
        public Builder eciFlag(EciFlag value) {
            this.eciFlag = value;
            return this;
        }

        /**
         * A TRID, or a Token Requestor ID, is an identifier used by merchants to request network tokens from card 
networks. A TRID is a precursor to obtaining a network token for a credit card primary account number (PAN), 
and will aid in enabling secure card on file (COF) payments and reducing fraud.
         */
        @JasonerProperty("token_requestor_id")
        public Builder tokenRequestorId(String value) {
            this.tokenRequestorId = value;
            return this;
        }

        public NetworkTokenRequest build() {
            return new NetworkTokenRequest(this);
        }

    }


}

