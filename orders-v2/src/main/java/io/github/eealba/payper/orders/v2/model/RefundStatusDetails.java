package io.github.eealba.payper.orders.v2.model;



/**
 * The details of the refund status.
 */
public class RefundStatusDetails {


    
    private final String reason;

    private RefundStatusDetails(Builder builder) {
        reason = builder.reason;

    }

    /**
     * The reason why the refund has the `PENDING` or `FAILED` status.
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
         * The reason why the refund has the `PENDING` or `FAILED` status.
         */
        
        public Builder reason(String value) {
            this.reason = value;
            return this;
        }

        public RefundStatusDetails build() {
            return new RefundStatusDetails(this);
        }

    }

}

