package io.github.eealba.payper.payments.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * Any additional payments instructions during refund payment processing. This object is only applicable to merchants that 
have been enabled for PayPal Commerce Platform for Marketplaces and Platforms capability. Please speak to your account 
manager if you want to use this capability.
 */
public class PaymentInstruction2 {


    @JasonerProperty("platform_fees")
    private final List<PlatformFee> platformFees;

    private PaymentInstruction2(Builder builder) {
        platformFees = builder.platformFees;

    }

    /**
     * Specifies the amount that the API caller will contribute to the refund being processed. The amount needs to be 
lower than platform_fees amount originally captured or the amount that is remaining if multiple refunds have 
been processed. This field is only applicable to merchants that have been enabled for PayPal Commerce Platform 
for Marketplaces and Platforms capability. Please speak to your account manager if you want to use this 
capability.
     */
    @JasonerProperty("platform_fees")
    public List<PlatformFee> platformFees() {
        return platformFees;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private List<PlatformFee> platformFees;

        /**
         * Specifies the amount that the API caller will contribute to the refund being processed. The amount needs to be 
lower than platform_fees amount originally captured or the amount that is remaining if multiple refunds have 
been processed. This field is only applicable to merchants that have been enabled for PayPal Commerce Platform 
for Marketplaces and Platforms capability. Please speak to your account manager if you want to use this 
capability.
         */
        @JasonerProperty("platform_fees")
        public Builder platformFees(List<PlatformFee> value) {
            this.platformFees = value;
            return this;
        }

        public PaymentInstruction2 build() {
            return new PaymentInstruction2(this);
        }

    }

}

