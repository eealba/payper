package io.github.eealba.payper.subscriptions.v1.model;



/**
 * The cancel subscription request details.
 */
public record SubscriptionCancelRequest(String reason) {

    public SubscriptionCancelRequest(String reason) {
        if (reason == null) {
            throw new IllegalArgumentException("Field reason can`t be null");
        }
        this.reason = reason;
    }

}
