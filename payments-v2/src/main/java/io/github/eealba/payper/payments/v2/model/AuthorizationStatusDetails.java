package io.github.eealba.payper.payments.v2.model;



/**
 * The details of the authorized payment status.
 */
public class AuthorizationStatusDetails {


    
    private final String reason;

    private AuthorizationStatusDetails(Builder builder) {
        reason = builder.reason;

    }

    /**
     * The reason why the authorized status is `PENDING`.
     */
    
    public String reason() {
        return reason;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String reason;

        /**
         * The reason why the authorized status is `PENDING`.
         */
        
        public Builder reason(String value) {
            this.reason = value;
            return this;
        }

        public AuthorizationStatusDetails build() {
            return new AuthorizationStatusDetails(this);
        }

    }

}

