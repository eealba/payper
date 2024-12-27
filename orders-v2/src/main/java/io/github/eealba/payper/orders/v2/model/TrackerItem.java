package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The details of the items in the shipment.
 */
public class TrackerItem {


    
    private final String name;
    
    private final String quantity;
    
    private final String sku;
    
    private final String url;
    @JasonerProperty("image_url")
    private final String imageUrl;
    
    private final UniversalProductCode upc;

    private TrackerItem(Builder builder) {
        name = builder.name;
        quantity = builder.quantity;
        sku = builder.sku;
        url = builder.url;
        imageUrl = builder.imageUrl;
        upc = builder.upc;

    }

    /**
     * The item name or title.
     */
    
    public String name() {
        return name;
    }

    /**
     * The item quantity. Must be a whole number.
     */
    
    public String quantity() {
        return quantity;
    }

    /**
     * The stock keeping unit (SKU) for the item. This can contain unicode characters.
     */
    
    public String sku() {
        return sku;
    }

    /**
     * The URL to the item being purchased. Visible to buyer and used in buyer experiences.
     */
    
    public String url() {
        return url;
    }

    /**
     * The URL of the item's image. File type and size restrictions apply. An image that violates these restrictions 
will not be honored.
     */
    @JasonerProperty("image_url")
    public String imageUrl() {
        return imageUrl;
    }

    /**
     * upc
     */
    
    public UniversalProductCode upc() {
        return upc;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String name;
        private String quantity;
        private String sku;
        private String url;
        private String imageUrl;
        private UniversalProductCode upc;

        /**
         * The item name or title.
         */
        
        public Builder name(String value) {
            this.name = value;
            return this;
        }

        /**
         * The item quantity. Must be a whole number.
         */
        
        public Builder quantity(String value) {
            this.quantity = value;
            return this;
        }

        /**
         * The stock keeping unit (SKU) for the item. This can contain unicode characters.
         */
        
        public Builder sku(String value) {
            this.sku = value;
            return this;
        }

        /**
         * The URL to the item being purchased. Visible to buyer and used in buyer experiences.
         */
        
        public Builder url(String value) {
            this.url = value;
            return this;
        }

        /**
         * The URL of the item's image. File type and size restrictions apply. An image that violates these restrictions 
will not be honored.
         */
        @JasonerProperty("image_url")
        public Builder imageUrl(String value) {
            this.imageUrl = value;
            return this;
        }

        /**
         * upc
         */
        
        public Builder upc(UniversalProductCode value) {
            this.upc = value;
            return this;
        }

        public TrackerItem build() {
            return new TrackerItem(this);
        }

    }

}

