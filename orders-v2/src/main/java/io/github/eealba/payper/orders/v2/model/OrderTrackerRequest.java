package io.github.eealba.payper.orders.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * The tracking details of an order.
 */
public class OrderTrackerRequest {


    @JasonerProperty("transaction_id")
    private final String transactionId;
    @JasonerProperty("tracking_number")
    private final String trackingNumber;
    @JasonerProperty("tracking_number_type")
    private final ShipmentTrackingNumberType trackingNumberType;
    
    private final ShipmentTrackingStatus status;
    @JasonerProperty("shipment_date")
    private final LocalDate shipmentDate;
    
    private final ShipmentCarrier carrier;
    @JasonerProperty("carrier_name_other")
    private final String carrierNameOther;
    @JasonerProperty("postage_payment_id")
    private final String postagePaymentId;
    @JasonerProperty("notify_buyer")
    private final Boolean notifyBuyer;
    
    private final Integer quantity;
    @JasonerProperty("tracking_number_validated")
    private final Boolean trackingNumberValidated;
    @JasonerProperty("last_updated_time")
    private final Instant lastUpdatedTime;
    @JasonerProperty("shipment_direction")
    private final ShipmentDirection shipmentDirection;
    @JasonerProperty("shipment_uploader")
    private final ShipmentUploader shipmentUploader;
    @JasonerProperty("capture_id")
    private final String captureId;
    @JasonerProperty("notify_payer")
    private final Boolean notifyPayer;
    
    private final List<TrackerItem> items;

    private OrderTrackerRequest(Builder builder) {
        trackingNumber = builder.trackingNumber;
        trackingNumberType = builder.trackingNumberType;
        shipmentDate = builder.shipmentDate;
        carrier = builder.carrier;
        carrierNameOther = builder.carrierNameOther;
        postagePaymentId = builder.postagePaymentId;
        notifyBuyer = builder.notifyBuyer;
        quantity = builder.quantity;
        trackingNumberValidated = builder.trackingNumberValidated;
        lastUpdatedTime = builder.lastUpdatedTime;
        shipmentDirection = builder.shipmentDirection;
        shipmentUploader = builder.shipmentUploader;
        notifyPayer = builder.notifyPayer;
        items = builder.items;
        transactionId = Objects.requireNonNull(builder.transactionId);
        status = Objects.requireNonNull(builder.status);
        captureId = Objects.requireNonNull(builder.captureId);
    }

    /**
     * The PayPal transaction ID.
     */
    @JasonerProperty("transaction_id")
    public String transactionId() {
        return transactionId;
    }

    /**
     * The tracking number for the shipment. This property supports Unicode.
     */
    @JasonerProperty("tracking_number")
    public String trackingNumber() {
        return trackingNumber;
    }

    /**
     * trackingNumberType
     */
    @JasonerProperty("tracking_number_type")
    public ShipmentTrackingNumberType trackingNumberType() {
        return trackingNumberType;
    }

    /**
     * status
     */
    
    public ShipmentTrackingStatus status() {
        return status;
    }

    /**
     * shipmentDate
     */
    @JasonerProperty("shipment_date")
    public LocalDate shipmentDate() {
        return shipmentDate;
    }

    /**
     * carrier
     */
    
    public ShipmentCarrier carrier() {
        return carrier;
    }

    /**
     * The name of the carrier for the shipment. Provide this value only if the carrier parameter is OTHER. This 
property supports Unicode.
     */
    @JasonerProperty("carrier_name_other")
    public String carrierNameOther() {
        return carrierNameOther;
    }

    /**
     * The postage payment ID. This property supports Unicode.
     */
    @JasonerProperty("postage_payment_id")
    public String postagePaymentId() {
        return postagePaymentId;
    }

    /**
     * If true, sends an email notification to the buyer of the PayPal transaction. The email contains the tracking 
information that was uploaded through the API.
     */
    @JasonerProperty("notify_buyer")
    public Boolean notifyBuyer() {
        return notifyBuyer;
    }

    /**
     * The quantity of items shipped.
     */
    
    public Integer quantity() {
        return quantity;
    }

    /**
     * Indicates whether the carrier validated the tracking number.
     */
    @JasonerProperty("tracking_number_validated")
    public Boolean trackingNumberValidated() {
        return trackingNumberValidated;
    }

    /**
     * lastUpdatedTime
     */
    @JasonerProperty("last_updated_time")
    public Instant lastUpdatedTime() {
        return lastUpdatedTime;
    }

    /**
     * To denote whether the shipment is sent forward to the receiver or returned back.
     */
    @JasonerProperty("shipment_direction")
    public ShipmentDirection shipmentDirection() {
        return shipmentDirection;
    }

    /**
     * To denote which party uploaded the shipment tracking info.
     */
    @JasonerProperty("shipment_uploader")
    public ShipmentUploader shipmentUploader() {
        return shipmentUploader;
    }

    /**
     * The PayPal capture ID.
     */
    @JasonerProperty("capture_id")
    public String captureId() {
        return captureId;
    }

