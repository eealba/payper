package io.github.eealba.payper.invoices.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The reference to the refund payment detail.
 */
public class RefundReference {


    @JasonerProperty("refund_id")
    private final String refundId;

    private RefundReference(Builder builder) {
        refundId = builder.refundId;

    }

    /**
     * The ID of the refund of an invoice payment.
     */
    @JasonerProperty("refund_id")
    public String refundId() {
        return refundId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String refundId;

        /**
         * The ID of the refund of an invoice payment.
         */
        @JasonerProperty("refund_id")
        public Builder refundId(String value) {
            this.refundId = value;
            return this;
        }

        public RefundReference build() {
            return new RefundReference(this);
        }

    }

}

