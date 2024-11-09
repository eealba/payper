package io.github.eealba.payper.subscriptions.v1.model;



/**
 * The suspend subscription request details.
 */
public record SubscriptionSuspendRequest(String reason) {

    public SubscriptionSuspendRequest(String reason) {
        if (reason == null) {
            throw new IllegalArgumentException("Field reason can`t be null");
        }
        this.reason = reason;
    }

}
