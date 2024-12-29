package io.github.eealba.payper.payments.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * Identifiers related to a specific resource.
 */
public class RelatedIds {


    @JasonerProperty("order_id")
    private final String orderId;
    @JasonerProperty("authorization_id")
    private final String authorizationId;
    @JasonerProperty("capture_id")
    private final String captureId;

    private RelatedIds(Builder builder) {
        orderId = builder.orderId;
        authorizationId = builder.authorizationId;
        captureId = builder.captureId;

    }

    /**
     * Order ID related to the resource.
     */
    @JasonerProperty("order_id")
    public String orderId() {
        return orderId;
    }

    /**
     * Authorization ID related to the resource.
     */
    @JasonerProperty("authorization_id")
    public String authorizationId() {
        return authorizationId;
    }

    /**
     * Capture ID related to the resource.
     */
    @JasonerProperty("capture_id")
    public String captureId() {
        return captureId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String orderId;
        private String authorizationId;
        private String captureId;

        /**
         * Order ID related to the resource.
         */
        @JasonerProperty("order_id")
        public Builder orderId(String value) {
            this.orderId = value;
            return this;
        }

        /**
         * Authorization ID related to the resource.
         */
        @JasonerProperty("authorization_id")
        public Builder authorizationId(String value) {
            this.authorizationId = value;
            return this;
        }

        /**
         * Capture ID related to the resource.
         */
        @JasonerProperty("capture_id")
        public Builder captureId(String value) {
            this.captureId = value;
            return this;
        }

        public RelatedIds build() {
            return new RelatedIds(this);
        }

    }

}

