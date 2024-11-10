package io.github.eealba.payper.subscriptions.v1.model;



public class SubscriptionActivateRequest {

    
    private final String reason;

    private SubscriptionActivateRequest(Builder builder) {
        reason = builder.reason;

    }

    
    public String reason() {
        return reason;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String reason;

        
        public Builder reason(String value) {
            this.reason = value;
            return this;
        }

        public SubscriptionActivateRequest build() {
            return new SubscriptionActivateRequest(this);
        }

    }

}
