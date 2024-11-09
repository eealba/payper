package io.github.eealba.payper.subscriptions.v1.model;



public class ShippingDetail {

    
    private final Name name;
    
    private final Type type;
    
    private final AddressPortable address;

    private ShippingDetail(Builder builder) {
        name = builder.name;
        type = builder.type;
        address = builder.address;

    }

    
    public Name name() {
        return name;
    }

    
    public Type type() {
        return type;
    }

    
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

        
        public Builder name(Name value) {
            name = value;
            return this;
        }

        
        public Builder type(Type value) {
            type = value;
            return this;
        }

        
        public Builder address(AddressPortable value) {
            address = value;
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

