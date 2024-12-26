package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;


/**
 * Information about the Payment data obtained by decrypting Apple Pay token.
 */
public class ApplePayDecryptedTokenData {


    @JasonerProperty("transaction_amount")
    private final Money transactionAmount;
    @JasonerProperty("tokenized_card")
    private final Card tokenizedCard;
    @JasonerProperty("device_manufacturer_id")
    private final String deviceManufacturerId;
    @JasonerProperty("payment_data_type")
    private final PaymentDataType paymentDataType;
    @JasonerProperty("payment_data")
    private final ApplePayPaymentData paymentData;

    private ApplePayDecryptedTokenData(Builder builder) {
        transactionAmount = builder.transactionAmount;
        deviceManufacturerId = builder.deviceManufacturerId;
        paymentDataType = builder.paymentDataType;
        paymentData = builder.paymentData;
        tokenizedCard = Objects.requireNonNull(builder.tokenizedCard);
    }

    /**
     * transactionAmount
     */
    @JasonerProperty("transaction_amount")
    public Money transactionAmount() {
        return transactionAmount;
    }

    /**
     * tokenizedCard
     */
    @JasonerProperty("tokenized_card")
    public Card tokenizedCard() {
        return tokenizedCard;
    }

    /**
     * Apple Pay Hex-encoded device manufacturer identifier. The pattern is defined by an external party and supports 
Unicode.
     */
    @JasonerProperty("device_manufacturer_id")
    public String deviceManufacturerId() {
        return deviceManufacturerId;
    }

    /**
     * Indicates the type of payment data passed, in case of Non China the payment data is 3DSECURE and for China it 
is EMV.
     */
    @JasonerProperty("payment_data_type")
    public PaymentDataType paymentDataType() {
        return paymentDataType;
    }

    /**
     * paymentData
     */
    @JasonerProperty("payment_data")
    public ApplePayPaymentData paymentData() {
        return paymentData;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Money transactionAmount;
        private Card tokenizedCard;
        private String deviceManufacturerId;
        private PaymentDataType paymentDataType;
        private ApplePayPaymentData paymentData;

        /**
         * transactionAmount
         */
        @JasonerProperty("transaction_amount")
        public Builder transactionAmount(Money value) {
            this.transactionAmount = value;
            return this;
        }

        /**
         * tokenizedCard
         */
        @JasonerProperty("tokenized_card")
        public Builder tokenizedCard(Card value) {
            this.tokenizedCard = value;
            return this;
        }

        /**
         * Apple Pay Hex-encoded device manufacturer identifier. The pattern is defined by an external party and supports 
Unicode.
         */
        @JasonerProperty("device_manufacturer_id")
        public Builder deviceManufacturerId(String value) {
            this.deviceManufacturerId = value;
            return this;
        }

        /**
         * Indicates the type of payment data passed, in case of Non China the payment data is 3DSECURE and for China it 
is EMV.
         */
        @JasonerProperty("payment_data_type")
        public Builder paymentDataType(PaymentDataType value) {
            this.paymentDataType = value;
            return this;
        }

        /**
         * paymentData
         */
        @JasonerProperty("payment_data")
        public Builder paymentData(ApplePayPaymentData value) {
            this.paymentData = value;
            return this;
        }

        public ApplePayDecryptedTokenData build() {
            return new ApplePayDecryptedTokenData(this);
        }

    }
    /**
     * Indicates the type of payment data passed, in case of Non China the payment data is 3DSECURE and for China it is EMV.
     */
    public enum PaymentDataType {
        @JasonerProperty("3DSECURE")
    NUM_3DSECURE,
        EMV
    }

}

