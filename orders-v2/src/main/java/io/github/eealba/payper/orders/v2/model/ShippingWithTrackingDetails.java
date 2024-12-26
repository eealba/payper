package io.github.eealba.payper.orders.v2.model;

import java.util.List;

/**
 * The order shipping details.
 */
public class ShippingWithTrackingDetails {


    
    private final Name name;
    
    private final Type type;
    
    private final List<ShippingOption> options;
    
    private final AddressPortable address;
    
    private final List<Tracker> trackers;

    private ShippingWithTrackingDetails(Builder builder) {
        name = builder.name;
        type = builder.type;
        options = builder.options;
        address = builder.address;
        trackers = builder.trackers;

    }

    /**
     * name
     */
    
    public Name name() {
        return name;
    }

    /**
     * A classification for the method of purchase fulfillment (e.g shipping, in-store pickup, etc). Either `type` or 
`options` may be present, but not both.
     */
    
    public Type type() {
        return type;
    }

    /**
     * An array of shipping options that the payee or merchant offers to the payer to ship or pick up their items.
     */
    
    public List<ShippingOption> options() {
        return options;
    }

    /**
     * address
     */
    
    public AddressPortable address() {
        return address;
    }

    /**
     * An array of trackers for a transaction.
     */
    
    public List<Tracker> trackers() {
        return trackers;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Name name;
        private Type type;
        private List<ShippingOption> options;
        private AddressPortable address;
        private List<Tracker> trackers;

        /**
         * name
         */
        
        public Builder name(Name value) {
            this.name = value;
            return this;
        }

        /**
         * A classification for the method of purchase fulfillment (e.g shipping, in-store pickup, etc). Either `type` or 
`options` may be present, but not both.
         */
        
        public Builder type(Type value) {
            this.type = value;
            return this;
        }

        /**
         * An array of shipping options that the payee or merchant offers to the payer to ship or pick up their items.
         */
        
        public Builder options(List<ShippingOption> value) {
            this.options = value;
            return this;
        }

        /**
         * address
         */
        
        public Builder address(AddressPortable value) {
            this.address = value;
            return this;
        }

        /**
         * An array of trackers for a transaction.
         */
        
        public Builder trackers(List<Tracker> value) {
            this.trackers = value;
            return this;
        }

        public ShippingWithTrackingDetails build() {
            return new ShippingWithTrackingDetails(this);
        }

    }
    /**
     * A classification for the method of purchase fulfillment (e.g shipping, in-store pickup, etc). Either `type` or `options` may be present, but not both.
     */
    public enum Type {
        SHIPPING,
        PICKUP_IN_PERSON,
        PICKUP_IN_STORE,
        PICKUP_FROM_PERSON
    }

}

