package io.github.eealba.payper.subscriptions.v1.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * Results of Authentication such as 3D Secure.
 */
public class AuthenticationResponse {


    @JasonerProperty("liability_shift")
    private final LiabilityShift liabilityShift;
    @JasonerProperty("three_d_secure")
    private final ThreeDSecureAuthenticationResponse threeDSecure;

    private AuthenticationResponse(Builder builder) {
        liabilityShift = builder.liabilityShift;
        threeDSecure = builder.threeDSecure;

    }

    /**
     * liabilityShift
     */
    @JasonerProperty("liability_shift")
    public LiabilityShift liabilityShift() {
        return liabilityShift;
    }

    /**
     * threeDSecure
     */
    @JasonerProperty("three_d_secure")
    public ThreeDSecureAuthenticationResponse threeDSecure() {
        return threeDSecure;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private LiabilityShift liabilityShift;
        private ThreeDSecureAuthenticationResponse threeDSecure;

        /**
         * liabilityShift
         */
        @JasonerProperty("liability_shift")
        public Builder liabilityShift(LiabilityShift value) {
            this.liabilityShift = value;
            return this;
        }

        /**
         * threeDSecure
         */
        @JasonerProperty("three_d_secure")
        public Builder threeDSecure(ThreeDSecureAuthenticationResponse value) {
            this.threeDSecure = value;
            return this;
        }

        public AuthenticationResponse build() {
            return new AuthenticationResponse(this);
        }

    }

}

