package io.github.eealba.payper.orders.v2.model;
/**
 * The status of the item shipment. For allowed values, see <a href="/docs/tracking/reference/shipping-status/">Shipping 
Statuses</a>.
 */
public enum ShipmentTrackingStatus {
    CANCELLED,
    DELIVERED,
    LOCAL_PICKUP,
    ON_HOLD,
    SHIPPED,
    SHIPMENT_CREATED,
    DROPPED_OFF,
    IN_TRANSIT,
    RETURNED,
    LABEL_PRINTED,
    ERROR,
    UNCONFIRMED,
    PICKUP_FAILED,
    DELIVERY_DELAYED,
    DELIVERY_SCHEDULED,
    DELIVERY_FAILED,
    INRETURN,
    IN_PROCESS,
    NEW,
    VOID,
    PROCESSED,
    NOT_SHIPPED,
    COMPLETED
}
