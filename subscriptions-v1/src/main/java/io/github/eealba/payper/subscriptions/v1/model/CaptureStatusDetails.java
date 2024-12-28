package io.github.eealba.payper.subscriptions.v1.model;



/**
 * The details of the captured payment status.
 */
public class CaptureStatusDetails {


    
    private final Reason reason;

    private CaptureStatusDetails(Builder builder) {
        reason = builder.reason;

    }

    /**
     * The reason why the captured payment status is `PENDING` or `DENIED`.
     */
    
    public Reason reason() {
        return reason;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Reason reason;

        /**
         * The reason why the captured payment status is `PENDING` or `DENIED`.
         */
        
        public Builder reason(Reason value) {
            this.reason = value;
            return this;
        }

        public CaptureStatusDetails build() {
            return new CaptureStatusDetails(this);
        }

    }
    /**
     * The reason why the captured payment status is `PENDING` or `DENIED`.
     */
    public enum Reason {
        BUYER_COMPLAINT,
        CHARGEBACK,
        ECHECK,
        INTERNATIONAL_WITHDRAWAL,
        OTHER,
        PENDING_REVIEW,
        RECEIVING_PREFERENCE_MANDATES_MANUAL_ACTION,
        REFUNDED,
        TRANSACTION_APPROVED_AWAITING_FUNDING,
        UNILATERAL,
        VERIFICATION_REQUIRED
    }
}

