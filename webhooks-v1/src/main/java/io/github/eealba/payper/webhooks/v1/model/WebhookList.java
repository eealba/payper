package io.github.eealba.payper.webhooks.v1.model;

import java.util.List;

/**
 * A list of webhooks.
 */
public class WebhookList {


    
    private final List<Webhook> webhooks;

    private WebhookList(Builder builder) {
        webhooks = builder.webhooks;
    }

    /**
     * An array of webhooks.
     */
    
    public List<Webhook> webhooks() {
        return webhooks;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private List<Webhook> webhooks;

        /**
         * An array of webhooks.
         */
        
        public Builder webhooks(List<Webhook> value) {
            this.webhooks = value;
            return this;
        }

        public WebhookList build() {
            return new WebhookList(this);
        }

    }

}

