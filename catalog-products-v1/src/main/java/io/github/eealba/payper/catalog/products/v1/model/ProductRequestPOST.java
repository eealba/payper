package io.github.eealba.payper.catalog.products.v1.model;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;

public class ProductRequestPOST {

    
    private final String id;
    
    private final String name;
    
    private final String description;
    
    private final Type type;
    
    private final ProductCategory category;
    @JasonerProperty("image_url")
    private final String imageUrl;
    @JasonerProperty("home_url")
    private final String homeUrl;

    private ProductRequestPOST(Builder builder) {
        id = builder.id;
        description = builder.description;
        category = builder.category;
        imageUrl = builder.imageUrl;
        homeUrl = builder.homeUrl;
        name = Objects.requireNonNull(builder.name);
        type = Objects.requireNonNull(builder.type);
    }

    /**
     * The ID of the product. You can specify the SKU for the product. If you omit the ID, the system generates it. 
System-generated IDs have the `PROD-` prefix.
     */
    
    public String id() {
        return id;
    }

    /**
     * The product name.
     */
    
    public String name() {
        return name;
    }

    /**
     * The product description.
     */
    
    public String description() {
        return description;
    }

    /**
     * The product type. Indicates whether the product is physical or digital goods, or a service.
     */
    
    public Type type() {
        return type;
    }

    /**
     * category
     */
    
    public ProductCategory category() {
        return category;
    }

    /**
     * The image URL for the product.
     */
    @JasonerProperty("image_url")
    public String imageUrl() {
        return imageUrl;
    }

    /**
     * The home page URL for the product.
     */
    @JasonerProperty("home_url")
    public String homeUrl() {
        return homeUrl;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String id;
        private String name;
        private String description;
        private Type type;
        private ProductCategory category;
        private String imageUrl;
        private String homeUrl;

        /**
         * The ID of the product. You can specify the SKU for the product. If you omit the ID, the system generates it. 
System-generated IDs have the `PROD-` prefix.
         */
        
        public Builder id(String value) {
            this.id = value;
            return this;
        }

        /**
         * The product name.
         */
        
        public Builder name(String value) {
            this.name = value;
            return this;
        }

        /**
         * The product description.
         */
        
        public Builder description(String value) {
            this.description = value;
            return this;
        }

        /**
         * The product type. Indicates whether the product is physical or digital goods, or a service.
         */
        
        public Builder type(Type value) {
            this.type = value;
            return this;
        }

        /**
         * category
         */
        
        public Builder category(ProductCategory value) {
            this.category = value;
            return this;
        }

        /**
         * The image URL for the product.
         */
        @JasonerProperty("image_url")
        public Builder imageUrl(String value) {
            this.imageUrl = value;
            return this;
        }

        /**
         * The home page URL for the product.
         */
        @JasonerProperty("home_url")
        public Builder homeUrl(String value) {
            this.homeUrl = value;
            return this;
        }

        public ProductRequestPOST build() {
            return new ProductRequestPOST(this);
        }

    }
    /**
     * The product type. Indicates whether the product is physical or digital goods, or a service.
     */
    public enum Type {
        PHYSICAL,
        DIGITAL,
        SERVICE
    }
}

