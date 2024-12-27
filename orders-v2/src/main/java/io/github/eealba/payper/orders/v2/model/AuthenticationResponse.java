package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * Results of Authentication such as 3D Secure.
 */
public class AuthenticationResponse {


    @JasonerProperty("liability_shift")
    private final LiabilityShift liabilityShift;
    @JasonerProperty("three_d_secure")
    private final ThreeDSecureAuthenticationResponse threeDSecure;
    @JasonerProperty("authentication_flow")
    private final AuthenticationFlow authenticationFlow;
    @JasonerProperty("exemption_details")
    private final ExemptionDetails exemptionDetails;

    private AuthenticationResponse(Builder builder) {
        liabilityShift = builder.liabilityShift;
        threeDSecure = builder.threeDSecure;
        authenticationFlow = builder.authenticationFlow;
        exemptionDetails = builder.exemptionDetails;

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

    /**
     * authenticationFlow
     */
    @JasonerProperty("authentication_flow")
    public AuthenticationFlow authenticationFlow() {
        return authenticationFlow;
    }

    /**
     * exemptionDetails
     */
    @JasonerProperty("exemption_details")
    public ExemptionDetails exemptionDetails() {
        return exemptionDetails;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private LiabilityShift liabilityShift;
        private ThreeDSecureAuthenticationResponse threeDSecure;
        private AuthenticationFlow authenticationFlow;
        private ExemptionDetails exemptionDetails;

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

        /**
         * authenticationFlow
         */
        @JasonerProperty("authentication_flow")
        public Builder authenticationFlow(AuthenticationFlow value) {
            this.authenticationFlow = value;
            return this;
        }

        /**
         * exemptionDetails
         */
        @JasonerProperty("exemption_details")
        public Builder exemptionDetails(ExemptionDetails value) {
            this.exemptionDetails = value;
            return this;
        }

        public AuthenticationResponse build() {
            return new AuthenticationResponse(this);
        }

    }

}

