package io.github.eealba.payper.catalog.products.v1.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.time.Instant;
import java.util.List;

/**
 * The product details.
 */
public class Product {


    
    private final String id;
    
    private final String name;
    
    private final String description;
    
    private final Type type;
    
    private final ProductCategory category;
    @JasonerProperty("image_url")
    private final String imageUrl;
    @JasonerProperty("home_url")
    private final String homeUrl;
    @JasonerProperty("create_time")
    private final Instant createTime;
    @JasonerProperty("update_time")
    private final Instant updateTime;
    
    private final List<LinkDescription> links;

    private Product(Builder builder) {
        id = builder.id;
        name = builder.name;
        description = builder.description;
        type = builder.type;
        category = builder.category;
        imageUrl = builder.imageUrl;
        homeUrl = builder.homeUrl;
        createTime = builder.createTime;
        updateTime = builder.updateTime;
        links = builder.links;

    }

    /**
     * The ID of the product.
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

    /**
     * createTime
     */
    @JasonerProperty("create_time")
    public Instant createTime() {
        return createTime;
    }

    /**
     * updateTime
     */
    @JasonerProperty("update_time")
    public Instant updateTime() {
        return updateTime;
    }

    /**
     * An array of request-related [HATEOAS links](/docs/api/overview/#hateoas-links).
     */
    
    public List<LinkDescription> links() {
        return links;
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
        private Instant createTime;
        private Instant updateTime;
        private List<LinkDescription> links;

        /**
         * The ID of the product.
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

        /**
         * createTime
         */
        @JasonerProperty("create_time")
        public Builder createTime(Instant value) {
            this.createTime = value;
            return this;
        }

        /**
         * updateTime
         */
        @JasonerProperty("update_time")
        public Builder updateTime(Instant value) {
            this.updateTime = value;
            return this;
        }

        /**
         * An array of request-related [HATEOAS links](/docs/api/overview/#hateoas-links).
         */
        
        public Builder links(List<LinkDescription> value) {
            this.links = value;
            return this;
        }

        public Product build() {
            return new Product(this);
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