    /**
     * If true, sends an email notification to the payer of the PayPal transaction. The email contains the tracking 
information that was uploaded through the API.
     */
    @JasonerProperty("notify_payer")
    public Boolean notifyPayer() {
        return notifyPayer;
    }

    /**
     * An array of details of items in the shipment.
     */
    
    public List<TrackerItem> items() {
        return items;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String transactionId;
        private String trackingNumber;
        private ShipmentTrackingNumberType trackingNumberType;
        private ShipmentTrackingStatus status;
        private LocalDate shipmentDate;
        private ShipmentCarrier carrier;
        private String carrierNameOther;
        private String postagePaymentId;
        private Boolean notifyBuyer;
        private Integer quantity;
        private Boolean trackingNumberValidated;
        private Instant lastUpdatedTime;
        private ShipmentDirection shipmentDirection;
        private ShipmentUploader shipmentUploader;
        private String captureId;
        private Boolean notifyPayer;
        private List<TrackerItem> items;

        /**
         * The PayPal transaction ID.
         */
        @JasonerProperty("transaction_id")
        public Builder transactionId(String value) {
            this.transactionId = value;
            return this;
        }

        /**
         * The tracking number for the shipment. This property supports Unicode.
         */
        @JasonerProperty("tracking_number")
        public Builder trackingNumber(String value) {
            this.trackingNumber = value;
            return this;
        }

        /**
         * trackingNumberType
         */
        @JasonerProperty("tracking_number_type")
        public Builder trackingNumberType(ShipmentTrackingNumberType value) {
            this.trackingNumberType = value;
            return this;
        }

        /**
         * status
         */
        
        public Builder status(ShipmentTrackingStatus value) {
            this.status = value;
            return this;
        }

        /**
         * shipmentDate
         */
        @JasonerProperty("shipment_date")
        public Builder shipmentDate(LocalDate value) {
            this.shipmentDate = value;
            return this;
        }

        /**
         * carrier
         */
        
        public Builder carrier(ShipmentCarrier value) {
            this.carrier = value;
            return this;
        }

        /**
         * The name of the carrier for the shipment. Provide this value only if the carrier parameter is OTHER. This 
property supports Unicode.
         */
        @JasonerProperty("carrier_name_other")
        public Builder carrierNameOther(String value) {
            this.carrierNameOther = value;
            return this;
        }

        /**
         * The postage payment ID. This property supports Unicode.
         */
        @JasonerProperty("postage_payment_id")
        public Builder postagePaymentId(String value) {
            this.postagePaymentId = value;
            return this;
        }

        /**
         * If true, sends an email notification to the buyer of the PayPal transaction. The email contains the tracking 
information that was uploaded through the API.
         */
        @JasonerProperty("notify_buyer")
        public Builder notifyBuyer(Boolean value) {
            this.notifyBuyer = value;
            return this;
        }

        /**
         * The quantity of items shipped.
         */
        
        public Builder quantity(Integer value) {
            this.quantity = value;
            return this;
        }

        /**
         * Indicates whether the carrier validated the tracking number.
         */
        @JasonerProperty("tracking_number_validated")
        public Builder trackingNumberValidated(Boolean value) {
            this.trackingNumberValidated = value;
            return this;
        }

        /**
         * lastUpdatedTime
         */
        @JasonerProperty("last_updated_time")
        public Builder lastUpdatedTime(Instant value) {
            this.lastUpdatedTime = value;
            return this;
        }

        /**
         * To denote whether the shipment is sent forward to the receiver or returned back.
         */
        @JasonerProperty("shipment_direction")
        public Builder shipmentDirection(ShipmentDirection value) {
            this.shipmentDirection = value;
            return this;
        }

        /**
         * To denote which party uploaded the shipment tracking info.
         */
        @JasonerProperty("shipment_uploader")
        public Builder shipmentUploader(ShipmentUploader value) {
            this.shipmentUploader = value;
            return this;
        }

        /**
         * The PayPal capture ID.
         */
        @JasonerProperty("capture_id")
        public Builder captureId(String value) {
            this.captureId = value;
            return this;
        }

        /**
         * If true, sends an email notification to the payer of the PayPal transaction. The email contains the tracking 
information that was uploaded through the API.
         */
        @JasonerProperty("notify_payer")
        public Builder notifyPayer(Boolean value) {
            this.notifyPayer = value;
            return this;
        }

        /**
         * An array of details of items in the shipment.
         */
        
        public Builder items(List<TrackerItem> value) {
            this.items = value;
            return this;
        }

        public OrderTrackerRequest build() {
            return new OrderTrackerRequest(this);
        }

    }
    /**
     * To denote whether the shipment is sent forward to the receiver or returned back.
     */
    public enum ShipmentDirection {
        FORWARD,
        RETURN
    }
    /**
     * To denote which party uploaded the shipment tracking info.
     */
    public enum ShipmentUploader {
        MERCHANT,
        CONSUMER,
        PARTNER
    }
}

