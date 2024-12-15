package io.github.eealba.payper.subscriptions.v1.model;



/**
 * The shipping details.
 */
public class ShippingDetail {


    
    private final Name name;
    
    private final Type type;
    
    private final AddressPortable address;

    private ShippingDetail(Builder builder) {
        name = builder.name;
        type = builder.type;
        address = builder.address;

    }

    /**
     * name
     */
    
    public Name name() {
        return name;
    }

    /**
     * The method by which the payer wants to get their items from the payee e.g shipping, in-person pickup. Either 
type or options but not both may be present.
     */
    
    public Type type() {
        return type;
    }

    /**
     * address
     */
    
    public AddressPortable address() {
        return address;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Name name;
        private Type type;
        private AddressPortable address;

        /**
         * name
         */
        
        public Builder name(Name value) {
            this.name = value;
            return this;
        }

        /**
         * The method by which the payer wants to get their items from the payee e.g shipping, in-person pickup. Either 
type or options but not both may be present.
         */
        
        public Builder type(Type value) {
            this.type = value;
            return this;
        }

        /**
         * address
         */
        
        public Builder address(AddressPortable value) {
            this.address = value;
            return this;
        }

        public ShippingDetail build() {
            return new ShippingDetail(this);
        }

    }
    /**
     * The method by which the payer wants to get their items from the payee e.g shipping, in-person pickup. Either type or options but not both may be present.
     */
    public enum Type {
        SHIPPING,
        PICKUP_IN_PERSON
    }
}

